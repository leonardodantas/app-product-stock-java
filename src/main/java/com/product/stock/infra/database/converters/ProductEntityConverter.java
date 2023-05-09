package com.product.stock.infra.database.converters;

import com.product.stock.domain.Product;
import com.product.stock.infra.database.entities.ProductEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityConverter implements Converter<ProductEntity, Product> {

    private static final Logger logger = LoggerFactory.getLogger(ProductEntityConverter.class);

    @Override
    public Product convert(final ProductEntity entity) {
        logger.info("Convert ProductEntity to Product domain");
        return Product.builder(entity.id(), entity.name(), entity.price())
                .id(entity.id())
                .create(entity.create())
                .build();
    }
}
