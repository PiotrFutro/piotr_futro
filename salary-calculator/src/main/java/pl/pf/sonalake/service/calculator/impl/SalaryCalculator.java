package pl.pf.sonalake.service.calculator.impl;

import pl.pf.sonalake.api.model.dict.CountryCode;
import pl.pf.sonalake.api.model.dict.CountryData;
import pl.pf.sonalake.service.calculator.ISalaryCalculator;

import java.math.BigDecimal;

/**
 * Klasa abstakcyjna z metodami wspólnymi dla kalklulatorów
 *
 * @author pfutro
 */
public abstract class SalaryCalculator implements ISalaryCalculator {

    private final CountryCode countryCode;
    private final Integer  daysInMonth;
    private final BigDecimal constCosts;
    private final Integer tax;

    public SalaryCalculator(Integer daysInMonth, CountryCode countryCode) {
        this.countryCode = countryCode;
        this.daysInMonth = daysInMonth;
        this.constCosts = CountryData.forCountryCode(countryCode).getFixedCosts();
        this.tax = CountryData.forCountryCode(countryCode).getTax();
    }

    @Override
    public Boolean isGivenCountry(CountryCode countryCode) {
       return this.countryCode.equals(countryCode);
    }

    @Override
    public BigDecimal calculate(BigDecimal dailyBrutto, BigDecimal exchangeRate ) {
        return dailyBrutto
                .multiply(BigDecimal.valueOf(daysInMonth))
                .subtract(constCosts)
                .multiply(BigDecimal.valueOf(100 - tax).divide(new BigDecimal(100)))
                .multiply(exchangeRate);
    }
}
