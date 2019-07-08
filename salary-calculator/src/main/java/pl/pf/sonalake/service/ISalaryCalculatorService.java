package pl.pf.sonalake.service;

import pl.pf.sonalake.api.model.request.SalaryCalculatorParams;
import pl.pf.sonalake.api.model.response.SalaryCalculatorResponse;
import pl.pf.sonalake.api.model.dict.CountryCode;

/**
 *  Serwis usługi
 *
 * @author pfutro
 */
public interface ISalaryCalculatorService {

     SalaryCalculatorResponse getPolishSalary(CountryCode countryCode, SalaryCalculatorParams salaryCalculatorParams);
}
