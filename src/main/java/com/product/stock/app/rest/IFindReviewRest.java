package com.product.stock.app.rest;

import com.product.stock.domain.ProductReview;
import org.springframework.data.domain.Page;

public interface IFindReviewRest {

    Page<ProductReview> findByProductId(final String productId);
}
