package pl.pf.sonalake.api.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Klasa odpowiedzi serwisu
 *
 * @author pfutro
 */
@ApiModel(description = "Klasa odpowiedzi kalklulatora")
public class SalaryCalculatorResponse {
    @ApiModelProperty(value = "Wynagrodzenie miesięczne netto w PLN")
    private BigDecimal monthlyNettoPLN;

    @ApiModelProperty(value = "Sredni kurs NBP waluty do PLN przyjęty do pzeliczenia wynagrodzenia")
    private BigDecimal exchangeRate;

    public BigDecimal getMonthlyNettoPLN() {
        return monthlyNettoPLN;
    }

    public void setMonthlyNettoPLN(BigDecimal monthlyNettoPLN) {
        this.monthlyNettoPLN = monthlyNettoPLN;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public String toString() {
        return "SalaryCalculatorResponse{" +
                "monthlyNettoPLN=" + monthlyNettoPLN +
                ", exchangeRate=" + exchangeRate +
                '}';
    }
}
