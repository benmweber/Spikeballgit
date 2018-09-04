package com.example.ben.spikeball;

import java.util.ArrayList;
import java.util.Random;

public class PairingSelector {

    private ArrayList<Duel> duel_history = new ArrayList<Duel>();


    int nr_players;
    int tries = 0;
    int max_tries = 10000;

    Random rand = new Random();


    Player player1_ = new Player("Timo",600);
    Player player2_ = new Player("Ellie",300);
    Player player3_ = new Player("Diana",300);
    Player player4_ = new Player("Ben",700);
    Player player5_ = new Player("Julius",550);


    public Duel calculateDuel(ArrayList<Player> players){


        Team defaultTeam1 = new Team(player1_, player2_);
        Team defaultTeam2 = new Team(player1_, player2_);
        Duel defaultDuel = new Duel(defaultTeam1, defaultTeam2);

        do{

            //ArrayList<Player> players = new ArrayList<Player>();





            /*players.add(player1_);
            players.add(player2_);
            players.add(player3_);
            players.add(player4_);
            players.add(player5_);
            */
            //Player player6_ = new Player("Melike");
            //players.add(player6_);

            //Player player7_ = new Player("Adi");
            //players.add(player7_);



            nr_players = players.size();


            ArrayList<Player> players_temp = new ArrayList<Player>();

            ArrayList<Player> team1_ = new ArrayList<Player>();

            ArrayList<Player> team2_ = new ArrayList<Player>();


            players_temp = players;



            int r = rand.nextInt(players_temp.size());

            team1_.add(players_temp.get(r));

            players_temp.remove(r);





            r = rand.nextInt(players_temp.size());

            team1_.add(players_temp.get(r));

            players_temp.remove(r);


            r = rand.nextInt(players_temp.size());

            team2_.add(players_temp.get(r));

            players_temp.remove(r);


            r = rand.nextInt(players_temp.size());

            team2_.add(players_temp.get(r));

            players_temp.remove(r);

            Team newTeam1_ = new Team(team1_.get(0),team1_.get(1));
            Team newTeam2_ = new Team(team2_.get(0),team2_.get(1));

            Duel newDuel = new Duel(newTeam1_,newTeam2_);

            if(isNewDuel(newDuel) && newDuel.isFairDuel(200))
            {
                //System.out.println("Next duel: " + newDuel.identifier);
                //System.out.println("Old MMR:");
                //newDuel.showPlayerMMRs();

                tries = 0;

                // playing...

                //r = rand.nextInt(2);
                //newDuel.setWinner(r);
                //newDuel.applyMMRChange();

                duel_history.add(newDuel);
                //System.out.println("===");

                return newDuel;

            }
            else
            {
                // System.out.println("'" + newDuel.identifier + "' is invalid duel!!!");
                tries++;
            }

            //System.out.println(duel_history.get(duel_history.size() - 1).identifier);


        }
        while(tries < max_tries);

        return defaultDuel;

        //System.out.println("Finished with " + duel_history.size() + " duels."); //TODO: right funciton
    }

    public boolean isNewDuel(Duel input) {
        for (int m = 0; m < duel_history.size(); m++) {
            if (input.identifier.equals(duel_history.get(m).identifier)) {
                return false;
            }
        }
        return true;
    }

}




