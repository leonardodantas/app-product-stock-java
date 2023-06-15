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
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import testcontainersconfig.MONGODBTestContainerConfiguration;
import utils.GetMockJson;

import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateProductIntegrationTest extends MONGODBTestContainerConfiguration {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    @Order(1)
    @DisplayName(value = "Salvando um novo produto com sucesso")
    public void shouldCreateProduct() {

        final var request = GetMockJson.getObject("products/product_to_save_success_request", ProductCreateRequest.class);

        final var response =
                restTemplate.postForEntity("http://localhost:" + port + "/v1/product", request, ProductResponse.class);

        assertEquals(HttpStatusCode.valueOf(201), response.getStatusCode());

        final var productResponse = response.getBody();

        assertNotNull(productResponse.id(), "ID validado");
        assertEquals(request.code().toUpperCase(), productResponse.code(), "Codigo validado em upperCase");
        assertEquals(request.name(), productResponse.name(), "Nome validado");
        assertEquals(request.description(), productResponse.description(), "Descrição validado");
        assertEquals(request.price().compareTo(productResponse.price()), 0, "Preço validado");
        assertEquals(3, request.details().size());

        final var productExistInDataBase = mongoTemplate.exists(Query.query(Criteria.where("id").is(productResponse.id())), ProductDocument.class);

        assertTrue(productExistInDataBase, "Produto inserido na base de dados");

        mongoTemplate.dropCollection("products");
    }

    @Test
    @Order(2)
    @DisplayName(value = "Lançando uma exception pois já existe um produto cadastrado com o mesmo codigo na base de dados")
    public void shouldThrowDocumentAlreadyExistException() {

        final var document = GetMockJson.getObject("products/product_valid_document", ProductDocument.class);

        mongoTemplate.insert(document, "products");

        final var request = GetMockJson.getObject("products/product_to_save_success_request", ProductCreateRequest.class);

        final var response =
                restTemplate.postForEntity("http://localhost:" + port + "/v1/product", request, ErrorResponse.class);

        final var errorResponse = response.getBody();

        assertEquals(HttpStatusCode.valueOf(400), errorResponse.status());
        assertEquals("DocumentAlreadyExistException", errorResponse.exception());

        mongoTemplate.dropCollection("products");
    }

    @Test
    @Order(3)
    @DisplayName("Lançando um MethodArgumentNotValidException ao validar um produto invalido com Bean Validation")
    public void shouldValidateRequestWithBeanValidation() {

        final var request = GetMockJson.getObject("products/product_to_bean_validation", ProductCreateRequest.class);

        final var response =
                restTemplate.postForEntity("http://localhost:" + port + "/v1/product", request, ErrorResponse.class);

        final var errorResponse = response.getBody();

        assertEquals(HttpStatusCode.valueOf(400), errorResponse.status());
        assertEquals("MethodArgumentNotValidException", errorResponse.exception());
    }

    @Test
    @Order(4)
    @DisplayName("Lançando um MethodArgumentNotValidException ao validar uma lista de detalhes vazia")
    public void shouldValidateRequestWithDetailsEmpty() {

        final var request = GetMockJson.getObject("products/product_with_details_empty_request", ProductCreateRequest.class);

        final var response =
                restTemplate.postForEntity("http://localhost:" + port + "/v1/product", request, ErrorResponse.class);

        final var errorResponse = response.getBody();

        assertEquals(HttpStatusCode.valueOf(400), errorResponse.status());
        assertEquals("MethodArgumentNotValidException", errorResponse.exception());
    }

    @Test
    @Order(5)
    @DisplayName("Lançando um MethodArgumentNotValidException ao validar uma lista de detalhes nula")
    public void shouldValidateRequestWithDetailsNull() {

        final var request = GetMockJson.getObject("products/product_with_details_null_request", ProductCreateRequest.class);

        final var response =
                restTemplate.postForEntity("http://localhost:" + port + "/v1/product", request, ErrorResponse.class);

        final var errorResponse = response.getBody();

        assertEquals(HttpStatusCode.valueOf(400), errorResponse.status());
        assertEquals("MethodArgumentNotValidException", errorResponse.exception());
    }

    @Test
    @Order(6)
    @DisplayName("Lançando um MethodArgumentNotValidException ao validar uma lista de detalhes repetidos")
    public void shouldValidateRequestWithDetailsRepeated() {

        final var request = GetMockJson.getObject("products/product_with_details_repeated_request", ProductCreateRequest.class);

        final var response =
                restTemplate.postForEntity("http://localhost:" + port + "/v1/product", request, ErrorResponse.class);

        final var errorResponse = response.getBody();

        assertEquals(HttpStatusCode.valueOf(400), errorResponse.status());
        assertEquals("MethodArgumentNotValidException", errorResponse.exception());
    }

}
