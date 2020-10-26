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

    String[] sectors = {"01","02","03","04","05","06","07","08"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flix_wheel);

        wheelimg = findViewById(R.id.wheel);
        result = findViewById(R.id.result);

        Collections.reverse(Arrays.asList(sectors));
    }

    public FlixWheel()
    {

    }

    public void spinWheel(View view)
    {
        Random rr = new Random();
        final int degree = rr.nextInt(360);

        RotateAnimation rotateAnimation = new RotateAnimation(0, degree + 720, RotateAnimation.RELATIVE_TO_SELF,.5f, RotateAnimation.RELATIVE_TO_SELF, .5f);

        rotateAnimation.setDuration(3000);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(new DecelerateInterpolator());

        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

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
        }while(res == null);

        result.setText(res);
    }
}
