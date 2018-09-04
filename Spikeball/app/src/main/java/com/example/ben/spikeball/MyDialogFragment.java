package com.example.ben.spikeball;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyDialogFragment extends DialogFragment {



    public interface MyDialogListener {
        void onChoiceClicked(DialogFragment dialog, int which, int position);
    }

    MyDialogListener mListener;

    // Override the Fragment.onAttach() method to instantiate the MyDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (MyDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement MyDialogListener");
        }
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final int position;
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt("position");
        }
        else{
            position = 0;
        }


        // Build the dialog and set up the button click handlers

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        String[] auswahlListe = {
                "Statistik",
                "Löschen"
        };

        builder.setTitle("Bitte eine Auswahl treffen")

                .setItems(auswahlListe, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item

                        mListener.onChoiceClicked(MyDialogFragment.this, which, position);
                    }
                });

        return builder.create();
    }




    // Use this instance of the interface to deliver action events




}
