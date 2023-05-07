package com.product.stock.infra.database.jpa;

import com.product.stock.infra.database.entities.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepositoryJPA extends MongoRepository<ProductEntity, String> {
}
