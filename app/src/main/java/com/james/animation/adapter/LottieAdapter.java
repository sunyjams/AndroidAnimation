package com.james.animation.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.james.animation.LottieActivity;
import com.james.animation.R;
import com.james.animation.entity.LottieEntity;

import java.util.List;

/**
 * Created by James
 * Date 2019/1/15.
 * description
 */

public class LottieAdapter extends RecyclerView.Adapter<LottieAdapter.HV> {

    public static class HV extends RecyclerView.ViewHolder {

        public final TextView titleTextView;
        public final TextView descTextView;
        public final ImageView iconImageView;

        public HV(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.item_lottie_title);
            descTextView = itemView.findViewById(R.id.item_lottie_desc);
            iconImageView = itemView.findViewById(R.id.item_lottie_icon);
        }
    }

    private List<LottieEntity> mDatas;
    private Activity mCtx;

    public LottieAdapter(List<LottieEntity> datas, Activity activity) {
        this.mDatas = datas;
        this.mCtx = activity;
    }

    @Override
    public void onBindViewHolder(final HV holder, final int position) {
        holder.titleTextView.setText(mDatas.get(position).getRes());
        holder.descTextView.setText(mDatas.get(position).getDesc());
        holder.iconImageView.setBackgroundColor(mDatas.get(position).getBg());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LottieActivity.start(mCtx, mDatas.get(position).getRes(),
                        mDatas.get(position).getBg(), mDatas.get(position).getDesc(),
                        holder.iconImageView, "img");
            }
        });
        holder.iconImageView.setTransitionName("img");
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public HV onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lottie, parent, false);
        return new HV(v);
    }
}
