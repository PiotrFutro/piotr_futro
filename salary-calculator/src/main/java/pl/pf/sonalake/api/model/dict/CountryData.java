package pl.pf.sonalake.api.model.dict;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @author pfutro
 *
 * - UK: 25%, 600 GBP
 * - DE: 20%, 800 EUR
 * - PL: 19%, 1200 PLN
 */
public enum CountryData {

    PL_DATA(CountryCode.PL, 19, new BigDecimal(1200), CurrencyCode.PLN),
    UK_DATA(CountryCode.UK, 25, new BigDecimal(600), CurrencyCode.GBP),
    DE_DATA(CountryCode.DE, 20, new BigDecimal(800), CurrencyCode.EUR);

    private CountryCode countryCode;
    private Integer tax;
    private BigDecimal fixedCosts;
    private CurrencyCode currencyCode;

    CountryData(CountryCode countryCode, Integer tax, BigDecimal fixedCosts, CurrencyCode currencyCode) {
        this.countryCode = countryCode;
        this.tax = tax;
        this.fixedCosts = fixedCosts;
        this.currencyCode = currencyCode;
    }

    public CountryCode getCountryCode() {
        return countryCode;
    }

    public Integer getTax() {
        return tax;
    }

    public BigDecimal getFixedCosts() {
        return fixedCosts;
    }

    public CurrencyCode getCurrencyCode() {
        return currencyCode;
    }

    public static CountryData forCountryCode(CountryCode countryCode) {
        return Arrays.stream(CountryData.values())
                .filter(en -> en.getCountryCode().equals(countryCode))
                .findFirst()
                .orElseGet(null);
    }

}
