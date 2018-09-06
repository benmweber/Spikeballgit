package com.example.ben.spikeball;

import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.PreferenceManager;

public class Player implements Parcelable {




    private String name;

    private boolean played_last_match = false;

    private int mmr = 0;
    private int wins = 0;
    private int lost = 0;

    public boolean checked = false;

    public Player(String input, int input_mmr)
    {
        name = input;
        mmr = input_mmr;
    }
    public Player(String input, int input_mmr, int input_wins, int input_lost){


        name = input;
        mmr = input_mmr;
        wins = input_wins;
        lost = input_lost;

    }

    public Player(String input)
    {
        name = input;
    }

    public String getName()    {
        return name;
    }

    public int getMmr(){
        return mmr;
    }
    public void changeMmr(int mmrChange){

        mmr += mmrChange;

        if(mmr<0){
            mmr = 0;
        }

    }

    public int getWins(){
        return wins;
    }
    public void addWin() {
        this.wins += 1;
    }

    public int getLost() {
        return lost;
    }
    public void addLost() {
        this.lost +=1;
    }

    // Parcelling part
    public Player(Parcel in){

        this.name = in.readString();
        this.mmr = in.readInt();


    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.mmr);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        public Player[] newArray(int size) {
            return new Player[size];
        }
    };
}
