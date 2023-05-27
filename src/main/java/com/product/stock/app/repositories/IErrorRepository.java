package com.product.stock.app.repositories;

import com.product.stock.domain.Error;

public interface IErrorRepository {
    Error save(final Error error);
}
