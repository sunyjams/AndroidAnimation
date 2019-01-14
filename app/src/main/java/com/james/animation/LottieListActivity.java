package com.james.animation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by James
 * Date 2019/1/14.
 * description
 */
public class LottieListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie_list);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, LottieListActivity.class);
        context.startActivity(intent);
    }
}
