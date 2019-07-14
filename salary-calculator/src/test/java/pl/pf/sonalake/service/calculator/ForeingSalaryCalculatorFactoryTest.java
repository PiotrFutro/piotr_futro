package pl.pf.sonalake.service.calculator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.pf.sonalake.api.model.dict.CountryData;

import java.util.Set;

/**
 * Klasa testów metody klasy fabrykującej kalkulatory
 *
 * @author pfutro
 */
public class ForeingSalaryCalculatorFactoryTest {

    private  ForeingSalaryCalculatorFactory foreingSalaryCalculatorFactory;

    @BeforeEach
    public void setUp() {
        foreingSalaryCalculatorFactory = new ForeingSalaryCalculatorFactory(22);
    }

    /**
     * Metoda sprawdza liczbę kalkulatorów. Powinna być zgodna z liczą obiektów enum Countrydata
     */
    @Test
    public void should_generate_foreignSalaryCountryBeans() {
        //when
        Set<ISalaryCalculator> salaryCalculators = foreingSalaryCalculatorFactory.foreignSalaryCalculatorBeans();

        //then
        Assertions.assertThat(salaryCalculators).isNotNull();
        Assertions.assertThat(salaryCalculators).hasSize(CountryData.values().length);

    }
}