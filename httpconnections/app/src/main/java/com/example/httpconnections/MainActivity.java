package com.example.httpconnections;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Event Listener for button
        findViewById(R.id.checkNetwork).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected()){
                    Toast.makeText(MainActivity.this, "You are Connected!", Toast.LENGTH_SHORT).show();
                    RequestParams requestParams = new RequestParams();
                    requestParams.addParameters("name","Nithin")
                            .addParameters("age","18")
                            .addParameters("email","nhuliyap@uncc.edu")
                            .addParameters("pass","pass");
                    //new AsyncPOSTParamsDataTask(requestParams).execute("http://api.theappsdr.com/params.php");
                    new AsyncGETParamsDataTask(requestParams).execute("http://api.theappsdr.com/params.php");
                    //new AsyncDataTask().execute("http://dev.theappsdr.com/apis/photos/keywords.php");
                }
                else
                    Toast.makeText(MainActivity.this, "Please Connect to Internet!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Method to check the Internet Connectivity
    private boolean isConnected(){

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo =  connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected()){
            return false;
        }
        else {
            return true;
        }
    }

    private class AsyncDataTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder = new StringBuilder();
            HttpURLConnection httpURLConnection = null;
            //BufferedReader bufferedReader = null;
            String result = null;

            Log.d("url", strings[0]);

            try {
                URL url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
              //  httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() ==  HttpURLConnection.HTTP_OK){
                   //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    //String line = "";
                   // while ((line = bufferedReader.readLine())!= null){
                    //    stringBuilder.append(line);
                    //}
                    //return stringBuilder.toString();
                    result = IOUtils.toString(httpURLConnection.getInputStream(),"UTF-8");
                    Log.d("connection","status Ok" + result);
                //}
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(httpURLConnection !=null)
                    httpURLConnection.disconnect();
            }
            return result;
        }

            @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("result", "Result Is:"+s);
        }
    }

    private class AsyncGETParamsDataTask extends AsyncTask<String, Void, String> {
        RequestParams mParams;
        public  AsyncGETParamsDataTask(RequestParams params){
            mParams = params;
        }
        @Override
        protected String doInBackground(String... strings) {
            //StringBuilder stringBuilder = new StringBuilder();
            HttpURLConnection httpURLConnection = null;
            String result = null;

            try {
                URL url = new URL(mParams.getEncodedURL(strings[0]));
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() ==  HttpURLConnection.HTTP_OK){
                    result = IOUtils.toString(httpURLConnection.getInputStream(),"UTF-8");
                    Log.d("demo", result);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(httpURLConnection !=null)
                    httpURLConnection.disconnect();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("demo", "Result Is:"+s);
        }
    }

    private class AsyncPOSTParamsDataTask extends AsyncTask<String, Void, String> {
        RequestParams mParams;
        public  AsyncPOSTParamsDataTask(RequestParams params){
            mParams = params;
        }
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder = new StringBuilder();
            HttpURLConnection httpURLConnection = null;
            //BufferedReader bufferedReader = null;
            String result = null;

            Log.d("url", strings[0]);

            try {
                URL url = new URL(mParams.getEncodedURL(strings[0]));
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                mParams.encodPOSTParameters(httpURLConnection);
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() ==  HttpURLConnection.HTTP_OK){
                    //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    //String line = "";
                    // while ((line = bufferedReader.readLine())!= null){
                    //    stringBuilder.append(line);
                    //}
                    //return stringBuilder.toString();
                    result = IOUtils.toString(httpURLConnection.getInputStream(),"UTF8");
                    Log.d("connection","status Ok" + result);
                    //}
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(httpURLConnection !=null)
                    httpURLConnection.disconnect();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("result", "Result Is:"+s);
        }
    }

}
