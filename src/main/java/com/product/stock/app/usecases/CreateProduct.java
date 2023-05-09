package com.product.stock.app.usecases;

import com.product.stock.app.repositories.IProductRepository;
import com.product.stock.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreateProduct {

    private static Logger logger = LoggerFactory.getLogger(CreateProduct.class);

    private final IProductRepository productRepository;

    public CreateProduct(final IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product execute(final Product product) {
        logger.info("Execute useCase CreateProduct");
        return productRepository.save(product);
    }
}
