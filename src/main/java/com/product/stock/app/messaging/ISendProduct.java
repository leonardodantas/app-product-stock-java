package com.product.stock.app.messaging;

import com.product.stock.domain.Product;

public interface ISendProduct {

    void sendProduct(final Product product);
}
