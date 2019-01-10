package com.james.animation;

import android.annotation.TargetApi;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by James
 * Date 2019/1/9.
 * description
 */

public class ShareEleActivity extends AppCompatActivity {

    private ImageView playImg;
    private AnimatedVectorDrawable vectorDrawable;
    private boolean isPlay = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_ele);
        getWindow().setEnterTransition(new Fade().setDuration(2000));
        getWindow().setExitTransition(new Fade().setDuration(2000));

        playImg = findViewById(R.id.share_play);

        getSupportActionBar().setTitle("SVG ANIMATION");

        vectorDrawable = ((AnimatedVectorDrawable) playImg.getDrawable());

        playImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    vectorDrawable.registerAnimationCallback(callback);
                }
                vectorDrawable.start();
            }
        });
    }

    private  Animatable2.AnimationCallback callback = new Animatable2.AnimationCallback() {
        @Override
        public void onAnimationStart(Drawable drawable) {
            super.onAnimationStart(drawable);
        }

        @Override
        public void onAnimationEnd(Drawable drawable) {
            super.onAnimationEnd(drawable);
            if (isPlay) {
                vectorDrawable = (AnimatedVectorDrawable) getDrawable(R.drawable.play_anim);
                playImg.setImageDrawable(vectorDrawable);
            }else{
                vectorDrawable = (AnimatedVectorDrawable) getDrawable(R.drawable.pause_anim);
                playImg.setImageDrawable(vectorDrawable);
            }
            isPlay = !isPlay;
        }
    };
}
