package pl.pf.sonalake.io.repository.impl;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import pl.pf.sonalake.api.model.dict.CurrencyCode;
import pl.pf.sonalake.io.entity.CurrencyEntity;
import pl.pf.sonalake.io.entity.Rate;
import pl.pf.sonalake.io.repository.dict.NbpRateTableType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Test metod klasy klienta API NBP ${@link NbpRepository}<br>.
 * z wykorzystaniem klasy MockRestServiceServer
 *
 * @author pfutro
 */
public class NbpRepositoryTest {

    private static final String EXCHANGE_RATE_BASE_URL = "/exchangerates/rates/";

    private NbpRepository nbpRepository;
    private MockRestServiceServer mockNbpServer;


    @BeforeEach
    void estUpBeforeEach(){
        RestTemplate restTemplate = new RestTemplate();
        nbpRepository = new NbpRepository(restTemplate, EXCHANGE_RATE_BASE_URL);
        mockNbpServer = MockRestServiceServer.createServer(restTemplate);
    }


    @Test
    public void should_getCurrentExchangeRate() throws JsonProcessingException {
        //given
        final BigDecimal expectedMid = new BigDecimal("4.23");

        CurrencyEntity expectedCurrencyEntity = new CurrencyEntity();
        Rate rate = new Rate();
        rate.setMid(expectedMid);
        List<Rate> rates = new ArrayList<>();
        rates.add(rate);
        expectedCurrencyEntity.setRates(rates);


        mockNbpServer.expect(requestTo(EXCHANGE_RATE_BASE_URL + NbpRateTableType.TABLE_A.getType() + "/" + CurrencyCode.EUR.name().toLowerCase())).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(new ObjectMapper().writeValueAsString(expectedCurrencyEntity), MediaType.APPLICATION_JSON));

        //when
         Optional<CurrencyEntity>  currencyEntity = nbpRepository.getCurrentExchangeRate(CurrencyCode.EUR);

        //then
        assertThat(new ObjectMapper().writeValueAsString(currencyEntity.get())).isEqualTo(new ObjectMapper().writeValueAsString(expectedCurrencyEntity));

    }
}