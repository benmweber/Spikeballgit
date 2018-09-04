package com.example.ben.spikeball;

import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable {

    private String name;

    private boolean played_last_match = false;

    public int mmr = 0;

    public boolean checked = false;

    public Player(String input, int input_mmr)

    {
        name = input;
        mmr = input_mmr;
    }

    public Player(String input)
    {
        name = input;
    }

    public String getName()
    {
        return name;
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
