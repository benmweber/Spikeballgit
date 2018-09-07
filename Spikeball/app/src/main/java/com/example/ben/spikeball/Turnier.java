package com.example.ben.spikeball;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Turnier extends AppCompatActivity {


    private SharedPreferences myPrefs;
    private SharedPreferences.Editor myPrefEditor;

    private ArrayList<Player> myActivePlayers;
    private Duel activeDuel;

    private PairingSelector pairSelector = new PairingSelector();

    private Player player1_ = new Player("Fertig",0);
    private Player player2_ = new Player("Fertig",0);

    private Team defaultTeam1 = new Team(player1_, player2_);
    private Team defaultTeam2 = new Team(player1_, player2_);
    private Duel defaultDuel = new Duel(defaultTeam1, defaultTeam2);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turnier);

        Intent intent = getIntent();

        Bundle newBundle = intent.getBundleExtra("newBundle");
        myActivePlayers = newBundle.getParcelableArrayList("myActivePlayers");

        myPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        myPrefEditor = myPrefs.edit();




        calculateTeams();


    }

    private void calculateTeams(){


            //Duel duel = pairSelector.calculateDuel(myActivePlayers, defaultDuel);
            activeDuel = pairSelector.calculateDuel(myActivePlayers, defaultDuel);
            ArrayList<Team> teams = activeDuel.getTeams();

            TextView textMMRdiff = findViewById(R.id.mmrdiff);
            textMMRdiff.setText(Integer.toString(activeDuel.getMmrDiff()));

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

                                activeDuel.setWinnerLoser(activeDuel.getTeams().get(0), activeDuel.getTeams().get(1));
                                activeDuel.applyMMRChange();
                                calculateTeams();

                                break;

                            case 1:

                                activeDuel.setWinnerLoser(activeDuel.getTeams().get(1), activeDuel.getTeams().get(0));
                                activeDuel.applyMMRChange();
                                calculateTeams();

                                break;


                        }



                    }



                });


        AlertDialog dialog = builder.create();
        dialog.show();




    }

    public void skipDuel(View view){

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

                                for(int i = 0; i < myActivePlayers.size(); i++){
                                    myPrefEditor.putInt(myActivePlayers.get(i).getName() + "MMR", myActivePlayers.get(i).getMmr());
                                    myPrefEditor.putInt(myActivePlayers.get(i).getName() + "Wins", myActivePlayers.get(i).getWins());
                                    myPrefEditor.putInt(myActivePlayers.get(i).getName() + "Lost", myActivePlayers.get(i).getLost());

                                }
                                myPrefEditor.apply();




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



