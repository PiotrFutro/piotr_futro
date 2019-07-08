package pl.pf.sonalake.service.calculator;

import pl.pf.sonalake.api.model.dict.CountryCode;

import java.math.BigDecimal;

public interface ISalaryCalculator {

    default Boolean isGivenCountry(CountryCode countryCode) {return true;};
    BigDecimal calculate(BigDecimal dailyBrutto, BigDecimal exchangeRate);

}
