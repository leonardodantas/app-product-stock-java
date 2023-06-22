package com.product.stock.infra.kafka;

import com.product.stock.app.messaging.ISendMessage;
import com.product.stock.domain.Product;
import com.product.stock.infra.kafka.jsons.ProductJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProducerKafka implements ISendMessage {

    private static final Logger logger = LoggerFactory.getLogger(ProducerKafka.class);

    private final KafkaTemplate<String, ProductJson> kafkaTemplate;
    private final String topic;

    public ProducerKafka(final KafkaTemplate<String, ProductJson> kafkaTemplate, @Value("${spring.kafka.topics.create.product}") final String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @Override
    public void sendMessage(final Product product) {
        final var json = ProductJson.from(product);
        final var future = kafkaTemplate.send(topic, product.code(), json);

        future.whenComplete((result, ex) -> {
            if (Objects.isNull(ex)) {
                logger.info("Send message to topic: {} with key: {}", result.getRecordMetadata().topic(), result.getRecordMetadata().serializedKeySize());
            } else {
                logger.error("Error send message to topic: {} with key: {}", result.getRecordMetadata().topic(), result.getRecordMetadata().serializedKeySize());
            }
        });
    }
}
