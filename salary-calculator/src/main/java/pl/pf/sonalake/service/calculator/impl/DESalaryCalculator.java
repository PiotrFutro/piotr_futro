package pl.pf.sonalake.service.calculator.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.pf.sonalake.api.model.dict.CountryCode;

/**
 * Calculator dla DE
 *
 * @author pfutro
 */
@Component
public class DESalaryCalculator extends SalaryCalculator {

    public DESalaryCalculator(@Value("${month.days}") Integer daysInMonth) {
        super(daysInMonth, CountryCode.DE);
    }

}
