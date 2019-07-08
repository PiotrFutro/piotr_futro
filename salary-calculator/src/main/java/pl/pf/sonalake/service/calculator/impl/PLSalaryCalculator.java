package pl.pf.sonalake.service.calculator.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.pf.sonalake.api.model.dict.CountryCode;

/**
 * Calculator dla PL
 *
 * @author pfutro
 */
@Component
public class PLSalaryCalculator extends SalaryCalculator {

    public PLSalaryCalculator(@Value("${month.days}") Integer daysInMonth) {
        super(daysInMonth, CountryCode.PL);
    }
}
