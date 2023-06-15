package com.product.stock.app.repositories;

import com.product.stock.domain.Product;

import java.util.Optional;

public interface IProductRepository {
    Product save(final Product product);

    Optional<Product> findByCode(final String code);

    Optional<Product> findById(final String id);
}
