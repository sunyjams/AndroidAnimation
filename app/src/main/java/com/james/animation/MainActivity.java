package com.james.animation;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.animation.DynamicAnimation;
import android.support.animation.FlingAnimation;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button signBtn, flingAnimBtn, descAnimBtn, showUpBtn;
    private LinearLayout descLayout;
    private ImageView imgImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signBtn = findViewById(R.id.main_spring_anim);
        flingAnimBtn = findViewById(R.id.main_fling_anim);
        descLayout = findViewById(R.id.main_txt_layout);
        imgImageView = findViewById(R.id.main_img);
        descAnimBtn = findViewById(R.id.main_desc_anim);
        showUpBtn = findViewById(R.id.main_btn_show_up);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        final int screenHeight = dm.heightPixels;

        showView(signBtn, 100, screenHeight);
        showView(flingAnimBtn, 500, screenHeight);
        showView(descAnimBtn, 800, screenHeight);
        showView(showUpBtn, 1000, screenHeight);

        showDescAnim(screenHeight);

        SpringForce spring = new SpringForce(0)
                .setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY)
                .setStiffness(SpringForce.STIFFNESS_MEDIUM);
        final SpringAnimation anim = new SpringAnimation(imgImageView, SpringAnimation.TRANSLATION_X).setSpring(spring);

        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim.cancel();
                anim.setStartValue(100);
                anim.start();
            }
        });


        flingAnimBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FlingAnimation flingAnimation = new FlingAnimation(imgImageView, DynamicAnimation.X);
                flingAnimation.setStartVelocity(1000f);
                flingAnimation.setFriction(0.5f);
                flingAnimation.setStartValue(-100);
                flingAnimation.start();
            }
        });

        descAnimBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDescAnim(screenHeight);
            }
        });

        imgImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShareEleActivity.class),
                        ActivityOptions.makeSceneTransitionAnimation
                                (MainActivity.this, imgImageView, "img")
                                .toBundle());
            }
        });

        showUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showView(signBtn, 100, screenHeight);
                showView(flingAnimBtn, 400, screenHeight);
                showView(descAnimBtn, 700, screenHeight);
                showView(showUpBtn, 1000, screenHeight);
            }
        });
    }

    private void showView(final View view, long delayTime, int screenHeight) {

        view.setTranslationY(screenHeight);
        final SpringAnimation animY = new SpringAnimation(view, SpringAnimation.TRANSLATION_Y, 0);
        animY.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);
        animY.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY);

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                animY.start();
            }
        }, delayTime);
    }


    private void showDescAnim(int screenHeight) {
        List<SpringAnimation> mLetterAnims = new ArrayList<>();
        for (int i = 0; i < descLayout.getChildCount(); i++) {
            View letterView = descLayout.getChildAt(i);
            letterView.setTranslationY(screenHeight);
            SpringAnimation letterAnimY = new SpringAnimation(letterView, SpringAnimation.TRANSLATION_Y, 0);
            letterAnimY.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);
            letterAnimY.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_LOW_BOUNCY);
            mLetterAnims.add(letterAnimY);
        }

        for (final SpringAnimation letterAnim : mLetterAnims) {
            descLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    letterAnim.start();
                }
            }, 50 * mLetterAnims.indexOf(letterAnim));
        }
    }

}
