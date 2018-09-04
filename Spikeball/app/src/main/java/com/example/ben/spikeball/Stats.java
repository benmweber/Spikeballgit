package com.example.ben.spikeball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Stats extends AppCompatActivity {

    private ArrayList<String> myPlayerNameList;
    private ArrayList<Player> myPlayerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        Intent intent = getIntent();

        Bundle newBundle = intent.getBundleExtra("newBundle");
        myPlayerList = newBundle.getParcelableArrayList("myPlayerList");
        myPlayerNameList = newBundle.getStringArrayList("myPlayerNameList");


        TextView textView = findViewById(R.id.textView);
        textView.setText(myPlayerNameList.get(0) + " " + myPlayerList.get(0).mmr);


    }
}
