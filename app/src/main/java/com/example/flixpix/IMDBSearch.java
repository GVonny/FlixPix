package com.example.flixpix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class IMDBSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imdb_search);

        Intent i = getIntent();

        String title = i.getExtras().toString();
        title.replaceAll(" ", "%20");

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
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}