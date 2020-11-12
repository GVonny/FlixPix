package com.example.flixpix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    LinearLayout layout;
    Button flixwheel;
    Button search;
    Button show_form;
    EditText searchTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (LinearLayout)findViewById(R.id.imdb_search);
        show_form = (Button)findViewById(R.id.show_form);
        show_form.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                if(layout.getVisibility() == View.VISIBLE)
                {
                    layout.setVisibility(v.INVISIBLE);
                }
                else
                {
                    layout.setVisibility(v.VISIBLE);
                }

            }
        });

        searchTitle = (EditText)findViewById(R.id.search_title);
        search = (Button)findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IMDBSearch();
            }
        });

        flixwheel = (Button)findViewById(R.id.flix_wheel);
        flixwheel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FlixWheel();
            }
        });
    }

    public void FlixWheel()
    {
        Intent intent = new Intent(this, FlixWheelInput.class);
        startActivity(intent);
    }

    String title;
    Intent intent;
    public void IMDBSearch()
    {
        title = searchTitle.getText().toString();
        if(!title.equals(""))
        {
            title.replaceAll(" ", "%20");

            new MyTask().execute();
            intent = new Intent(this, IMDBSearch.class);
        }
        else
        {
            searchTitle.setHint("Please enter a title.");
        }
    }

    String movie_title;
    String url;
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
                //System.out.println(responseBody);
                JSONObject object = new JSONObject(responseBody);

                JSONArray results = object.getJSONArray("results");

                for(int x = 0; x < results.length(); x++)
                {
                    JSONObject result = results.getJSONObject(x);
                    String titleType = result.getString("titleType");
                    if(titleType.equals("movie") && !titleType.equals("") && !titleType.equals(null))
                    {
                        movie_title = result.getString("title");
                        JSONObject url_data = result.getJSONObject("image");
                        url = url_data.getString("url");

                        break;
                    }
                }
                intent.putExtra("title", movie_title);
                intent.putExtra("url", url);
                startActivity(intent);
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