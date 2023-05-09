package com.product.stock.infra.database.converters;

import com.product.stock.domain.Product;
import com.product.stock.infra.database.entities.ProductEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityConverter implements Converter<ProductEntity, Product> {

    @Override
    public Product convert(final ProductEntity entity) {
        return Product.builder(entity.id(), entity.name(), entity.price())
                .id(entity.id())
                .create(entity.create())
                .build();
    }
}
