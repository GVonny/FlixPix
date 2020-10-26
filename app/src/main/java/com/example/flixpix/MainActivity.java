package com.example.flixpix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.flix_wheel);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FlixWheel();
            }
        });
    }

    public void FlixWheel()
    {
        Intent intent = new Intent(this, FlixWheel.class);
        startActivity(intent);
    }
}