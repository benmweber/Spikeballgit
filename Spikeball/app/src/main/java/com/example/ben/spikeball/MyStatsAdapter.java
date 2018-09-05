package com.example.ben.spikeball;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyStatsAdapter extends RecyclerView.Adapter<MyStatsAdapter.ViewHolder> {

    private ArrayList<Player> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    MyStatsAdapter(Context context, ArrayList<Player> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_stats, parent, false);
        return new MyStatsAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String playerName = mData.get(position).getName();
        int playerMMR =  mData.get(position).getMmr();
        int playerWins = mData.get(position).getWins();
        int playerLost = mData.get(position).getLost();

        holder.myTextViewName.setText("Name: " + playerName);
        holder.myTextViewMMR.setText("MMR: " + String.valueOf(playerMMR));
        holder.myTextViewWins.setText("Wins: " + String.valueOf(playerWins));
        holder.myTextViewLost.setText("Lost: " + String.valueOf(playerLost));
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView myTextViewName;
        TextView myTextViewMMR;
        TextView myTextViewWins;
        TextView myTextViewLost;

        ViewHolder(View itemView) {
            super(itemView);
            myTextViewName = itemView.findViewById(R.id.textViewName);
            myTextViewMMR = itemView.findViewById(R.id.textViewMMR);
            myTextViewWins = itemView.findViewById(R.id.textViewWins);
            myTextViewLost = itemView.findViewById(R.id.textViewLost);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if (mClickListener != null) {
                mClickListener.onItemClick(view, getAdapterPosition(), myTextViewName);
            }
        }
    }

        // convenience method for getting data at click position

    public String getItem(int id) {

        return mData.get(id).getName();
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position, TextView myTextView);
    }
}




