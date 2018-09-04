package com.example.ben.spikeball;

import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable {

    private String name;

    private boolean played_last_match = false;

    public int mmr = 0;

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
        String[] data = new String[1];
        int[] mmrs = new int[1];
        //in.readString(this.name);
        //in.readInt(this.mmr);

        in.readStringArray(data);
        in.readIntArray(mmrs);
        this.name = data[0];
        this.mmr = mmrs[0];


    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.name});
        dest.writeIntArray(new int[] {this.mmr});
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
