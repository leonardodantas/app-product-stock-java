package com.product.stock.infra.database;

import com.product.stock.app.repositories.IProductRepository;
import com.product.stock.domain.Product;
import com.product.stock.infra.database.converters.ProductDocumentConverter;
import com.product.stock.infra.database.documents.ProductDocument;
import com.product.stock.infra.database.jpa.ProductRepositoryJPA;
import com.product.stock.infra.exceptions.SaveObjectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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

    @Override
    public Optional<Product> findByCode(final String code) {
        logger.info("Execute method find by with {} in repository ProductRepository", code);
        final var document = productRepositoryJPA.findByCode(code);
        return getOptionalProduct(document);
    }

    @Override
    public Optional<Product> findById(final String id) {
        logger.info("Execute method find by with {} in repository ProductRepository", id);
        final var document = productRepositoryJPA.findById(id);
        return getOptionalProduct(document);
    }

    @Override
    public Page<Product> findAll(final int page, final int size) {
        logger.info("Execute method find all with page {} and size {} in repository ProductRepository", page, size);
        return productRepositoryJPA.findAll(PageRequest.of(page, size))
                .map(productDocumentConverter::convert);
    }

    @Override
    public void delete(final String id) {
        productRepositoryJPA.deleteById(id);
    }

    private Optional<Product> getOptionalProduct(final Optional<ProductDocument> document) {
        return document.map(d -> {
            final var product = productDocumentConverter.convert(d);
            return Optional.ofNullable(product);
        }).orElse(Optional.empty());
    }
}
