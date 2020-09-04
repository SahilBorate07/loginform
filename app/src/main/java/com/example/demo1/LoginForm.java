package com.example.demo1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginForm extends AppCompatActivity {

    Button btn3;
    Button btn4;
    TextView editT1;
    TextView editT2;

    public String a;
    public String b;

    public String c;
    public String d;

   FirebaseAuth mAuth;
   DatabaseReference reff;
   Member member;

    public final static String KEY_EXTRA_DATA_ID = "KEY_EXTRA_DATA_ID";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginform);

        member = new Member();

        editT1 = findViewById(R.id.editText1);
        editT2 = findViewById(R.id.editTextPassword);

        btn3 = findViewById(R.id.btn1);   //login
        btn4 = findViewById(R.id.btn2);   //Register

        reff = FirebaseDatabase.getInstance().getReference();

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                mAuth = FirebaseAuth.getInstance();
                String et1 = editT1.getText().toString(); //UserName
                String et2 = editT2.getText().toString(); //Password

                System.out.println(et1+et2);

                reff = reff.child(editT1.getText().toString());

                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String userName = dataSnapshot.child("userName").getValue(String.class); //for retrieving data
                        String password = dataSnapshot.child("password").getValue(String.class);
                        // Log.d("username","Got the username");




                        a=userName;
                        b=password;

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                System.out.println(a+b);



                if(et1.isEmpty()){
                    editT1.setError("Please Enter UserName");
                    editT1.requestFocus();
                }
                else if(et2.isEmpty()){
                    editT2.setError("Please Enter Password");
                    editT2.requestFocus();
                }
                else {  //if(!(et1.isEmpty() || et2.isEmpty())){

                    if ((et1.equals(a)) && (et2.equals(b))) {
                        Toast.makeText(LoginForm.this, "SignIn Successful", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LoginForm.this, WelcomeScreen.class));
                    } else {
                        Toast.makeText(LoginForm.this, "LogIn Failed", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I2 = new Intent(LoginForm.this, Home.class);
                startActivity(I2);
            }

        });


    }


}