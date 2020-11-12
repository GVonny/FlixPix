package com.example.flixpix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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

    ImageView thumbnail;
    TextView movie_title;

    String searched;
    String title;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imdb_search);

        thumbnail = (ImageView)findViewById(R.id.thumbnail);
        movie_title = (TextView)findViewById(R.id.movie_title);

        Intent i = getIntent();

        searched = i.getStringExtra("title");
        searched.replaceAll(" ", "%20");

        //sendGetRequest();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    OkHttpClient client = new OkHttpClient();

                    Request request = new Request.Builder()
                            .url("https://rapidapi.p.rapidapi.com/title/find?q=" + searched)
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

                        JSONArray results = object.getJSONArray("results");

                        for(int x = 0; x < results.length(); x++)
                        {
                            JSONObject result = results.getJSONObject(x);
                            String titleType = result.getString("titleType");
                            if(titleType.equals("movie") && !titleType.equals("") && !titleType.equals(null))
                            {
                                title = result.getString("title");
                                JSONObject url_data = result.getJSONObject("image");
                                url = url_data.getString("url");

                                movie_title.setText(title);
                                Picasso.with(IMDBSearch.this).load(url).into(thumbnail);

                                break;
                            }
                        }
                        System.out.println("");
                    }
                    catch (IOException | JSONException e)
                    {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    private class MyTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://rapidapi.p.rapidapi.com/title/find?q=" + searched)
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

                JSONArray results = object.getJSONArray("results");

                for(int x = 0; x < results.length(); x++)
                {
                    JSONObject result = results.getJSONObject(x);
                    String titleType = result.getString("titleType");
                    if(titleType.equals("movie") && !titleType.equals("") && !titleType.equals(null))
                    {
                        title = result.getString("title");
                        JSONObject url_data = result.getJSONObject("image");
                        url = url_data.getString("url");
                        break;
                    }
                }
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