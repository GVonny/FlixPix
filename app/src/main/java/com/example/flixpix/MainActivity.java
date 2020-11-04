package com.example.flixpix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button flixwheel;
    Button search;
    EditText searchTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    public void IMDBSearch()
    {
        String title = searchTitle.getText().toString();
        if(!title.equals(""))
        {
            Intent intent = new Intent(this, IMDBSearch.class);
            intent.putExtra("title", title);
            startActivity(intent);
        }
        else
        {
            searchTitle.setHint("Please enter a title.");
        }
    }
}