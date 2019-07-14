package pl.pf.sonalake.service.calculator.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.pf.sonalake.api.model.dict.CountryCode;
import pl.pf.sonalake.service.calculator.ISalaryCalculator;

import java.math.BigDecimal;

/**
 * Klasa testów metod klasy ${@link ForeignSalaryCalculator}
 *
 * @author pfutro
 */
public class ForeignSalaryCalculatorTest {

    private ISalaryCalculator foreignSalaryCalculator;

    @ParameterizedTest
    @CsvSource(value = {
            "DE, 100, 4.00, 4480.00",
            "DE,100, 5.00, 5600.00",
            "DE, 200, 4.50, 12960.00",
            "DE, 200.50, 4.50, 12999.60",
            "PL, 100, 1.00, 810.00",
            "PL, 200, 1.00, 2592.00",
            "UK, 100, 4.00, 4800.00",
            "UK, 100, 5.00, 6000.00",
            "UK, 200, 4.50, 12825.00",
            "UK, 200.50, 4.50, 12862.12"
    })
    public void calculate(String countryCode, BigDecimal dailySalaryBrutto, BigDecimal exchangeRate, BigDecimal expectdMonthlySalaryNettoPLN) {
        //given
        foreignSalaryCalculator  = new ForeignSalaryCalculator(22, CountryCode.valueOf(countryCode));

        //when
        BigDecimal calculatedMonthlySalaryNettoPLN = foreignSalaryCalculator.calculate(dailySalaryBrutto, exchangeRate);

        //then
        Assertions.assertThat(calculatedMonthlySalaryNettoPLN).isNotNull();
        Assertions.assertThat(calculatedMonthlySalaryNettoPLN).isEqualTo(expectdMonthlySalaryNettoPLN);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "DE",
            "PL",
            "UK"
    })
    public void isGivenCountry(String countryCode) {
        //given
        foreignSalaryCalculator  = new ForeignSalaryCalculator(22, CountryCode.valueOf(countryCode));

        //when
        Boolean givenCountry = foreignSalaryCalculator.isGivenCountry(CountryCode.valueOf(countryCode));

        //then
        Assertions.assertThat(givenCountry).isTrue();
    }



}