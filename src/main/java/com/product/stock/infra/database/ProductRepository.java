package com.product.stock.infra.database;

import com.product.stock.app.repositories.IProductRepository;
import com.product.stock.domain.Product;
import com.product.stock.infra.database.converters.ProductEntityConverter;
import com.product.stock.infra.database.entities.ProductEntity;
import com.product.stock.infra.database.jpa.ProductRepositoryJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository implements IProductRepository {

    private static Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    private final ProductRepositoryJPA productRepositoryJPA;
    private final ProductEntityConverter productEntityConverter;

    public ProductRepository(final ProductRepositoryJPA productRepositoryJPA, final ProductEntityConverter productEntityConverter) {
        this.productRepositoryJPA = productRepositoryJPA;
        this.productEntityConverter = productEntityConverter;
    }

    @Override
    public Product save(final Product product) {
        logger.info("Execute method save in the repository ProductRepository");
        try {
            final var productSave = productRepositoryJPA.save(ProductEntity.from(product));
            return productEntityConverter.convert(productSave);
        } catch (final Exception e) {
            logger.error("Error to execute method save in repository ProductRepository");
            throw new RuntimeException(e);
        }

    }
}
