package com.charles.recyclerviewlist;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

import base.visibility.calculator.SingleListViewItemActiveCalculator;
import base.visibility.items.ListItem;
import base.visibility.scroll.ItemsProvider;
import base.visibility.scroll.RecyclerViewItemPositionGetter;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    String img_1 = "http://img10.3lian.com/sc6/show02/67/27/03.jpg";
    String img_2 = "http://img10.3lian.com/sc6/show02/67/27/04.jpg";
    String img_3 = "http://img10.3lian.com/sc6/show02/67/27/01.jpg";
    String img_4 = "http://img10.3lian.com/sc6/show02/67/27/02.jpg";

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<String> list;
    private SingleListViewItemActiveCalculator mCalculator;
    private int mScrollState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        adapter = new MyAdapter(this,list);
        ininView();

    }


    private void ininView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mCalculator = new SingleListViewItemActiveCalculator(adapter,
                new RecyclerViewItemPositionGetter(layoutManager, recyclerView));
        recyclerView.setAdapter(adapter);
        //设置滑动监听
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                mScrollState = newState;
                if(newState == RecyclerView.SCROLL_STATE_IDLE && adapter.getItemCount() > 0){
                    mCalculator.onScrollStateIdle();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                mCalculator.onScrolled(mScrollState);
            }
        });

    }

    private void initData() {
        list = new ArrayList<>();
        list.add(img_1);
        list.add(img_2);
        list.add(img_3);
        list.add(img_4);
        list.add(img_1);
        list.add(img_2);
        list.add(img_3);
        list.add(img_4);
        list.add(img_1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private class MyAdapter<T> extends RecyclerView.Adapter<MyViewHolder> implements ItemsProvider {
        protected  Context context;
        private List<T> listData;

        private MyAdapter(Context context , List<T> listData){
            this.context = context;
            if(listData == null){
                this.listData = new ArrayList<>();
            }else {
                this.listData = listData;
            }
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.rv_item, parent, false);
            MyViewHolder holderView = new MyViewHolder(inflate ,context);
            return holderView;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.bind(position ,listData.get(position));
        }

        @Override
        public int getItemCount() {
            return listData.size() >  0 ? listData.size() : 0;
        }

        @Override
        public ListItem getListItem(int position) {
            RecyclerView.ViewHolder holder = recyclerView.findViewHolderForAdapterPosition(position);
            if (holder instanceof ListItem) {
                return (ListItem) holder;
            }
            return null;
        }

        @Override
        public int listItemSize() {
            return getItemCount();
        }
    }

}
