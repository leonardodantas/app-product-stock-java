package http;

import com.product.stock.infra.database.documents.ProductDocument;
import com.product.stock.infra.http.jsons.response.ProductResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import testcontainersconfig.MONGODBTestContainerConfiguration;
import utils.GetMockJson;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FIndProductIntegrationTest extends MONGODBTestContainerConfiguration {

    private static final String PRODUCT_CODE = "ABC123XYZ";
    private static final String PRODUCT_ID = "0690e3d6-0a4b-11ee-be56-0242ac120002";

    @Test
    @Order(1)
    @DisplayName(value = "Buscando um produto pelo seu codigo")
    public void shouldFindCodeByCode() {

        final var document = GetMockJson.getObject("products/product_valid_document", ProductDocument.class);
        mongoTemplate.insert(document, "products");

        final var params = new HashMap<String, String>();
        params.put("productCode", PRODUCT_CODE);

        final var response =
                restTemplate.getForEntity(getBaseURL() + "v1/product/code/{productCode}", ProductResponse.class, params);

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());

        final var productResponse = response.getBody();

        assertEquals(PRODUCT_CODE, productResponse.code());
    }

    @Test
    @Order(2)
    @DisplayName(value = "Recebendo um no content 204 para produto não encontrado pelo codigo")
    public void shouldReturnProductNoContentByCode() {

        final var params = new HashMap<String, String>();
        params.put("productCode", "1");

        final var response =
                restTemplate.getForEntity(getBaseURL() + "v1/product/code/{productCode}", Void.class, params);

        assertEquals(HttpStatusCode.valueOf(204), response.getStatusCode());
    }

    @Test
    @Order(3)
    @DisplayName(value = "Buscando um produto pelo seu id")
    public void shouldFindCodeById() {

        final var params = new HashMap<String, String>();
        params.put("productId", PRODUCT_ID);

        final var response =
                restTemplate.getForEntity(getBaseURL() + "v1/product/id/{productId}", ProductResponse.class, params);

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());

        final var productResponse = response.getBody();

        assertEquals(PRODUCT_ID, productResponse.id());
    }

    @Test
    @Order(4)
    @DisplayName(value = "Recebendo um no content 204 para produto não encontrado pelo id")
    public void shouldReturnProductNoContentById() {

        final var params = new HashMap<String, String>();
        params.put("productId", "2");

        final var response =
                restTemplate.getForEntity(getBaseURL() + "v1/product/id/{productId}", Void.class, params);

        assertEquals(HttpStatusCode.valueOf(204), response.getStatusCode());
    }

}
