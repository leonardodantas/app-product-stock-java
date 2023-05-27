package com.product.stock.app.usecases;

import com.product.stock.app.repositories.IErrorRepository;
import com.product.stock.domain.Error;
import org.springframework.stereotype.Service;

@Service
public class SaveMessageError {

    private final IErrorRepository errorRepository;

    public SaveMessageError(final IErrorRepository errorRepository) {
        this.errorRepository = errorRepository;
    }

    public Error execute(final Error error) {
        return errorRepository.save(error);
    }
}
