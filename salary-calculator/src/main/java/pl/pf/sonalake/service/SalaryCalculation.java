package pl.pf.sonalake.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.pf.sonalake.api.model.dict.CountryCode;
import pl.pf.sonalake.service.calculator.ISalaryCalculator;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Komponent wybierający właściwy kalkulator dla kraju i uruchamiający kalkulacje
 *
 * @author pfutro
 */
@Component
public class SalaryCalculation {

    private final Set<ISalaryCalculator> iSalaryCalculators;

    @Autowired
    public SalaryCalculation(Set<ISalaryCalculator> salaryCalculators) {
        this.iSalaryCalculators = salaryCalculators;
    }

    public BigDecimal calculateSalary(CountryCode countryCode, BigDecimal dailyBrutto, BigDecimal exchangeRate) {
        return iSalaryCalculators.stream()
                .filter(calculator -> calculator.isGivenCountry(countryCode))
                .map(calculator -> calculator.calculate(dailyBrutto, exchangeRate))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No salary calculator for given country code."));
    }

}
