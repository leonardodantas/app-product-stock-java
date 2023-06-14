import com.product.stock.Application;
import com.product.stock.infra.database.documents.ProductDocument;
import com.product.stock.infra.http.jsons.requests.ProductRequest;
import com.product.stock.infra.http.jsons.response.ErrorResponse;
import com.product.stock.infra.http.jsons.response.ProductResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import utils.GetMockJson;

import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateProductIntegrationTest extends MONGODBTestContainerConfiguration {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    @DisplayName(value = "Teste para validar a criação com sucesso de um novo produto")
    public void shouldCreateProduct() {

        final var request = GetMockJson.getObject("products/product_to_save_success", ProductRequest.class);

        final var response =
                restTemplate.postForEntity("http://localhost:" + port + "/v1/product", request, ProductResponse.class);

        assertEquals(HttpStatusCode.valueOf(201), response.getStatusCode());

        final var productResponse = response.getBody();

        assertNotNull(productResponse.id(), "ID validado");
        assertEquals(request.name(), productResponse.name(), "Nome validado");
        assertEquals(request.description(), productResponse.description(), "Descrição validado");
        assertEquals(request.price().compareTo(productResponse.price()), 0, "Preço validado");

        final var productExistInDataBase = mongoTemplate.exists(Query.query(Criteria.where("id").is(productResponse.id())), ProductDocument.class);

        assertTrue(productExistInDataBase, "Produto inserido na base de dados");
    }

    @Test
    @DisplayName("Deve validar um request invalido com Bean Validation e retornar um MethodArgumentNotValidException")
    public void shouldValidateRequestWithBeanValidation() {

        final var request = GetMockJson.getObject("products/product_to_bean_validation", ProductRequest.class);

        final var response =
                restTemplate.postForEntity("http://localhost:" + port + "/v1/product", request, ErrorResponse.class);

        final var errorResponse = response.getBody();

        assertEquals(HttpStatusCode.valueOf(400), errorResponse.status());
        assertEquals("MethodArgumentNotValidException", errorResponse.exception());
    }
}
