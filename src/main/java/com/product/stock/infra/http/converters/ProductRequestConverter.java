package com.product.stock.infra.http.converters;

import com.product.stock.domain.Product;
import com.product.stock.infra.http.jsons.requests.ProductCreateRequest;
import com.product.stock.infra.http.jsons.requests.ProductUpdateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestConverter implements Converter<ProductCreateRequest, Product> {

    private static final Logger logger = LoggerFactory.getLogger(ProductRequestConverter.class);

    @Override
    public Product convert(final ProductCreateRequest request) {
        logger.info("Convert ProductRequest to Product domain");
        return Product.builder(request.code(), request.name(), request.description(), request.price())
                .quantity(request.quantity())
                .details(request.details())
                .build();
    }

    public Converter<ProductUpdateRequest, Product> toDomain(final String code) {
        logger.info("Convert ProductRequest to Product domain");
        return (request) -> Product.builder(code, request.name(), request.description(), request.price())
                .quantity(request.quantity())
                .details(request.details())
                .build();
    }
}
