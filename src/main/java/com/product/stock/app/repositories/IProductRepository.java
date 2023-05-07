package com.product.stock.app.repositories;

import com.product.stock.domain.Product;

public interface IProductRepository {
    Product save(final Product product);
}
