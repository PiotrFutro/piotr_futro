package pl.pf.sonalake.api.model.request;

import io.swagger.annotations.ApiParam;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Klasa parametrow request
 *
 * @author pfutro
 */
public class SalaryCalculatorParams {

    @ApiParam(value = "Wynagrodzenie dzienne brutto", required = true)
    @NotNull
    private BigDecimal dailySalary;

    public BigDecimal getDailySalary() {
        return dailySalary;
    }

    public void setDailySalary(BigDecimal dailySalary) {

        if (BigDecimal.ZERO.compareTo(dailySalary) >= 0  ){
            throw new IllegalArgumentException("Daily salary wrong value.");
        }
        this.dailySalary = dailySalary;
    }

    @Override
    public String toString() {
        return "SalaryCalculatorParams{" +
                "dailySalary=" + dailySalary +
                '}';
    }
}
