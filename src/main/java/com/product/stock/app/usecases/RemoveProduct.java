package com.product.stock.app.usecases;

import com.product.stock.app.exceptions.ProductNotFoundException;
import com.product.stock.app.repositories.IProductRepository;
import org.springframework.stereotype.Service;

@Service
public class RemoveProduct {

    private final IProductRepository productRepository;

    public RemoveProduct(final IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void removeByCode(final String code) {
        final var product = productRepository.findByCode(code)
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product with code {} not found", code)));
        productRepository.delete(product.id());
    }
}
