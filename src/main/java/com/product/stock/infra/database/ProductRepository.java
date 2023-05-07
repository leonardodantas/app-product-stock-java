package com.product.stock.infra.database;

import com.product.stock.app.repositories.IProductRepository;
import com.product.stock.domain.Product;
import com.product.stock.infra.database.entities.ProductEntity;
import com.product.stock.infra.database.jpa.ProductRepositoryJPA;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository implements IProductRepository {

    private final ProductRepositoryJPA productRepositoryJPA;
    private final ProductEntityConverter productEntityConverter;

    public ProductRepository(final ProductRepositoryJPA productRepositoryJPA, final ProductEntityConverter productEntityConverter) {
        this.productRepositoryJPA = productRepositoryJPA;
        this.productEntityConverter = productEntityConverter;
    }

    @Override
    public Product save(final Product product) {
        final var productSave = productRepositoryJPA.save(ProductEntity.from(product));
        return productEntityConverter.convert(productSave);
    }
}
