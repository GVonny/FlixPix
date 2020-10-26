package com.example.flixpix;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class FlixWheel extends AppCompatActivity {

    ImageView wheelimg;
    TextView result;
    TextView winner;

    String[] sectors = {"1","2","3","4","5","6","7","8"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flix_wheel);

        wheelimg = findViewById(R.id.wheel);
        result = findViewById(R.id.result);
        winner = findViewById(R.id.winner);

        Collections.reverse(Arrays.asList(sectors));
    }

    public FlixWheel()
    {

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
        String res = null;

        do{
            if(degree > initialPoint && degree < endPoint)
            {
                res = sectors[i];
            }
            initialPoint += 45;
            endPoint += 45;
            i++;
        }while(res == null);

        winner.setVisibility(View.VISIBLE);
        result.setText(res);
    }
}
