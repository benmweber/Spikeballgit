package com.example.ben.helloworld;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public SharedPreferences myPrefs;
    public SharedPreferences.Editor editor;
    public ArrayList<String> myMessagesList = new ArrayList<String>();
    public int messageCount;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        myPrefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);
        editor = myPrefs.edit();

        messageCount = 0;
        myMessagesList.clear();
        messageCount = readMessages();





        //final Button myButton = findViewById(R.id.button);
        //final CheckBox myCheckBox = findViewById(R.id.checkBox);
        //final EditText myEditText = findViewById(R.id.editText);
        //final TextView myTextView = findViewById(R.id.textView)

    }

    public int readMessages(){



        boolean moreMessages = true;
        int counter = 0;

        while(moreMessages){



            if(myPrefs.contains("message"+(counter+1)) == true){
                //myMessagesList.add(myPrefs.getString("message"+counter,"Default"));
                counter++;
            }
            else{

                moreMessages = false;

            }
        }


        return counter;

    }


    /** Called when the user taps the Send button */
    public void saveMessage(View view) {


        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();

        if (!message.isEmpty()) {

            messageCount++;
            //myMessagesList.add(message);
            editor.putString("message" + messageCount, message);


            //editor.putString("message"+ myMessagesList.size(), message);
            editor.apply();
        }
        else{

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.no_message).setTitle(R.string.no_message);

            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    // User clicked OK button
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }











        //Intent intent = new Intent(this, DisplayMessageActivity.class);
        //intent.putExtra(EXTRA_MESSAGE, message);
        //startActivity(intent);

        // Do something in response to button
    }

    public void showMessages(View view) {

        Intent intent = new Intent(this, SavedMessages.class);
        intent.putExtra(EXTRA_MESSAGE, messageCount);
        startActivity(intent);

        // Do something in response to button
    }


}
