package com.automation.utils;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This class is used for retrieving data from specified URLs
 * e.g. JSONs
 */
public class HttpRequest {

    private JSONArray jsonArray;

    public HttpRequest(String url) {
        jsonArray = getJSONArray(url);
    }

    /**
     * Retrieves JSON from a specified URL
     * @param url full URL to JSON
     * @return JSONArray object
     */
    private JSONArray getJSONArray(String url) {
        JSONArray jsonArray = null;
        try {
            JsonNode jsonBody = Unirest.get(url).asJson().getBody();
            jsonArray = jsonBody.getArray();
        } catch (UnirestException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return jsonArray;
    }

    public String getEmail(int index) {
        JSONObject jsonObject = jsonArray.getJSONObject(index);
        String email = jsonObject.getString("email");
        return email;
    }
}
