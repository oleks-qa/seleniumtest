package com.automation.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

public class Json {

    public JSONObject jsonObj;

    public Json(String fileName) {
        File file = new File(fileName);
        String jsonString = "";
        try {
             jsonString = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        jsonObj = new JSONObject(jsonString);
    }

    public String getString(String key) {
        return jsonObj.getString(key);
    }

    public boolean getBoolean(String key) {
        return jsonObj.getBoolean(key);
    }

    public int[] getArray(String key) {
        JSONArray jsonArray = jsonObj.getJSONArray(key);
        int[] intArray = new int[jsonArray.length()];
        for (int i=0; i < jsonArray.length(); i++) {
            intArray[i] = jsonArray.getInt(i);
        }
        return intArray;
    }

}
