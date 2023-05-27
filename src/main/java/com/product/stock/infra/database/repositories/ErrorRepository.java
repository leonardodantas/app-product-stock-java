package com.product.stock.infra.database.repositories;

import com.product.stock.app.repositories.IErrorRepository;
import com.product.stock.domain.Error;
import com.product.stock.infra.database.jpa.ErrorRepositoryJPA;
import org.springframework.stereotype.Repository;

@Repository
public class ErrorRepository implements IErrorRepository {

    private final ErrorRepositoryJPA errorRepositoryJPA;

    public ErrorRepository(final ErrorRepositoryJPA errorRepositoryJPA) {
        this.errorRepositoryJPA = errorRepositoryJPA;
    }

    @Override
    public Error save(final Error error) {
//        final var t =  errorRepositoryJPA.save(error);
        return null;
    }
}
