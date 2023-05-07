package com.product.stock.infra.database;

import com.product.stock.domain.Product;
import com.product.stock.infra.database.entities.ProductEntity;
import org.springframework.core.convert.converter.Converter;

public class ProductEntityConverter implements Converter<ProductEntity, Product> {

    @Override
    public Product convert(final ProductEntity entity) {
        return null;
    }
}
