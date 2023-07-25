package com.product.stock.infra.feign.client;

import com.product.stock.infra.feign.jsons.ProductReviewFeignResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "${url.review}")
public interface IFindReviewFeign {

    @GetMapping("product/{productId}")
    ResponseEntity<Page<ProductReviewFeignResponse>> findByProductId(final String productId);
}
