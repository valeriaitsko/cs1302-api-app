package cs1302.api;

import java.util.Objects;

/**
 * Represents a name object from the countries api.
 */
public class Name {
    String common;
    String official;

    @Override
    public String toString() {
        return "Common Name: " + Objects.toString(this.common, "") + '\n' +
            "Official Name: " + Objects.toString(this.official, "");
    } // toString

}
