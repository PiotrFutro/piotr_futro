package pl.pf.sonalake.service.calculator.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.pf.sonalake.api.model.dict.CountryCode;
import pl.pf.sonalake.service.calculator.ISalaryCalculator;

import java.math.BigDecimal;

/**
 * Testy mestod klasy  UKSalaryCalculator
 *
 * @author pfutro
 */
public class UKSalaryCalculatorTest {

    private ISalaryCalculator ukSalaryCalculator;

    @ParameterizedTest
    @CsvSource(value = {
            "100, 4.00, 4800.00",
            "100, 5.00, 6000.00",
            "200, 4.50, 12825.00",
            "200.50, 4.50, 12862.12"
    })
    public void calculate(BigDecimal dailySalaryBrutto, BigDecimal exchangeRate,  BigDecimal expectdMonthlySalaryNettoPLN) {
        //given
        ukSalaryCalculator = new UKSalaryCalculator(22);

        //when
        BigDecimal calculatedMonthlySalaryNettoPLN = ukSalaryCalculator.calculate(dailySalaryBrutto, exchangeRate);

        //then
        Assertions.assertThat(calculatedMonthlySalaryNettoPLN).isNotNull();
        Assertions.assertThat(calculatedMonthlySalaryNettoPLN).isEqualTo(expectdMonthlySalaryNettoPLN);
    }

    @Test
    public void isGivenCountry() {
        //given
        ukSalaryCalculator = new UKSalaryCalculator(22);

        //when
        Boolean givenCountry = ukSalaryCalculator.isGivenCountry(CountryCode.UK);

        //then
        Assertions.assertThat(givenCountry).isTrue();
    }

}