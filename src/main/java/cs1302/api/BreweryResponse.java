package cs1302.api;
import com.google.gson.annotations.SerializedName;

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
}
