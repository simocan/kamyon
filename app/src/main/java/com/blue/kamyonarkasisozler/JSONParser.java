package com.blue.kamyonarkasisozler;

import android.os.StrictMode;



import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;


public class JSONParser {

    static InputStream iStream = null;
    static JSONArray jarray = null;
    static String json = "";
    ArrayList<String> jsonList = new ArrayList<String>();


    private static final String JSON_URL = "http://blueyazilim.com/admin/index.php";

    // constructor
    public JSONParser() {

    }

    public ArrayList getJSONFromUrl() throws MalformedURLException {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(JSON_URL);
        try {
            HttpResponse response = client.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } // Parse String to JSON object

        try {

            jarray = new JSONArray(builder.toString());

            for (int i = 0; i < jarray.length(); i++) {
                JSONObject object = jarray.getJSONObject(i);
                jsonList.add(object.getString("aciklama"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        } // return JSON Object return jarray;

        return jsonList;
    }
}