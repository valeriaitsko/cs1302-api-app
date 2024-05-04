package cs1302.api;

import java.util.Map;
import java.util.Objects;

import cs1302.api.Name;
import cs1302.api.Currency;
import cs1302.api.Flag;

/**
 * Represents the country response from the countries api.
 */
public class CountryResponse {
    Name name;
    Map<String, Currency> currencies;
    String[] capital;
    String region;
    Map<String, String> languages;
    String[] timezones;
    int population;
    Flag flags;

    @Override
    public String toString() {
        String allCurrencies = "";
        for (Map.Entry currency: currencies.entrySet()) {
            allCurrencies += "Currency Name: " + currency.getKey() + '\n' +
                currency.getValue();
        } // for
        String allCapitals = "Capital: ";
        String separator = "";
        for (String cap: capital) {
            allCapitals += separator + Objects.toString(cap, "");
            separator = ", ";
        } // for
        String allLanguages = "Languages: ";
        separator = "";
        for (Map.Entry language: languages.entrySet()) {
            allLanguages += separator + language.getValue();
            separator = ", ";
        } // for
        String allTimezones = "Timezones: ";
        separator = "";
        for (String timezone: timezones) {
            allTimezones += separator + Objects.toString(timezone, "");
            separator = ", \n";
        } // for
        return Objects.toString(name, "") + '\n' + allCurrencies + '\n' +
            allCapitals + '\n' + "Region: " + Objects.toString(region, "") +
            '\n' + allLanguages + '\n'  + allTimezones + '\n' + "Population: " + population;
    }
}
