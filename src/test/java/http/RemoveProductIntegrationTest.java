package http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.product.stock.infra.database.documents.ProductDocument;
import com.product.stock.infra.http.jsons.response.ErrorResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import testcontainersconfig.MONGODBTestContainerConfiguration;
import utils.GetMockJson;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveProductIntegrationTest extends MONGODBTestContainerConfiguration {

    private static final String PRODUCT_CODE = "ABC123XYZ";

    @Test
    @Order(1)
    @DisplayName(value = "Removendo um produto")
    public void shouldRemoveProduct() {
        final var documents = GetMockJson.getList("products/products_mongodb", new TypeReference<List<ProductDocument>>() {
        });
        mongoTemplate.insertAll(documents);

        final var params = new HashMap<String, String>();
        params.put("productCode", PRODUCT_CODE);

        final var response = restTemplate.exchange(getBaseURL() + "v1/product/code/{productCode}", HttpMethod.DELETE, null, Void.class, params);
        assertEquals(HttpStatusCode.valueOf(202), response.getStatusCode());
    }

    @Test
    @Order(2)
    @DisplayName(value = "Retornando 404 para produto não encontrado")
    public void shouldReturn404WhenRemoveProduct() {
        final var params = new HashMap<String, String>();
        params.put("productCode", PRODUCT_CODE);

        final var response = restTemplate.exchange(getBaseURL() + "v1/product/code/{productCode}", HttpMethod.DELETE, null, ErrorResponse.class, params);
        assertEquals(HttpStatusCode.valueOf(404), response.getStatusCode());
        assertEquals("ProductNotFoundException", response.getBody().exception());
    }

}
