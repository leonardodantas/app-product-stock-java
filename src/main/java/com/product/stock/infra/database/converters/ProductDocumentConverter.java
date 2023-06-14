package com.product.stock.infra.database.converters;

import com.product.stock.domain.Product;
import com.product.stock.infra.database.documents.ProductDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductDocumentConverter implements Converter<ProductDocument, Product> {

    private static final Logger logger = LoggerFactory.getLogger(ProductDocumentConverter.class);

    @Override
    public Product convert(final ProductDocument document) {
        logger.info("Convert ProductEntity to Product domain");
        return Product.builder(document.code(), document.name(), document.description(), document.price())
                .id(document.id())
                .create(document.create())
                .details(document.details())
                .build();
    }
}
