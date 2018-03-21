package com.example.chaochen.myvalyoutdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @创建者 chenchao.
 * @创建时间 2018/3/15 14:29
 * @描述 ${TODO}
 */

public class VlayoutAdapter extends DelegateAdapter.Adapter<VlayoutAdapter.ValyoutViewHolder> {

    private Context                   context;
    private LayoutHelper              layoutHelper;
    private RecyclerView.LayoutParams layoutParams;
    private int count = 0;

    public VlayoutAdapter(Context context, LayoutHelper layoutHelper, int count) {
        this(context, layoutHelper, new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300), count);
    }

    public VlayoutAdapter(Context context, LayoutHelper layoutHelper, RecyclerView.LayoutParams layoutParams, int count) {
        this.context = context;
        this.layoutHelper = layoutHelper;
        this.layoutParams = layoutParams;
        this.count = count;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }

    @Override
    public ValyoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vlayout, null);
        ValyoutViewHolder valyoutViewHolder = new ValyoutViewHolder(view);
        return valyoutViewHolder;
    }

    @Override
    public void onBindViewHolder(ValyoutViewHolder holder, int position) {
        holder.mTextView.setText(position + "");
        if (position > 7) {
            holder.itemView.setBackgroundColor(0x66cc0000 + (position - 6) * 128);
        } else if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(0xaa22ff22);
        } else {
            holder.itemView.setBackgroundColor(0xccff22ff);
        }
    }

    @Override
    public int getItemCount() {
        return count;
    }

    public class ValyoutViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ValyoutViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}

