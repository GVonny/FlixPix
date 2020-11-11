package com.example.flixpix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class IMDBSearch extends AppCompatActivity {

    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imdb_search);

        Intent i = getIntent();

        title = i.getStringExtra("title");
        title.replaceAll(" ", "%20");

        new MyTask().execute();
    }

    private class MyTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://rapidapi.p.rapidapi.com/title/find?q=" + title)
                    .get()
                    .addHeader("x-rapidapi-key", "7c57c58d8cmshc44eb6b914339e8p15232djsn6acde32e5206")
                    .addHeader("x-rapidapi-host", "imdb8.p.rapidapi.com")
                    .build();

            try
            {
                Response response = client.newCall(request).execute();
                String responseBody = response.body().string();
                System.out.println(responseBody);
                JSONObject object = new JSONObject(responseBody);

                //JSONArray id = object.getJSONArray("titleType");
                //System.out.println(id);

                //Iterator<String> keys = object.keys();
                //System.out.println("");

                JSONArray results = object.getJSONArray("results");
                System.out.println("");
            }
            catch (IOException | JSONException e)
            {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}