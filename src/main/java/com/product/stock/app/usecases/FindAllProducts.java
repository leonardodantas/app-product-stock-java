package com.product.stock.app.usecases;

import com.product.stock.app.repositories.IProductRepository;
import com.product.stock.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class FindAllProducts {

    private static final Logger logger = LoggerFactory.getLogger(CreateProduct.class);
    private final IProductRepository productRepository;

    public FindAllProducts(final IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> execute(final int page, final int size) {
        logger.info("Execute useCase FindAllProducts");
        return productRepository.findAll(page, size);
    }
}
