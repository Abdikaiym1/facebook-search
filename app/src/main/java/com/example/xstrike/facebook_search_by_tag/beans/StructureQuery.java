package com.example.xstrike.facebook_search_by_tag.beans;

/**
 * Created by XStrike on 07.03.2018.
 */

public class StructureQuery  {
    private String latitude;
    private String longitude;
    private String tag;
    private String distance;

    public StructureQuery(String latitude, String longitude, String tag, String distance) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.tag = tag;
        this.distance = distance;
    }

    public StructureQuery() {
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
