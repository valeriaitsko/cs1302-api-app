package cs1302.api;

/**
 * Represents a currency object from the countries api.
 */
public class Currency {
    String name;
    String symbol;

    @Override
    public String toString() {
        return "Name: " + name + '\n' + "Symbol: " + symbol;
    }
}
