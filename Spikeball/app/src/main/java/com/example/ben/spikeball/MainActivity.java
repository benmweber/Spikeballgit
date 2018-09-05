package com.example.ben.spikeball;

import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.EditText;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements MyAdapter.ItemClickListener, MyDialogFragment.MyDialogListener {


    private SharedPreferences myPrefs;
    private SharedPreferences.Editor editor;
    private ArrayList<Player> myPlayerList;

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        myPlayerList = new ArrayList<>();



        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewStats);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)



        mAdapter = new MyAdapter(this, myPlayerList);


        mAdapter.setClickListener(this);

        mRecyclerView.setAdapter(mAdapter);



    }


    private void showMyDialog(int pos) {

    }

    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the MyDialogFragment.MyDialogListener interface

    @Override
    public void onChoiceClicked(DialogFragment dialog, int which, int position){


        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        switch(which){
            case 0:
                ;//Statistik anzeigen

                Intent intent = new Intent(this, Stats.class);
                Bundle newBundle = new Bundle();
                newBundle.putParcelableArrayList("myPlayerList", myPlayerList);

                intent.putExtra("newBundle", newBundle);


                //intent.putStringArrayListExtra("myPlayerList", myPlayerNameList);
                //intent.putParcelableArrayListExtra("myPlayerList", myPlayerList);
                //intent.putExtra("playerName", position);
                startActivity(intent);

                break;

            case 1:


                myPlayerList.remove(position);

                mRecyclerView.setAdapter(mAdapter);

                builder.setMessage("Fuck that bitch").setTitle("Spieler wurde gelöscht");

                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        // User clicked OK button
                    }
                });

                AlertDialog dialog3 = builder.create();
                dialog3.show();

                break;
        }


    }

    @Override
    public void onItemClick(View view, int pos, CheckedTextView myCheckedTextView) {

        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new MyDialogFragment();

        Boolean value = myCheckedTextView.isChecked();

        if(value){


            myCheckedTextView.setChecked(false);
            myPlayerList.get(pos).checked = false;

        }
        else{

            myCheckedTextView.setChecked(true);
            myPlayerList.get(pos).checked = true;
        }

        Bundle bundle = new Bundle();
        bundle.putInt("position", pos);
        dialog.setArguments(bundle);


        //dialog.show(getSupportFragmentManager(), "MyDialogFragment");

    }

    public void addPlayer(View view){

        EditText editText = (EditText) findViewById(R.id.playerName);
        String playerName = editText.getText().toString();
        editText.getText().clear();

        if (!playerName.isEmpty()) {


            Player pl = new Player(playerName, 0);

            myPlayerList.add(pl);

            mRecyclerView.setAdapter(mAdapter);

        }
        else{

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.no_playerName).setTitle(R.string.no_playerName);

            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    // User clicked OK button
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }


    }

    public void startTurnier(View view){

        ArrayList<Player> myActivePlayers = new ArrayList<>();

        for(int i = 0; i < myPlayerList.size(); i++){

            if(myPlayerList.get(i).checked){

                myActivePlayers.add(myPlayerList.get(i));

            }
            else{

            }
        }

        if(myActivePlayers.size()<4){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Fuck off dude").setTitle("Not enough Players selected");

            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    // User clicked OK button
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else{
            Intent intent = new Intent(this, Turnier.class);
            Bundle newBundle = new Bundle();
            newBundle.putParcelableArrayList("myActivePlayers", myActivePlayers);
            intent.putExtra("newBundle", newBundle);
            startActivity(intent);
        }
    }

    public void showStats(View view){

        ArrayList<Player> myCheckedPlayers = new ArrayList<>();

        for(int i = 0; i < myPlayerList.size(); i++){

            if(myPlayerList.get(i).checked){

                myCheckedPlayers.add(myPlayerList.get(i));

            }
            else{

            }
        }

        if(myCheckedPlayers.isEmpty()){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Fuck off dude").setTitle("No Players selected");

            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    // User clicked OK button
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else{
            Intent intent = new Intent(this, Stats.class);
            Bundle newBundle = new Bundle();
            newBundle.putParcelableArrayList("myCheckedPlayers", myCheckedPlayers);
            intent.putExtra("newBundle", newBundle);
            startActivity(intent);
        }














    }



}
