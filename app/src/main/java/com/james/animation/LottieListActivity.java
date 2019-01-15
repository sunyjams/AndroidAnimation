package com.james.animation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.james.animation.adapter.LottieAdapter;
import com.james.animation.entity.LottieEntity;
import com.james.animation.res.JsonRes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James
 * Date 2019/1/14.
 * description
 */
public class LottieListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LottieAdapter mLottieAdapter;
    private List<LottieEntity> lottieEntities = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie_list);

        mRecyclerView = findViewById(R.id.lottie_list);

        for (int i = 0; i < JsonRes.JSON_NAMES.length; i++) {
            LottieEntity entity = new LottieEntity();
            entity.setRes(JsonRes.JSON_NAMES[i]);
            entity.setDesc(JsonRes.FILE_DESC[i]);
            entity.setBg(JsonRes.BG_COLORS[i]);
            lottieEntities.add(entity);
        }

        mLottieAdapter = new LottieAdapter(lottieEntities, this);

        mRecyclerView.setAdapter(mLottieAdapter);
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, LottieListActivity.class);
        context.startActivity(intent);
    }
}
