package http;

import com.product.stock.Application;
import com.product.stock.infra.database.documents.ProductDocument;
import com.product.stock.infra.http.jsons.requests.ProductCreateRequest;
import com.product.stock.infra.http.jsons.response.ErrorResponse;
import com.product.stock.infra.http.jsons.response.ProductResponse;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import testcontainersconfig.MONGODBTestContainerConfiguration;
import utils.GetMockJson;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DirtiesContext
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UpdateProductIntegrationTest extends MONGODBTestContainerConfiguration {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    @Order(1)
    @DisplayName("Atualizando um produto com sucesso")
    public void shouldUpdateProduct() {

        final var document = GetMockJson.getObject("products/product_valid_document", ProductDocument.class);
        final var productDocumentSave = mongoTemplate.insert(document, "products");

        final var request = GetMockJson.getObject("products/product_to_update_success_request", ProductCreateRequest.class);

        final var productRequestHttpEntity = new HttpEntity<>(request);

        final var params = new HashMap<String, String>();
        params.put("productCode", "123");

        final var response =
                restTemplate.exchange("http://localhost:" + port + "/v1/product/{productCode}", HttpMethod.PUT, productRequestHttpEntity, ProductResponse.class, params);

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());

        final var productResponse = response.getBody();

        assertEquals(productDocumentSave.id(), productResponse.id());
        assertNotEquals(productDocumentSave.name(), productResponse.name());
        assertNotEquals(productDocumentSave.description(), productResponse.description());
        assertNotEquals(productDocumentSave.details().size(), productResponse.details().size());
    }

    @Test
    @Order(2)
    @DisplayName("Lançando exception pois não existe nenhum produto com esse codigo cadastrado")
    public void shouldThrowProductNotFoundException() {

        final var request = GetMockJson.getObject("products/product_to_update_success_request", ProductCreateRequest.class);

        final var productRequestHttpEntity = new HttpEntity<>(request);

        final var params = new HashMap<String, String>();
        params.put("productCode", "123");

        final var response =
                restTemplate.exchange("http://localhost:" + port + "/v1/product/{productCode}", HttpMethod.PUT, productRequestHttpEntity, ErrorResponse.class, params);

        final var errorResponse = response.getBody();

        assertEquals(HttpStatusCode.valueOf(404), errorResponse.status());
        assertEquals("ProductNotFoundException", errorResponse.exception());
    }

    @BeforeEach
    public void beforeAll() {
        mongoTemplate.dropCollection("products");
    }

}
