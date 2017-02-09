package com.test.ln.postapitask.model;

/**
 * Created by ln-300 on 9/2/17.
 */

public class RestaurantDetails {

    private String res_name;
    private String res_logo;
    private String res_loc_lat;
    private String res_loc_lng;

    public String getRes_name() {
        return res_name;
    }

    public void setRes_name(String res_name) {
        this.res_name = res_name;
    }

    public String getRes_logo() {
        return res_logo;
    }

    public void setRes_logo(String res_logo) {
        this.res_logo = res_logo;
    }

    public String getRes_loc_lat() {
        return res_loc_lat;
    }

    public void setRes_loc_lat(String res_loc_lat) {
        this.res_loc_lat = res_loc_lat;
    }

    public String getRes_loc_lng() {
        return res_loc_lng;
    }

    public void setRes_loc_lng(String res_loc_lng) {
        this.res_loc_lng = res_loc_lng;
    }
}
