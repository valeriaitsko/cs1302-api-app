package cs1302.api;

import java.util.Map;

public class CountryResponse extends Response {
    Name name;
    Map<String, Currency> currencies;
    String[] capital;
    String region;
    Map<String, String> languages;
    String[] timezones;
    int population;
    Flag flags;

    public String toString() {
        String commonName = Objects.toString(name.common, "");
        String officialName = this.checkIfStringEmpty(name.official);
        String currName = this.checkIfStringEmpty(currencies.name);
        String currSymbol = this.checkIfStringEmpty(currencies.symbol);
        String region = this.checkIfStringEmpty(this.region);
        String languages =
    }
}
