package com.product.stock.config.feign;

import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class FeignConfig implements RequestInterceptor {

    private static final String UUID_HEADER = "UUID";
    private static final String APP_CODE_HEADER = "APP-CODE";

    private final String appCodeHeader;

    public FeignConfig(@Value("${application.code.header}") final String appCodeHeader) {
        this.appCodeHeader = appCodeHeader;
    }

    @Override
    public void apply(final RequestTemplate requestTemplate) {
        requestTemplate.header(APP_CODE_HEADER, appCodeHeader);
        requestTemplate.header(UUID_HEADER, UUID.randomUUID().toString());
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecode();
    }
}
