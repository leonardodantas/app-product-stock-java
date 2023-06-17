package com.product.stock.infra.database.jpa;

import com.product.stock.infra.database.documents.ProductDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepositoryJPA extends MongoRepository<ProductDocument, String> {
    Optional<ProductDocument> findByCodeAndActiveTrue(final String code);

    Page<ProductDocument> findAllByActiveTrue(final Pageable pageRequest);

    Optional<ProductDocument> findByIdAndActiveTrue(final String id);
}
