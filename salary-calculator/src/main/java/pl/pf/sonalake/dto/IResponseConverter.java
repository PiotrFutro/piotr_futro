package pl.pf.sonalake.dto;

import pl.pf.sonalake.api.model.response.SalaryCalculatorResponse;

import java.math.BigDecimal;

/**
 * Interface konwertera do modelu odpowiedzi
 *
 * @author pfutro
 */
public interface IResponseConverter {
    SalaryCalculatorResponse convert(BigDecimal currencyEntity, BigDecimal mid);
}
