package com.example.flixpix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class FlixWheel extends AppCompatActivity {

    ImageView wheelimg;
    TextView result;
    TextView winner;
    ArrayList<String> movies;

    Integer[] sectors = {1,2,3,4,5,6,7,8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flix_wheel);

        wheelimg = findViewById(R.id.wheel);
        result = findViewById(R.id.result);
        winner = findViewById(R.id.winner);

        Collections.reverse(Arrays.asList(sectors));

        Intent i = getIntent();
        movies = i.getStringArrayListExtra("movie_list");
    }

    public void spinWheel(View view)
    {
        Random rr = new Random();
        final int degree = rr.nextInt(360);

        RotateAnimation rotateAnimation = new RotateAnimation(0, degree + 1080, RotateAnimation.RELATIVE_TO_SELF,.5f, RotateAnimation.RELATIVE_TO_SELF, .5f);

        rotateAnimation.setDuration(3000);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(new DecelerateInterpolator());

        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                winner.setVisibility(View.INVISIBLE);
                result.setText("");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                CalculatePoint(degree);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        wheelimg.startAnimation(rotateAnimation);
    }
    public void CalculatePoint(int degree)
    {
        int initialPoint = 0;
        int endPoint = 45;
        int i = 0;
        int res = 0;

        do{
            if(degree > initialPoint && degree < endPoint)
            {
                res = sectors[i];
            }
            initialPoint += 45;
            endPoint += 45;
            i++;
        }while(res == 0);

        winner.setVisibility(View.VISIBLE);

        if(res < 5)
        {
            res += 4;
        }

        int index = -1;
        if(movies.size() == 2)
        {
            if(res % 2 == 0)
            {
                index = 0;
            }
            else
            {
                index = 1;
            }
        }
        else if(movies.size() == 3)
        {
            if(res == 7)
            {
                index = 0;
            }
            else if(res == 8)
            {
                index = 1;
            }
            else
            {
                index = Math.round(res / 2) - 1;
            }
        }
        else
        {
            if(res > 4)
            {
                index = 4 - (8 % res) -1;
            }
            else
            {
                index = res - 1;
            }
        }


        String movie = movies.get(index);
        result.setText(movie);
    }
}
