package com.product.stock.app.usecases;

import com.product.stock.domain.ProductReview;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class FindProductReviews {

    public Page<ProductReview> findByProductId(final String productId) {
        return null;
    }
}
