package com.james.animation;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;

import com.airbnb.lottie.LottieAnimationView;

/**
 * Created by James
 * Date 2019/1/14.
 * description
 */

public class LottieActivity extends Activity {

    private static final String TAG_RES = "res";
    private static final String TAG_BG = "bg";
    private static final String TAG_DESC = "desc";

    private FrameLayout bgLayout;
    private LottieAnimationView mLottieAnimationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie);

        mLottieAnimationView = findViewById(R.id.share_lottie);
        bgLayout = findViewById(R.id.lottie_bg);

        mLottieAnimationView.setAnimation(getIntent().getStringExtra(TAG_RES));
        mLottieAnimationView.loop(true);
        mLottieAnimationView.playAnimation();
        bgLayout.setBackgroundColor(getIntent().getIntExtra(TAG_BG, Color.WHITE));
    }

    public static void start(Activity activity, String res, int bg, String desc, View view, String sharedEle) {
        Intent intent = new Intent(activity, LottieActivity.class);
        intent.putExtra(TAG_RES, res);
        intent.putExtra(TAG_BG, bg);
        intent.putExtra(TAG_DESC, desc);
        activity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation
                (activity, view, sharedEle)
                .toBundle());
    }
}
