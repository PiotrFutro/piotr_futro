package pl.pf.sonalake.io.entity;

import java.util.List;

/**
 * klasa odpowiedzi z NBP
 * {
 *     "table": "A",
 *     "currency": "frank szwajcarski",
 *     "code": "CHF",
 *     "rates": [
 *         {
 *             "no": "129/A/NBP/2019",
 *             "effectiveDate": "2019-07-05",
 *             "mid": 3.8165
 *         }
 *     ]
 * }
 *
 * @author pfutro
 */
public class CurrencyEntity {
    private String table;
    private String currency;
    private String code;
    List<Rate> rates;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "CurrencyEntity{" +
                "table='" + table + '\'' +
                ", currency='" + currency + '\'' +
                ", code='" + code + '\'' +
                ", rates=" + rates +
                '}';
    }
}
