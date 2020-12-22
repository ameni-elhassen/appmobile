package com.example.myapplication22;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.ValueEventListener;
public class Event extends AppCompatActivity {
  DatabaseReference reference;
  RecyclerView recyclerView;
  ArrayList <NewEvent> list;
  myadapter adapter;
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        recyclerView = (RecyclerView) findViewById(R.id.recview);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        reference = FirebaseDatabase.getInstance().getReference().child("events");
        reference.addValueEventListener(new ValueEventListener() {
@Override
public void onDataChange( DataSnapshot dataSnapshot) {
        list = new ArrayList<NewEvent>();
        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
        {
        NewEvent p = dataSnapshot1.getValue(NewEvent.class);
        list.add(p);
        }
        adapter = new myadapter(Event.this,list);
        recyclerView.setAdapter(adapter);
        }

@Override
public void onCancelled( DatabaseError databaseError) {
        Toast.makeText(Event.this, "Something is wrong", Toast.LENGTH_SHORT).show();
        }
        });
        }
        }
