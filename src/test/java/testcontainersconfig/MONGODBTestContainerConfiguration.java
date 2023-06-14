package testcontainersconfig;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class MONGODBTestContainerConfiguration {

    @Container
    private static final MongoDBContainer container = new MongoDBContainer("mongo:4.4.6");

    @DynamicPropertySource
    static void configDynamicProperties(final DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", container::getReplicaSetUrl);
    }

}
