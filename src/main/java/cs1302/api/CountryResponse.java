package cs1302.api;

import java.util.Map;

public class CountryResponse {
    Name name;
    Map<String, Currency> currencies;
    String[] capital;
    String region;
    Map<String, String> languages;
    String[] timezones;
    Flag flags;

}
