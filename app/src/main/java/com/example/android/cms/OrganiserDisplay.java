package com.example.android.cms;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OrganiserDisplay extends AppCompatActivity {

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(OrganiserDisplay.this);
        builder.setTitle("Warning").
                setMessage("You sure, that you want to logout?");
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i = new Intent(getApplicationContext(),
                                MainActivity.class);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(), "Logout Successful", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
        builder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder.create();
        alert11.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_organiser_display );
        Button Event = findViewById(R.id.button2);
        Button Judge = findViewById(R.id.button3);
        Button Sponser = findViewById(R.id.button4);
        Button UpdEvt = findViewById(R.id.button5);
        Button ViewEvent = findViewById(R.id.button6);
        Button ViewJudge = findViewById(R.id.button7);
        Button ViewSponser = findViewById(R.id.button8);


        Event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OrganiserDisplay.this, AddEvent.class);
                startActivity(i);
            }
        });

        Judge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OrganiserDisplay.this, AddJudge.class);
                startActivity(i);
            }
        });

        Sponser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OrganiserDisplay.this, AddSponser.class);
                startActivity(i);
            }
        });

        UpdEvt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OrganiserDisplay.this, UpdateEvent.class);
                startActivity(i);
            }
        });
        ViewEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OrganiserDisplay.this, ViewOrganiserEvent.class);
                startActivity(i);
            }
        });

        ViewJudge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OrganiserDisplay.this, ViewOrganiserJudge.class);
                startActivity(i);
            }
        });

        ViewSponser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OrganiserDisplay.this, ViewOrganiserSponser.class);
                startActivity(i);
            }
        });

    }
}
