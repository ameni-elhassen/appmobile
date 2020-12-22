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

public class signin extends AppCompatActivity {
    Button b1,b2,b3;
    EditText ed1,ed2;
    FirebaseAuth fAuth;
    ProgressBar pB;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        b1 = (Button)findViewById(R.id.btnsignup);
        b2 = (Button)findViewById(R.id.btnsigin);
        b3= (Button)findViewById(R.id.passf);
        ed1 = (EditText)findViewById(R.id.etEmail);
        ed2 = (EditText)findViewById(R.id.etPassword);
        fAuth=FirebaseAuth.getInstance();
        pB =findViewById(R.id.progressBar);


        b1.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {

                                      startActivity(new Intent(getApplicationContext(),signup.class));

                                  }
                              });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),Resetpass.class));

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      String email = ed1.getText().toString().trim();
                                      String pass = ed2.getText().toString().trim();
                                      if (TextUtils.isEmpty(email)) {
                                          ed1.setError("Email is required");

                                          return;
                                      }
                                      if (TextUtils.isEmpty(pass)) {
                                          ed2.setError("pass is required");
                                          return;
                                      }


                                      pB.setVisibility(View.VISIBLE);

                                      fAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                          @Override
                                          public void onComplete(@NonNull Task<AuthResult> task) {
                                              if(task.isSuccessful()){
                                                  Toast.makeText(signin.this,"logged in successfully ",Toast.LENGTH_SHORT).show();
                                                  startActivity(new Intent( getApplicationContext(),MainActivity.class));}
                                              else {


                                                  Toast.makeText(signin.this,"Try again",Toast.LENGTH_SHORT).show();

                                              }
                                              ;

                                          }
                                      });

                                  }
                              });




        ;}}