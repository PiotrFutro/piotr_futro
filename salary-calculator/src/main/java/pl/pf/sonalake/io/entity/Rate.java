package pl.pf.sonalake.io.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * {
 *  *             "no": "129/A/NBP/2019",
 *  *             "effectiveDate": "2019-07-05",
 *  *             "mid": 3.8165
 *  *         }
 */
public class Rate {
    private String no;
    private LocalDate effectiveDate;
    private BigDecimal mid;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public BigDecimal getMid() {
        return mid;
    }

    public void setMid(BigDecimal mid) {
        this.mid = mid;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "no='" + no + '\'' +
                ", effectiveDate=" + effectiveDate +
                ", mid=" + mid +
                '}';
    }
}
