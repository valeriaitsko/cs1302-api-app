package cs1302.api;

import com.google.gson.annotations.SerializedName;
import cs1302.api.Response;

public class BreweryResponse extends Response {
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
        String name = this.checkIfStringEmpty(this.name);
        String address = this.checkIfStringEmpty(this.address1);
        String stateProvince = this.checkIfStringEmpty(this.stateProvince);
        String phone = this.checkIfStringEmpty(this.phone);
        String websiteUrl = this.checkIfStringEmpty(this.websiteUrl);
        String rtn = name + '\n' + address + '\n' + stateProvince + '\n' + phone + '\n' +
            websiteUrl;
        rtn.trim();
        return rtn;
    }



}
