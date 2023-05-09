package com.product.stock.infra.http.converters;

import com.product.stock.domain.Product;
import com.product.stock.infra.http.jsons.requests.ProductRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestConverter implements Converter<ProductRequest, Product> {

    private static final Logger logger = LoggerFactory.getLogger(ProductRequestConverter.class);

    @Override
    public Product convert(final ProductRequest request) {
        logger.info("Convert ProductRequest to Product domain");
        return Product.builder(request.name(), request.description(), request.price())
                .quantity(request.quantity())
                .build();
    }
}
