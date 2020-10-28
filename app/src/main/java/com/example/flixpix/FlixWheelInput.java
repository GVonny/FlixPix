package com.example.flixpix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FlixWheelInput extends AppCompatActivity {

    Button spin;

    public FlixWheelInput()
    {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flix_wheel_input);

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
        Intent intent = new Intent(this, FlixWheel.class);
        startActivity(intent);
    }
}
