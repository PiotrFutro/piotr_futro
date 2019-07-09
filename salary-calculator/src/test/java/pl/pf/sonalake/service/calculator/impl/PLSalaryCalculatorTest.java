package pl.pf.sonalake.service.calculator.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.pf.sonalake.api.model.dict.CountryCode;
import pl.pf.sonalake.service.calculator.ISalaryCalculator;

import java.math.BigDecimal;

/**
 * Testy metod klasy  PLSalaryCalculator
 *
 * @author pfutro
 */
public class PLSalaryCalculatorTest {

    private ISalaryCalculator plSalaryCalculator;

    @ParameterizedTest
    @CsvSource(value = {
            "100, 1.00, 810.00",
            "200, 1.00, 2592.00",
    })
    public void calculate(BigDecimal dailySalaryBrutto, BigDecimal exchangeRate,  BigDecimal expectdMonthlySalaryNettoPLN) {
        //given
        plSalaryCalculator = new PLSalaryCalculator(22);

        //when
        BigDecimal calculatedMonthlySalaryNettoPLN = plSalaryCalculator.calculate(dailySalaryBrutto, exchangeRate);

        //then
        Assertions.assertThat(calculatedMonthlySalaryNettoPLN).isNotNull();
        Assertions.assertThat(calculatedMonthlySalaryNettoPLN).isEqualTo(expectdMonthlySalaryNettoPLN);
    }

    @Test
    public void isGivenCountry() {
        //given
        plSalaryCalculator = new PLSalaryCalculator(22);

        //when
        Boolean givenCountry = plSalaryCalculator.isGivenCountry(CountryCode.PL);

        //then
        Assertions.assertThat(givenCountry).isTrue();
    }

}