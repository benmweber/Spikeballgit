package com.example.ben.spikeball;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Duel {

    private ArrayList<Team> teams = new ArrayList<Team>();

    public String identifier;
    private int mmrDiff;
    private Team winner;
    private Team loser;

    private Comparator<Team> team_comp = new Comparator<Team>(){
        @Override
        public int compare(Team team1, Team team2)
        {
            return  team1.getTeamId().compareTo(team2.getTeamId());
        }
    };

    public Duel(Team team1_,Team team2_)
    {

        teams.add(team1_);
        teams.add(team2_);

        mmrDiff = Math.abs(teams.get(0).combined_mmr - teams.get(1).combined_mmr);

        Collections.sort(teams,team_comp);

        identifier = teams.get(0).getTeamId() + " vs. " + teams.get(1).getTeamId();
    }


    public void setWinnerLoser(Team w, Team l)
    {
        /*winner = teams.get(team_nr);

        if (team_nr == 0)
        {
            loser = teams.get(1);
        }
        else
        {
            loser = teams.get(0);
        }

        System.out.println("And the winner is ... " + winner.getTeamId());*/

        winner = w;
        loser = l;

        winner.getPlayer(0).addWin();
        winner.getPlayer(1).addWin();

        loser.getPlayer(0).addLost();
        loser.getPlayer(1).addLost();


    }

    public void applyMMRChange()
    {
        int mmr_difference = winner.combined_mmr - loser.combined_mmr; // if positive, the expected team won, if negative, the underdog won

        double gain;
        int abs_mmr_diff = Math.abs(mmr_difference);

        //System.out.println("MMR Differenz: " + abs_mmr_diff);

        int change_winner_team;
        int change_loser_team;

        if(mmr_difference >= 0) // expected
        {
            change_winner_team = (int) (10 + 0.1 * abs_mmr_diff);
            change_loser_team = (int) (-10 + 0.1 * abs_mmr_diff);

            if(change_loser_team > 0)
            {
                change_loser_team = 0;
            }
        }
        else // unexpected
        {
            change_winner_team = (int) (10 + abs_mmr_diff * 0.3);
            change_loser_team = (int) (-10 - abs_mmr_diff * 0.3);
        }

        winner.getPlayer(0).changeMmr(change_winner_team);
        winner.getPlayer(1).changeMmr(change_winner_team);
        loser.getPlayer(0).changeMmr(change_loser_team);
        loser.getPlayer(1).changeMmr(change_loser_team);



        //System.out.println("Winner MMR change: " + change_winner_team);
        //System.out.println("Loser MMR change: " + change_loser_team);

        //winner.showMMRs();
        //loser.showMMRs();





    }

    public void showPlayerMMRs()
    {
        teams.get(0).showMMRs();
        teams.get(1).showMMRs();
    }

    public boolean isFairDuel(int threshold_diff)
    {
        int mmr_difference = Math.abs(teams.get(0).combined_mmr - teams.get(1).combined_mmr);
        if (mmr_difference > threshold_diff)
        {
            return false;
        }
        return true;
    }

    public ArrayList<Team> getTeams()
    {

        return teams;
    }

    public int getMmrDiff(){


        return mmrDiff;
    }


}
