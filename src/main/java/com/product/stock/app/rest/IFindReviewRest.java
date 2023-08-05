package com.product.stock.app.rest;

import com.product.stock.domain.ProductReview;

public interface IFindReviewRest {

    ProductReview findByProductId(final String productCode);
}
