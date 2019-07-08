package pl.pf.sonalake.api.model.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Klasa parametrow request
 *
 * @author pfutro
 */
public class SalaryCalculatorParams {

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
