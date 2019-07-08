package pl.pf.sonalake.io.repository.dict;

/**
 * Typ tabeli kursowej
 *
 * @author pfutro
 */
public enum RateTableType {
    TABLE_A("a"), TABLE_B("b"), TABLE_C("c");

    private String type;
    RateTableType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
