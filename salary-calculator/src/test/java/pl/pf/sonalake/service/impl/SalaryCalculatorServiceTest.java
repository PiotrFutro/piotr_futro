package pl.pf.sonalake.service.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import pl.pf.sonalake.api.model.dict.CountryCode;
import pl.pf.sonalake.api.model.dict.CountryData;
import pl.pf.sonalake.api.model.dict.CurrencyCode;
import pl.pf.sonalake.api.model.request.SalaryCalculatorParams;
import pl.pf.sonalake.api.model.response.SalaryCalculatorResponse;
import pl.pf.sonalake.dto.IResponseConverter;
import pl.pf.sonalake.dto.impl.ResponseConverter;
import pl.pf.sonalake.io.entity.CurrencyEntity;
import pl.pf.sonalake.io.entity.Rate;
import pl.pf.sonalake.io.repository.impl.NbpRepository;
import pl.pf.sonalake.service.SalaryCalculation;
import pl.pf.sonalake.service.calculator.ISalaryCalculator;
import pl.pf.sonalake.service.calculator.impl.ForeignSalaryCalculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Test metody klasy Serwisu ${@link pl.pf.sonalake.service.calculator.impl.SalaryCalculator}
 *
 * @author pfutro
 */
public class SalaryCalculatorServiceTest {

    @Mock
    private NbpRepository nbpRepositoryMock;

    private SalaryCalculation salaryCalculation;

    private IResponseConverter responseConverter;

    private SalaryCalculatorService salaryCalculatorService;



    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        Set<ISalaryCalculator> salaryCalculators = Stream.of(CountryData.values())
                .map(cd -> cd.getCountryCode())
                .map(cc -> new ForeignSalaryCalculator(22, cc))
                .collect(Collectors.toSet());
        salaryCalculation = new SalaryCalculation(salaryCalculators);
        responseConverter = new ResponseConverter();
        salaryCalculatorService = new SalaryCalculatorService(salaryCalculation, nbpRepositoryMock, responseConverter);
    }


    @ParameterizedTest
    @CsvSource(value = {
            "PL, PLN, 100, 1, 810.00",
            "DE, EUR, 100, 4.00, 4480.00",
            "UK, GBP, 100, 4.00, 4800.00",
    })
    public void getPolishSalaryForForeinCurrency(String countryCode, String currencyCode, BigDecimal dailySalary, BigDecimal exchangeRate, BigDecimal expectedSalaryPLN) {

        //given
        CurrencyEntity currencyEntity = new CurrencyEntity();
        Rate rate = new Rate();
        rate.setMid(exchangeRate);
        List<Rate> rates = new ArrayList<>();
        rates.add(rate);
        currencyEntity.setRates(rates);

        SalaryCalculatorParams salaryCalculatorParams = new SalaryCalculatorParams();
        salaryCalculatorParams.setDailySalary(dailySalary);

        Mockito.when(nbpRepositoryMock.getCurrentExchangeRate(CurrencyCode.valueOf(currencyCode)))
                .thenReturn(Optional.of(currencyEntity));

        //when
        SalaryCalculatorResponse polishSalary = salaryCalculatorService.getPolishSalary(CountryCode.valueOf(countryCode), salaryCalculatorParams);

        //then
        Assertions.assertThat(polishSalary).isNotNull();
        Assertions.assertThat(polishSalary.getMonthlyNettoPLN()).isEqualTo(expectedSalaryPLN);
        Assertions.assertThat(polishSalary.getExchangeRate()).isEqualTo(exchangeRate);

    }
}