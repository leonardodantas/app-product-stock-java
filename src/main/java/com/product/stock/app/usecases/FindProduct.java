package com.product.stock.app.usecases;

import com.product.stock.app.repositories.IProductRepository;
import com.product.stock.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindProduct {

    private static final Logger logger = LoggerFactory.getLogger(FindProduct.class);
    private final IProductRepository productRepository;

    public FindProduct(final IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> findByCode(final String code) {
        logger.info("Execute userCase findProduct by code");
        return productRepository.findByCode(code);
    }

    public Optional<Product> findById(final String id) {
        logger.info("Execute userCase findProduct by id");
        return productRepository.findById(id);
    }
}
