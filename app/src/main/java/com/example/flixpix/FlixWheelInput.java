package com.example.flixpix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class FlixWheelInput extends AppCompatActivity {

    Button spin;
    EditText movie1;
    EditText movie2;
    EditText movie3;
    EditText movie4;

    public FlixWheelInput()
    {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flix_wheel_input);

        movie1 = (EditText)findViewById(R.id.movie_1);
        movie2 = (EditText)findViewById(R.id.movie_2);
        movie3 = (EditText)findViewById(R.id.movie_3);
        movie4 = (EditText)findViewById(R.id.movie_4);

        spin = (Button)findViewById(R.id.spin);
        spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spin();
            }
        });
    }

    public void Spin()
    {
        ArrayList<String> movies = new ArrayList<String>();

        String movie_1 = movie1.getText().toString();
        String movie_2 = movie2.getText().toString();
        String movie_3 = movie3.getText().toString();
        String movie_4 = movie4.getText().toString();

        if(!movie_1.equals(""))
        {
            movies.add(movie_1);
        }
        if(!movie_2.equals(""))
        {
            movies.add(movie_2);
        }
        if(!movie_3.equals(""))
        {
            movies.add(movie_3);
        }
        if(!movie_4.equals(""))
        {
            movies.add(movie_4);
        }

        if(movies.size() > 1)
        {
            Intent intent = new Intent(this, FlixWheel.class);
            intent.putStringArrayListExtra("movie_list", movies);
            startActivity(intent);
        }
    }
}
