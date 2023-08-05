package com.product.stock.app.usecases;

import com.product.stock.app.exceptions.ProductNotFoundException;
import com.product.stock.app.messaging.ISendProduct;
import com.product.stock.app.repositories.IProductRepository;
import com.product.stock.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UpdateProduct {

    private static final Logger logger = LoggerFactory.getLogger(UpdateProduct.class);
    private final IProductRepository productRepository;
    private final ISendProduct sendProduct;

    public UpdateProduct(final IProductRepository productRepository, final ISendProduct sendProduct) {
        this.productRepository = productRepository;
        this.sendProduct = sendProduct;
    }

    public Product execute(final Product product) {
        logger.info("Execute useCase UpdateProduct");
        final var productFound = productRepository.findByCode(product.code())
                .orElseThrow(() -> new ProductNotFoundException(String.format("Product with code %s not found", product.code())));
        final var productToUpdate = product.of(productFound.id(), product.create(), LocalDateTime.now());
        sendProduct.sendProduct(productToUpdate);
        return productRepository.save(productToUpdate);
    }
}
