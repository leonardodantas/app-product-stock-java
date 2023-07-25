package com.product.stock.app.usecases;

import com.product.stock.app.rest.IFindReviewRest;
import com.product.stock.domain.ProductReview;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class FindProductReviews {

    private final IFindReviewRest findReviewRest;

    public FindProductReviews(final IFindReviewRest findReviewRest) {
        this.findReviewRest = findReviewRest;
    }

    public Page<ProductReview> findByProductId(final String productId) {
        return findReviewRest.findByProductId(productId);
    }
}
