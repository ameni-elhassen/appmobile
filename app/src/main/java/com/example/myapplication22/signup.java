package com.example.myapplication22;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {
    Button b1;
    EditText ed1,ed2,ed3;
    FirebaseAuth fAuth;
    ProgressBar pB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        b1 = (Button)findViewById(R.id.btok);
        ed1 = (EditText)findViewById(R.id.name);
        ed2 = (EditText)findViewById(R.id.nouveauEmail);
        ed3 = (EditText)findViewById(R.id.nouveauPassword);
        fAuth=FirebaseAuth.getInstance();
        pB =findViewById(R.id.progressBar);
        b1.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      String name = ed1.getText().toString().trim();
                                      String email = ed2.getText().toString().trim();
                                      String pass = ed3.getText().toString().trim();


                                      if (TextUtils.isEmpty(name)) {
                                          ed1.setError("name is required");

                                          return;
                                      }

                                      if (TextUtils.isEmpty(email)) {
                                          ed2.setError("Email is required");

                                          return;
                                      }
                                      if (TextUtils.isEmpty(pass)) {
                                          ed3.setError("pass is required");
                                          return;
                                      }
                                      if (pass.length() <5){
                                          ed3.setError("pass must be longer than 5 caracters ");
                                          return;
                                      }



                                      pB.setVisibility(View.VISIBLE);

                                      fAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                          @Override
                                          public void onComplete(@NonNull Task<AuthResult> task) {
                                              if(task.isSuccessful()){
                                                  Toast.makeText(signup.this,"signed up successfully ",Toast.LENGTH_SHORT).show();
                                                  startActivity(new Intent( getApplicationContext(),MainActivity.class));}
                                              else {


                                                  Toast.makeText(signup.this,"Try again",Toast.LENGTH_SHORT).show();

                                              }
                                              ;

                                          }
                                      });

                                  }
                              }
        );}}
