package com.product.stock.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.stock.app.exceptions.DeserializerException;
import com.product.stock.infra.kafka.jsons.ProductJson;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class ProductDeserializer implements Deserializer<ProductJson> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ProductJson deserialize(final String topic, final byte[] bytes) {
        try {
            return objectMapper.readValue(bytes, ProductJson.class);
        } catch (final IOException e) {
            throw new DeserializerException(String.format("Error deserializer ProductJson with topic %s", topic));
        }
    }
}
