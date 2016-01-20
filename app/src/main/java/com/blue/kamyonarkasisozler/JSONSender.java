package com.blue.kamyonarkasisozler;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Admin on 10.1.2016.
 */
public class JSONSender {

 public String sendData(String aciklama) throws MalformedURLException,Exception {

     URL url;
     String response = "";
     try {
         url = new URL("http://www.blueyazilim.com/admin/ekle.php");

         HttpURLConnection conn = (HttpURLConnection) url.openConnection();
         conn.setReadTimeout(15000);
         conn.setConnectTimeout(15000);
         conn.setRequestMethod("POST");
         conn.setDoInput(true);
         conn.setDoOutput(true);


         OutputStream os = conn.getOutputStream();
         BufferedWriter writer = new BufferedWriter(
                 new OutputStreamWriter(os, "UTF-8"));
         writer.write(getPostDataString(aciklama));

         writer.flush();
         writer.close();
         os.close();
         int responseCode=conn.getResponseCode();

         if (responseCode == HttpsURLConnection.HTTP_OK) {
             String line;
             BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
             while ((line=br.readLine()) != null) {
                 response+=line;
             }
         }
         else {
             response="";

         }
     } catch (Exception e) {
         e.printStackTrace();
     }

     return response;


 }

    private String getPostDataString(String aciklama) throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();

            result.append(URLEncoder.encode("icerik", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(aciklama, "UTF-8"));
            result.append("&");
            result.append(URLEncoder.encode("kaydet", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode("android", "UTF-8"));


        return result.toString();
    }

}
