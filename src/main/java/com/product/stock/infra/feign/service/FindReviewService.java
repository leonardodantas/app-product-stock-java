package com.product.stock.infra.feign.service;

import com.product.stock.app.rest.IFindReviewRest;
import com.product.stock.domain.ProductReview;
import com.product.stock.infra.feign.client.IFindReviewFeign;
import com.product.stock.infra.feign.converter.ProductReviewFeignConverter;
import org.springframework.stereotype.Component;

@Component
public class FindReviewService implements IFindReviewRest {

    private final IFindReviewFeign findReviewFeign;
    private final ProductReviewFeignConverter productReviewFeignConverter;

    public FindReviewService(final IFindReviewFeign findReviewFeign, final ProductReviewFeignConverter productReviewFeignConverter) {
        this.findReviewFeign = findReviewFeign;
        this.productReviewFeignConverter = productReviewFeignConverter;
    }

    @Override
    public ProductReview findByProductId(final String productCode) {
        final var response = findReviewFeign.findByProductCode(productCode).getBody();
        return productReviewFeignConverter.convert(response);
    }
}
