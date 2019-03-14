package com.example.httpconnections;

import android.util.Log;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.HashMap;

public class RequestParams {
    private HashMap<String, String> params;
    private StringBuilder stringBuilder;

    public RequestParams() {
        params = new HashMap<>();
        stringBuilder = new StringBuilder();
    }

    public RequestParams addParameters(String key, String value){
        try {
            params.put(key, URLEncoder.encode(value, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return this;
    }

    //http://api.theappsdr.com/params.php?name=Nithin&age=18&email=nhuliyap@uncc.edu&pass=pass

    public String getEncodedParameters (){
        for (String key:params.keySet()) {
            if (stringBuilder.length()>0){
                stringBuilder.append("&");
            }
            stringBuilder.append(key+"="+params.get(key));
        }
        return String.valueOf(stringBuilder);
    }

    public String getEncodedURL(String url){
        Log.d("demo", url+"?"+getEncodedParameters());
        return url+"?"+getEncodedParameters();
    }

    public void encodPOSTParameters(HttpURLConnection connection) throws IOException {
        connection.setDoOutput(true);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
        outputStreamWriter.write(getEncodedParameters());
        outputStreamWriter.flush();

    }
}
