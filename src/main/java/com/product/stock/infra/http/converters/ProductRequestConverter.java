package com.product.stock.infra.http.converters;

import com.product.stock.domain.Product;
import com.product.stock.infra.http.requests.ProductRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestConverter implements Converter<ProductRequest, Product> {

    @Override
    public Product convert(final ProductRequest request) {
        return null;
    }
}
