package com.charles.recyclerviewlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import base.visibility.items.ListItem;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Charles on 2016/9/4.
 */
public class MyViewHolder<T> extends RecyclerView.ViewHolder implements ListItem {
    @Bind(R.id.iv_img)
    ImageView imgView;
    @Bind(R.id.head)
    ImageView head;

    private Context context;
    private int cureentPosition;

    public MyViewHolder(View itemView ,Context context) {
        super(itemView);
        this.context = context;
        ButterKnife.bind(this,itemView);
    }


    public void  bind(int position , T strImg) {
        this.cureentPosition = position;
        Glide.with(context).load((String)strImg).error(R.mipmap.ic_launcher).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imgView);
    }


    @Override
    public void setActive(View newActiveView, int newActiveViewPosition) {
        head.setVisibility(View.VISIBLE);
        Log.i("Charles" ,  " Activity---可见item====" + newActiveViewPosition);
    }

    @Override
    public void deactivate(View currentView, int position) {
        head.setVisibility(View.INVISIBLE);
        Log.i("Charles" ,  " Activity---被销毁item===" + position);
    }

}
