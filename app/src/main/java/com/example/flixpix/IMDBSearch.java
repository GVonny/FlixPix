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

    String title;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imdb_search);

        thumbnail = (ImageView)findViewById(R.id.thumbnail);
        movie_title = (TextView)findViewById(R.id.movie_title);

        Intent i = getIntent();

        title = i.getStringExtra("title");
        url = i.getStringExtra("url");

        movie_title.setText(title);
        Picasso.with(IMDBSearch.this).load(url).into(thumbnail);


    }
}