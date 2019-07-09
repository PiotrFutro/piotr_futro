package pl.pf.sonalake.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.pf.sonalake.api.model.dict.CountryCode;
import pl.pf.sonalake.service.calculator.ISalaryCalculator;
import pl.pf.sonalake.service.calculator.impl.DESalaryCalculator;
import pl.pf.sonalake.service.calculator.impl.PLSalaryCalculator;
import pl.pf.sonalake.service.calculator.impl.UKSalaryCalculator;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Testy metod klasy SalaryCalculation
 *
 * @author pfutro
 */
public class SalaryCalculationTest {

    private static SalaryCalculation salaryCalculation;

    @BeforeAll
    static void  setUp() {
        Set<ISalaryCalculator> salaryCalculators = new HashSet<>();
        salaryCalculators.add(new PLSalaryCalculator(22));
        salaryCalculators.add(new UKSalaryCalculator(22));
        salaryCalculators.add(new DESalaryCalculator(22));
        salaryCalculation = new SalaryCalculation( salaryCalculators);
    }

    /**
     * Test metody, w której wybierany jest kalkulator właściwy dla podanego kodu kraju
     *
     * @param countryCode
     * @param dailySalaryBrutto
     * @param exchangeRate
     * @param expectdMonthlySalaryNettoPLN
     */
    @ParameterizedTest
    @CsvSource(value = {
            "PL, 100, 1.00, 810.00",
            "DE, 100, 4.00, 4480.00",
            "UK, 100, 4.00, 4800.00",
    })
    public void calculateSalary(String countryCode, BigDecimal dailySalaryBrutto, BigDecimal exchangeRate,  BigDecimal expectdMonthlySalaryNettoPLN) {
        //when
        BigDecimal calculatedMonthlySalaryNettoPLN = salaryCalculation.calculateSalary(CountryCode.valueOf(countryCode), dailySalaryBrutto, exchangeRate);

        //then
        Assertions.assertThat(calculatedMonthlySalaryNettoPLN).isNotNull();
        Assertions.assertThat(calculatedMonthlySalaryNettoPLN).isEqualTo(expectdMonthlySalaryNettoPLN);
    }
}