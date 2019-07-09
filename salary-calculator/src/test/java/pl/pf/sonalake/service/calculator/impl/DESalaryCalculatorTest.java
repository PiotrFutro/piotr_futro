package pl.pf.sonalake.service.calculator.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.pf.sonalake.api.model.dict.CountryCode;
import pl.pf.sonalake.service.calculator.ISalaryCalculator;

import java.math.BigDecimal;

/**
 * Testy mestod klasy  DESalaryCalculator
 *
 * @author pfutro
 */
public class DESalaryCalculatorTest {

    private ISalaryCalculator deSalaryCalculator;

    @ParameterizedTest
    @CsvSource(value = {
            "100, 4.00, 4480.00",
            "100, 5.00, 5600.00",
            "200, 4.50, 12960.00",
            "200.50, 4.50, 12999.60"
    })
    public void calculate(BigDecimal dailySalaryBrutto, BigDecimal exchangeRate,  BigDecimal expectdMonthlySalaryNettoPLN) {
        //given
        deSalaryCalculator = new DESalaryCalculator(22);

        //when
        BigDecimal calculatedMonthlySalaryNettoPLN = deSalaryCalculator.calculate(dailySalaryBrutto, exchangeRate);

        //then
        Assertions.assertThat(calculatedMonthlySalaryNettoPLN).isNotNull();
        Assertions.assertThat(calculatedMonthlySalaryNettoPLN).isEqualTo(expectdMonthlySalaryNettoPLN);
    }

    @Test
    public void isGivenCountry() {
        //given
        deSalaryCalculator = new DESalaryCalculator(22);

        //when
        Boolean givenCountry = deSalaryCalculator.isGivenCountry(CountryCode.DE);

        //then
        Assertions.assertThat(givenCountry).isTrue();
    }

}