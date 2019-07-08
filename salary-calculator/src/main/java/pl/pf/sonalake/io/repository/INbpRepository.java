package pl.pf.sonalake.io.repository;

import pl.pf.sonalake.api.model.dict.CurrencyCode;
import pl.pf.sonalake.io.entity.CurrencyEntity;

import java.util.Optional;

/**
 * Interface klasy repozytorium kursów NBP
 * @author pfutro
 */
public interface INbpRepository {

    Optional<CurrencyEntity> getCurrentExchangeRate(CurrencyCode currencyCode);
}
