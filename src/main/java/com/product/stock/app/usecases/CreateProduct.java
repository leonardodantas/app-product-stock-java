package com.product.stock.app.usecases;

import com.product.stock.app.exceptions.DocumentAlreadyExistException;
import com.product.stock.app.repositories.IProductRepository;
import com.product.stock.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreateProduct {

    private static final Logger logger = LoggerFactory.getLogger(CreateProduct.class);
    private final IProductRepository productRepository;

    public CreateProduct(final IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product execute(final Product product) {
        logger.info("Execute useCase CreateProduct");
        productRepository.findByCode(product.code()).ifPresent(productExist -> {
            throw new DocumentAlreadyExistException(String.format("Produto com codigo %s já cadastrado", product.code()));
        });
        return productRepository.save(product.activeProduct());
    }
}
