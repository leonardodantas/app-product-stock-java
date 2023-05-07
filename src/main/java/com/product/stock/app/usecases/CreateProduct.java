package com.product.stock.app.usecases;

import com.product.stock.app.repositories.IProductRepository;
import com.product.stock.domain.Product;
import org.springframework.stereotype.Service;

@Service
public class CreateProduct {

    private final IProductRepository productRepository;

    public CreateProduct(final IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product execute(final Product product){
        return productRepository.save(product);
    }
}
