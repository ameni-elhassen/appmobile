package com.example.myapplication22;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class createvent extends AppCompatActivity {



    EditText ed1,ed2,ed3,ed4;
    Button b1,b;
    // FirebaseDatabase rootNode ;
    DatabaseReference reference ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createvent);

        ed1= (EditText) findViewById(R.id.eventname);
        ed2= (EditText) findViewById(R.id.eventdate);
        ed3= (EditText) findViewById(R.id.eventtime);
        ed4= (EditText) findViewById(R.id.eventdescription);
        b1= (Button) findViewById(R.id.add);
        b = (Button) findViewById(R.id.back);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),MainActivity.class)); }

        });






        reference = FirebaseDatabase.getInstance().getReference().child("events");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                insertevent();

            }
        });



    }
    private void insertevent () {
        String name = ed1.getText().toString().trim();
        String date = ed2.getText().toString().trim();
        String time = ed3.getText().toString().trim();
        String description = ed4.getText().toString().trim();

        NewEvent newEvent =new NewEvent(name,date,time,description);
        reference.child(name).setValue(newEvent);

        Toast.makeText(this, "Event added", Toast.LENGTH_LONG).show();





    }






}