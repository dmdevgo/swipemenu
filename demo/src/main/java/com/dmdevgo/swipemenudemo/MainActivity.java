package com.dmdevgo.swipemenudemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dmdevgo.swipemenu.SwipeMenuHelper;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SwipeAdapter mSwipeAdapter = new SwipeAdapter();
    private SwipeMenuHelper mSwipeMenuHelper = new SwipeMenuHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mSwipeAdapter);
        mSwipeMenuHelper.attachToRecyclerView(mRecyclerView);
    }

}
