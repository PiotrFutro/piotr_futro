package pl.pf.sonalake.io.repository.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.pf.sonalake.api.model.dict.CurrencyCode;
import pl.pf.sonalake.io.repository.dict.RateTableType;
import pl.pf.sonalake.io.entity.CurrencyEntity;
import pl.pf.sonalake.io.repository.INbpRepository;

import java.util.Optional;

/**
 * Klasa repozytorium danych z NBP
 *
 * @author pfutro
 */
@Repository
public class NbpRepository  implements INbpRepository {

    private final RestTemplate restTemplate;
    private String urlSingleCurrency;

    public NbpRepository(RestTemplate restTemplate,  @Value("${nbp.address.single.currency}") String urlSingleCurrency) {
        this.restTemplate = restTemplate;
        this.urlSingleCurrency = urlSingleCurrency;
    }


    public Optional<CurrencyEntity> getCurrentExchangeRate(CurrencyCode currencyCode){
        UriComponentsBuilder uri = UriComponentsBuilder.fromUriString(urlSingleCurrency).pathSegment(RateTableType.TABLE_A.getType()).pathSegment(currencyCode.name());

        return Optional.ofNullable(restTemplate.getForEntity(uri.build().toUri(), CurrencyEntity.class).getBody());
    };

}
