package http;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
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
import utils.GetMockJson;

import java.util.HashMap;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@WireMockTest(httpPort = 8081)
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
    @DisplayName(value = "Buscando reviews de um produto atraves de seu codigo")
    public void shouldFindCodeByCode() {

        final var productCode = "TES123TES";
        final var params = new HashMap<String, String>();
        params.put("productCode", productCode);

        stubFor(get("/review/product/" + productCode).willReturn(aResponse()
                .withStatus(200)
                .withBody(GetMockJson.getStringAsJson("reviews/reviews_page_success"))));

        final var response =
                restTemplate.exchange("http://localhost:" + port + "/" + "v1/reviews/product/{productCode}", HttpMethod.GET, null, String.class, params);

        System.out.println(response);
    }

    /**
     * TODO
     * Validar envio do content-type json
     * buscar o property de testes no lugar do principal
     */
}
