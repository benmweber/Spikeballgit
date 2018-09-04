package com.example.ben.spikeball;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Turnier extends AppCompatActivity {

    private ArrayList<String> myPlayerNameList;
    private ArrayList<Player> myPlayerList;
    private ArrayList<Player> players;
    private PairingSelector pairSelector = new PairingSelector();

    public Player player1_ = new Player("Timo",600);
    public Player player2_ = new Player("Ellie",300);
    public Player player3_ = new Player("Diana",300);
    public Player player4_ = new Player("Ben",700);
    public Player player5_ = new Player("Julius",550);
    public Team defaultTeam1 = new Team(player1_, player2_);
    public Team defaultTeam2 = new Team(player1_, player2_);
    public Duel defaultDuel = new Duel(defaultTeam1, defaultTeam2);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turnier);

        Intent intent = getIntent();

        Bundle newBundle = intent.getBundleExtra("newBundle");
        myPlayerList = newBundle.getParcelableArrayList("myPlayerList");
        myPlayerNameList = newBundle.getStringArrayList("myPlayerNameList");

        players = new ArrayList<>();

        players.add(player1_);
        players.add(player2_);
        players.add(player3_);
        players.add(player4_);
        players.add(player5_);



        calculateTeams();


    }



    private void calculateTeams(){


            Duel duel = pairSelector.calculateDuel(players, defaultDuel);
            ArrayList<Team> teams = duel.getTeams();

            TextView textMMRdiff = findViewById(R.id.mmrdiff);
            textMMRdiff.setText(Integer.toString(duel.getMmrDiff()));




            TextView textView1= findViewById(R.id.spieler1);

            textView1.setText(teams.get(0).getPlayer(0).getName());

            TextView textView2 = findViewById(R.id.spieler2);

            textView2.setText(teams.get(0).getPlayer(1).getName());

            TextView textView3 = findViewById(R.id.spieler3);

            textView3.setText(teams.get(1).getPlayer(0).getName());

            TextView textView4 = findViewById(R.id.spieler4);

            textView4.setText(teams.get(1).getPlayer(1).getName());







    }

    public void fertigButtonPressed(View view){


        final AlertDialog.Builder builder = new AlertDialog.Builder(this);


        String[] auswahlListe = {
                "Team 1",
                "Team 2"
        };

        builder.setTitle("Wer hat gewonnen?")

                .setItems(auswahlListe, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item

                        switch(which){
                            case 0:




                                break;

                            case 1:



                                break;


                        }



                    }



                });


        AlertDialog dialog = builder.create();
        dialog.show();


        calculateTeams();

    }

    public void stopTurnier(View view){

        final Context con = this.getBaseContext();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);


        String[] auswahlListe = {
                "Speichern?",
                "Verwerfen?",
                "LUL?",
                "Fuck off!!!"
        };

        builder.setTitle("Bitte eine Auswahl treffen")

                .setItems(auswahlListe, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item

                        switch(which){
                            case 0:
                                ;//Statistik anzeigen

                                break;

                            case 1:



                                break;

                            case 2:
                                ;
                                break;

                            case 3:
                                ;
                                break;
                        }



                    }
                });


        AlertDialog dialog = builder.create();
        dialog.show();


    }


}



