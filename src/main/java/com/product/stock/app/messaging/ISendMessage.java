package com.product.stock.app.messaging;

import com.product.stock.domain.Product;

public interface ISendMessage {

    void sendMessage(final Product product);
}
