package http;

import com.product.stock.Application;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FindProductReviewsIntegrationTest {

    @LocalServerPort
    private int port;
    @Autowired
    protected TestRestTemplate restTemplate;

    @Test
    @Order(1)
    @DisplayName(value = "Buscando um produto pelo seu codigo")
    public void shouldFindCodeByCode() {

        final var params = new HashMap<String, String>();
        params.put("productId", "PRODUCT_CODE");

        final var response =
                restTemplate.exchange("http://localhost:" + port + "/" + "v1/reviews", HttpMethod.GET, null, String.class, params);

    }
}
