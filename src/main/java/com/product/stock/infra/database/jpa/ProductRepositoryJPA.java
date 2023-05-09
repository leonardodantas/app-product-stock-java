package com.product.stock.infra.database.jpa;

import com.product.stock.infra.database.documents.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepositoryJPA extends MongoRepository<ProductDocument, String> {
}
