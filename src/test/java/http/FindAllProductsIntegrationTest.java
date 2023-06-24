package http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.product.stock.infra.database.documents.ProductDocument;
import com.product.stock.infra.http.jsons.response.PaginatedResult;
import com.product.stock.infra.http.jsons.response.ProductResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import testcontainersconfig.TestContainerConfiguration;
import utils.GetMockJson;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindAllProductsIntegrationTest extends TestContainerConfiguration {

    @Test
    @Order(1)
    @DisplayName(value = "Recuperando uma pagina de produtos")
    public void shouldFindPageProducts() {
        final var documents = GetMockJson.getList("products/products_mongodb", new TypeReference<List<ProductDocument>>() {
        });
        mongoTemplate.insertAll(documents);

        final var typeReference = new ParameterizedTypeReference<PaginatedResult<ProductResponse>>() {
        };

        final var page = 1;
        final var size = 10;

        final var response =
                restTemplate.exchange(getBaseURL() + "v1/products?page=" + page + "&size=" + size, HttpMethod.GET, null, typeReference);

        assertEquals(HttpStatus.valueOf(200), response.getStatusCode());

        final var paginatedResult = response.getBody();

        assertEquals(3, paginatedResult.totalPages());
        assertEquals(1, paginatedResult.currentPage());
        assertEquals(10, paginatedResult.elements().size());
        assertEquals(10, paginatedResult.pageSize());
        assertEquals(25, paginatedResult.totalElements());
    }

    @Test
    @Order(2)
    @DisplayName(value = "Recuperando ultima pagina de produtos")
    public void shouldFindLastPageProducts() {
        final var typeReference = new ParameterizedTypeReference<PaginatedResult<ProductResponse>>() {
        };

        final var page = 2;
        final var size = 10;

        final var response =
                restTemplate.exchange(getBaseURL() + "v1/products?page=" + page + "&size=" + size, HttpMethod.GET, null, typeReference);

        assertEquals(HttpStatus.valueOf(200), response.getStatusCode());

        final var paginatedResult = response.getBody();

        assertEquals(3, paginatedResult.totalPages());
        assertEquals(2, paginatedResult.currentPage());
        assertEquals(5, paginatedResult.elements().size());
        assertEquals(10, paginatedResult.pageSize());
        assertEquals(25, paginatedResult.totalElements());
    }
}