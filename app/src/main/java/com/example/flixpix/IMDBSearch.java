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
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class IMDBSearch extends AppCompatActivity {

    ImageView thumbnail;
    TextView movie_title;
    TextView movie_length;
    TextView year_released;
    TextView actor1;
    TextView actor2;
    TextView actor3;
    TextView character1;
    TextView character2;
    TextView character3;

    String title;
    String url;
    int length;
    int year;
    ArrayList<String> actors;
    ArrayList<String> characters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imdb_search);

        thumbnail = (ImageView)findViewById(R.id.thumbnail);
        movie_title = (TextView)findViewById(R.id.movie_title);
        movie_length = (TextView)findViewById(R.id.movie_length);
        year_released = (TextView)findViewById(R.id.year_released);

        actor1 = (TextView)findViewById(R.id.actor1);
        actor2 = (TextView)findViewById(R.id.actor2);
        actor3 = (TextView)findViewById(R.id.actor3);


        Intent i = getIntent();

        title = i.getStringExtra("title");
        url = i.getStringExtra("url");
        length = i.getIntExtra("length",0);
        year = i.getIntExtra("year", 0);

        actors = i.getStringArrayListExtra("actors");
        characters = i.getStringArrayListExtra("characters");

        actor1.setText(actors.get(0) + ": " + characters.get(0));
        actor2.setText(actors.get(1) + ": " + characters.get(1));
        actor3.setText(actors.get(2) + ": " + characters.get(2));

        movie_length.setText("Length: " + String.valueOf(length) + " minutes");
        year_released.setText("Released: " + String.valueOf(year));

        movie_title.setText(title);
        Picasso.with(IMDBSearch.this).load(url).into(thumbnail);


    }
}