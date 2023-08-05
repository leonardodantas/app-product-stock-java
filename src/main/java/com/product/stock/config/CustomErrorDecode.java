package com.product.stock.config;

import com.product.stock.infra.exceptions.InternalServerFeignException;
import com.product.stock.infra.exceptions.ResponseFeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecode implements ErrorDecoder {

    @Override
    public Exception decode(final String methodKey, final Response response) {
        if (response.status() > 499) {
            throw new InternalServerFeignException(response.reason(), methodKey, response.status());
        }
        throw new ResponseFeignException(response.reason(), methodKey, response.status());
    }
}
