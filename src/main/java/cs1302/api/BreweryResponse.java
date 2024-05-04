package cs1302.api;

import com.google.gson.annotations.SerializedName;
import java.util.Objects;

/**
 * Class that represents the BreweryResponse from the brewery api.
 */
public class BreweryResponse {
    String name;
    @SerializedName("address_1")
    String address1;
    @SerializedName("state_province")
    String stateProvince;
    String phone;
    String country;
    @SerializedName("website_url")
    String websiteUrl;

    @Override
    public String toString() {
        String name = Objects.toString(this.name, "");
        String address = Objects.toString(this.address1, "");
        String stateProvince = Objects.toString(this.stateProvince, "");
        String phone = Objects.toString(this.phone, "");
        String websiteUrl = Objects.toString(this.websiteUrl, "");
        String rtn = name + '\n' + address + '\n' + stateProvince + '\n' + phone + '\n' +
            websiteUrl;
        return rtn;
    }



}
