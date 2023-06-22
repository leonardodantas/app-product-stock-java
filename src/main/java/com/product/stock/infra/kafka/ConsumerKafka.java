package com.product.stock.infra.kafka;

import com.product.stock.infra.kafka.jsons.ProductJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ConsumerKafka {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerKafka.class);

    @KafkaListener(topics = "${spring.kafka.topics.create.product}")
    public void listener(@Header(KafkaHeaders.RECEIVED_KEY) final String key, @Payload final ProductJson json) {
        logger.info("-----------------------------------");
        logger.info("Mensagem recebida com a key {}", key);
        logger.info("Produto recebido: {}", json.name());
        logger.info("-----------------------------------");
    }
}
