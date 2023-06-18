package http;

import com.product.stock.infra.database.documents.ProductDocument;
import com.product.stock.infra.http.jsons.requests.ProductCreateRequest;
import com.product.stock.infra.http.jsons.response.ErrorResponse;
import com.product.stock.infra.http.jsons.response.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import testcontainersconfig.MONGODBTestContainerConfiguration;
import utils.GetMockJson;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UpdateProductIntegrationTest extends MONGODBTestContainerConfiguration {

    private static final String PRODUCT_CODE = "ABC123XYZ";

    @Test
    @Order(1)
    @DisplayName("Atualizando um produto com sucesso")
    public void shouldUpdateProduct() {

        final var document = GetMockJson.getObject("products/product_valid_document", ProductDocument.class);
        final var productDocumentSave = mongoTemplate.insert(document, "products");

        final var request = GetMockJson.getObject("products/product_to_update_success_request", ProductCreateRequest.class);

        final var productRequestHttpEntity = new HttpEntity<>(request);

        final var params = new HashMap<String, String>();
        params.put("productCode", PRODUCT_CODE);

        final var response =
                restTemplate.exchange(getBaseURL() + "v1/product/{productCode}", HttpMethod.PUT, productRequestHttpEntity, ProductResponse.class, params);

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
        params.put("productCode", PRODUCT_CODE);

        final var response =
                restTemplate.exchange(getBaseURL() + "v1/product/{productCode}", HttpMethod.PUT, productRequestHttpEntity, ErrorResponse.class, params);

        final var errorResponse = response.getBody();

        assertEquals(HttpStatusCode.valueOf(404), errorResponse.status());
        assertEquals("ProductNotFoundException", errorResponse.exception());
    }

    @BeforeEach
    public void beforeAll() {
        mongoTemplate.dropCollection("products");
    }

}
