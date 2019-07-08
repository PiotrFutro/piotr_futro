package pl.pf.sonalake.api.model.response;

import java.math.BigDecimal;

/**
 * Klasa odpowiedzi serwisu
 *
 * @author pfutro
 */
public class SalaryCalculatorResponse {
    private BigDecimal monthlyNettoPLN;
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
