package com.product.stock.infra.database.jpa;

import com.product.stock.infra.database.documents.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepositoryJPA extends MongoRepository<ProductDocument, String> {
    Optional<ProductDocument> findByCode(final String code);
}
