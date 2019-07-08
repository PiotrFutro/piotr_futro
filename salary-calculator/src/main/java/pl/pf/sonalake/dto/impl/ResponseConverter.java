package pl.pf.sonalake.dto.impl;

import org.springframework.stereotype.Component;
import pl.pf.sonalake.api.model.response.SalaryCalculatorResponse;
import pl.pf.sonalake.dto.IResponseConverter;

import java.math.BigDecimal;

/**
 * Klasa konwertera do obiektu modelu odpowiedzi
 *
 * @author pfutro
 */
@Component
public class ResponseConverter implements IResponseConverter {
    @Override
    public SalaryCalculatorResponse convert(BigDecimal monthlySalary, BigDecimal mid) {

        SalaryCalculatorResponse salaryCalculatorResponse = new SalaryCalculatorResponse();
        salaryCalculatorResponse.setMonthlyNettoPLN(monthlySalary);
        salaryCalculatorResponse.setExchangeRate(mid);

        return salaryCalculatorResponse;
    }
}
