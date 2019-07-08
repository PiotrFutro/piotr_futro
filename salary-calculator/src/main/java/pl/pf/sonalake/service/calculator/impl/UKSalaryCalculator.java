package pl.pf.sonalake.service.calculator.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.pf.sonalake.api.model.dict.CountryCode;

/**
 * Calculator dla UK
 *
 * @author pfutro
 */
@Component
public class UKSalaryCalculator extends SalaryCalculator {

    public UKSalaryCalculator(@Value("${month.days}") Integer daysInMonth) {
        super(daysInMonth, CountryCode.UK);
    }

}
