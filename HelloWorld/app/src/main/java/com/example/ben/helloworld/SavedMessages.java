package com.example.ben.helloworld;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.recyclerview.extensions.AsyncListDiffer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.ben.helloworld.MyAdapter.ItemClickListener;
import java.util.ArrayList;
import java.util.List;

public class SavedMessages extends AppCompatActivity implements MyAdapter.ItemClickListener{

    public SharedPreferences myPrefs;
    public SharedPreferences.Editor editor;
    public ArrayList<String> myMessagesList;

    //public ListView listView;
    //public ArrayAdapter adapter;

    public RecyclerView mRecyclerView;
    public MyAdapter mAdapter;
    public RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_messages);

        myMessagesList = new ArrayList<String>();
        myPrefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);
        editor = myPrefs.edit();


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)



        mAdapter = new MyAdapter(this, myMessagesList);


        mAdapter.setClickListener(this);

        mRecyclerView.setAdapter(mAdapter);

        // = (ListView) findViewById(R.id.listView);
        //adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, myMessagesList);

        Intent intent = getIntent();

        int messageCount = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, 0);


        displayMessages(messageCount);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + mAdapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }

    public void displayMessages(int messageC){

        int counter = 1;
        boolean moreMessages = true;
        while (moreMessages) {

            if (myPrefs.contains("message" + counter) == true) {
                myMessagesList.add(myPrefs.getString("message" + counter, "Default"));
                counter++;
            }
            else{
               moreMessages = false;
            }

        }


        mRecyclerView.setAdapter(mAdapter);
        //listView.setAdapter(adapter);
    }

    public void deleteMessages(View view){

        editor.clear();
        editor.commit();

        int mC = 0;

        myMessagesList.clear();

        displayMessages(mC);

    }

}


