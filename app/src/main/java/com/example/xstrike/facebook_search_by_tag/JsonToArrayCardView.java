package com.example.xstrike.facebook_search_by_tag;

import android.util.Log;

import com.example.xstrike.facebook_search_by_tag.beans.DateOfPlace;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonToArrayCardView {
    private List<DateOfPlace> datesOfPlace;
    private JSONArray jsonArray;

    public JsonToArrayCardView(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }


    public List<DateOfPlace> parseJsonArray() throws JSONException {
        datesOfPlace = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject allJsonObject = jsonArray.getJSONObject(i);
            DateOfPlace dateOfPlace = new DateOfPlace();
            dateOfPlace.setAbout(allJsonObject.optString("about"));
            dateOfPlace.setLink(allJsonObject.optString("link"));
            dateOfPlace.setPhone(allJsonObject.optString("phone"));
            dateOfPlace.setId(allJsonObject.optString("id"));
            dateOfPlace.setName(allJsonObject.optString("name"));
            dateOfPlace.setRating_cont(allJsonObject.optString("rating_count"));


            JSONObject locationJsonObject = allJsonObject.getJSONObject("location");
            dateOfPlace.setCity(locationJsonObject.optString("city"));
            dateOfPlace.setCountry(locationJsonObject.optString("country"));
            dateOfPlace.setLatitude(locationJsonObject.optString("latitude"));
            dateOfPlace.setLongitude(locationJsonObject.optString("longitude"));
            dateOfPlace.setStreet(locationJsonObject.optString("street"));

            JSONObject pictureJsonObject = allJsonObject.getJSONObject("picture");
            JSONObject datePictureJsonObject = pictureJsonObject.getJSONObject("data");
            dateOfPlace.setPictureURL(datePictureJsonObject.optString("url"));
            datesOfPlace.add(dateOfPlace);
        }

        return datesOfPlace;
    }

}
