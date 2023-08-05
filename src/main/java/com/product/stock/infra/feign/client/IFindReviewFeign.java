package com.product.stock.infra.feign.client;

import com.product.stock.config.feign.FeignConfig;
import com.product.stock.infra.feign.jsons.ProductReviewFeignResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "FindReviewFeign", url = "${url.review}", configuration = FeignConfig.class)
public interface IFindReviewFeign {

    @GetMapping("product/{productCode}")
    @Headers("Content-Type: application/json")
    ResponseEntity<ProductReviewFeignResponse> findByProductCode(@PathVariable final String productCode);
}
