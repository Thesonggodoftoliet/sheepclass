package com.sheepclass.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReciveUtils {
    public static JSONObject getObject(HttpServletRequest request) throws IOException {
        InputStream js = request.getInputStream();
        InputStreamReader jsr = new InputStreamReader(js);
        BufferedReader br = new BufferedReader(jsr);

        String json = br.readLine();
        System.out.println("有东西吗"+json);


        JSONObject Obj = null;

        try {
            Obj = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return Obj;
    }

    public static JSONArray getArray(HttpServletRequest request) throws IOException, JSONException {
        InputStream js = request.getInputStream();
        InputStreamReader jsr = new InputStreamReader(js);
        BufferedReader br = new BufferedReader(jsr);

        String json = br.readLine();

        System.out.println("Array"+json);

        JSONObject jsonObject;
        jsonObject = new JSONObject(json);


        JSONArray jsonArray = jsonObject.getJSONArray("images");

        return jsonArray;
    }

}
