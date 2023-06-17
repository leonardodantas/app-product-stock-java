package com.product.stock.app.repositories;

import com.product.stock.domain.Product;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface IProductRepository {
    Product save(final Product product);

    Optional<Product> findByCode(final String code);

    Optional<Product> findById(final String id);

    Page<Product> findAll(final int page, final int size);

    void delete(final String id);
}
