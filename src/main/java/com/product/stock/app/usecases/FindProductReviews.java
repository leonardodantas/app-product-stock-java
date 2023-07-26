package com.product.stock.app.usecases;

import com.product.stock.app.rest.IFindReviewRest;
import com.product.stock.domain.ProductReview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class FindProductReviews {

    private static final Logger logger = LoggerFactory.getLogger(FindProductReviews.class);
    private final IFindReviewRest findReviewRest;

    public FindProductReviews(final IFindReviewRest findReviewRest) {
        this.findReviewRest = findReviewRest;
    }

    public Page<ProductReview> findReviewsByProductId(final String productId) {
        logger.info("Execute useCase FindProductReviews with productId: {}", productId);
        return findReviewRest.findByProductId(productId);
    }
}
