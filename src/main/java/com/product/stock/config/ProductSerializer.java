package com.product.stock.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.stock.infra.kafka.jsons.ProductJson;
import org.apache.kafka.common.serialization.Serializer;

import java.io.IOException;

public class ProductSerializer implements Serializer<ProductJson> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(final String topic, final ProductJson productJson) {
        try {
            return objectMapper.writeValueAsBytes(productJson);
        } catch (final IOException e) {
            //TODO
            //TRATAR EXCEPTION
            return null;
        }
    }

}
