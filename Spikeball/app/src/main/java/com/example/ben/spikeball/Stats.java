package com.example.ben.spikeball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class Stats extends AppCompatActivity implements MyStatsAdapter.ItemClickListener{


    private ArrayList<Player> myCheckedPlayers;
    private RecyclerView mRecyclerView;
    private MyStatsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        Intent intent = getIntent();

        Bundle newBundle = intent.getBundleExtra("newBundle");
        myCheckedPlayers = newBundle.getParcelableArrayList("myCheckedPlayers");

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewStats);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)



        mAdapter = new MyStatsAdapter(this, myCheckedPlayers);


        mAdapter.setClickListener(this);

        mRecyclerView.setAdapter(mAdapter);






    }

    @Override
    public void onItemClick(View view, int pos, TextView myTextView) {


    }

    public void showStats(){

    }

}
