package pl.pf.sonalake.service.calculator.impl;

import pl.pf.sonalake.api.model.dict.CountryCode;

/**
 * Klasa kalkulatora wynagrodzenia
 *
 * @author pfutro
 */
public class ForeignSalaryCalculator extends SalaryCalculator {
    public ForeignSalaryCalculator(Integer daysInMonth, CountryCode countryCode) {
        super(daysInMonth, countryCode);
    }
}
