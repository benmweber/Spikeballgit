package com.example.ben.spikeball;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Team {


        private ArrayList<Player> team_composition = new ArrayList<Player>();
        private String team_id;

        public int combined_mmr = 0;

        private Comparator<Player> player_comp = new Comparator<Player>(){
            @Override
            public int compare(Player player1, Player player2)
            {
                return  player1.getName().compareTo(player2.getName());
            }
        };

    public Team(Player player1, Player player2)
        {
            team_composition.add(player1);
            team_composition.add(player2);

            Collections.sort(team_composition,player_comp);

            team_id = team_composition.get(0).getName() + " & " + team_composition.get(1).getName();

            combined_mmr = (player1.mmr + player2.mmr) / 2;
        }

        public String getTeamId()
        {
            return team_id;
        }

        public void changePlayerMMR(int player_nr, int change)
        {
            team_composition.get(player_nr - 1).mmr += change;
        }

        public void showMMRs()
        {
            System.out.println("MMR " + team_composition.get(0).getName() + ": " + team_composition.get(0).mmr);
            System.out.println("MMR " + team_composition.get(1).getName() + ": " + team_composition.get(1).mmr);
        }

        public Player getPlayer(int nr)
        {


            return team_composition.get(nr);

        }



}
