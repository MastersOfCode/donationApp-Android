package com.mastersofcode;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by T on 2015/08/23.
 */
public class JSONParser
{
    /*** This method returns a json object ***/
    public JSONObject makeHttpRequestJObj(String urlString)
    {
        JSONObject output = null;

        HttpClient httpClient = new DefaultHttpClient();

        HttpGet httpGet = new HttpGet(urlString);

        httpGet.setHeader("accept", "application/json");
        httpGet.setHeader("", "");

        HttpResponse httpResponse;

        try
        {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();

            if (httpEntity != null)
            {
                InputStream inputStream = httpEntity.getContent();
                String result = convertStreamToString(inputStream);
                JSONObject jsonObject = new JSONObject(result);
                Log.d("Result: ", result);
                inputStream.close();
            }
        }
        catch (Exception e) {}

        return output;
    }

    private String convertStreamToString(InputStream inputStream)
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    /**************************************************************************/

    /*** This method returns a json array ***/
    public JSONArray makeHttpRequestJArray(String urlString)
    {
        URLConnection urlConnection = null;
        URL url;
        BufferedInputStream bufferedInputStream = null;

        try
        {
            url = new URL(urlString);
            urlConnection = url.openConnection();
            bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
        }
        catch (ClientProtocolException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        JSONArray jarray = null;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(bufferedInputStream,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                while((line = reader.readLine())!=null){
                    sb.append(line+"\n");
                }
                bufferedInputStream.close();
                Log.d("JSONParser.java", sb.toString());
                String str = sb.toString();
                try {
                    jarray = new JSONArray(str);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    Log.d("JSONParser.java", "Error 1");
                    e.printStackTrace();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                Log.d("JSONParser.java", "Error 2");
                e.printStackTrace();
            }

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            Log.d("JSONParser.java", "Error 3");
            e.printStackTrace();
        }
        Log.d("JSONParser.java", "Success");

        return jarray;
    }
}
