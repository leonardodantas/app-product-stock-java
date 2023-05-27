package com.product.stock.infra.database.jpa;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ErrorRepositoryJPA extends MongoRepository<Error, String> {
}
