package com.product.stock.infra.database;

import com.product.stock.app.repositories.IProductRepository;
import com.product.stock.domain.Product;
import com.product.stock.infra.database.converters.ProductDocumentConverter;
import com.product.stock.infra.database.documents.ProductDocument;
import com.product.stock.infra.database.jpa.ProductRepositoryJPA;
import com.product.stock.infra.exceptions.SaveObjectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository implements IProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    private final ProductRepositoryJPA productRepositoryJPA;
    private final ProductDocumentConverter productDocumentConverter;

    public ProductRepository(final ProductRepositoryJPA productRepositoryJPA, final ProductDocumentConverter productDocumentConverter) {
        this.productRepositoryJPA = productRepositoryJPA;
        this.productDocumentConverter = productDocumentConverter;
    }

    @Override
    public Product save(final Product product) {
        logger.info("Execute method save in the repository ProductRepository");
        try {
            final var productSave = productRepositoryJPA.save(ProductDocument.from(product));
            return productDocumentConverter.convert(productSave);
        } catch (final Exception e) {
            logger.error("Error to execute method save in repository ProductRepository");
            throw new SaveObjectException(String.format("Error save product %s", product.name()), e);
        }

    }
}
