package com.example.demo1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationDetails extends AppCompatActivity {

     TextView Tst1;
     TextView Tst2;
     TextView Tst3;
     TextView Tst4;
     TextView Tst5;
     Button but2;

     DatabaseReference reff;
     Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Tst1 = findViewById(R.id.Txt1);
        Tst2 = findViewById(R.id.Txt2);
        Tst3 = findViewById(R.id.Txt3);
        Tst4 = findViewById(R.id.Txt4);
        Tst5 = findViewById(R.id.Txt5);
        but2 = findViewById(R.id.but1);

        member = new Member();

        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reff = FirebaseDatabase.getInstance().getReference().child(Tst2.getText().toString());

                Long phn = Long.parseLong(Tst5.getText().toString().trim());

                member.setUserName(Tst2.getText().toString().trim());
                member.setE_mail(Tst3.getText().toString().trim());
                member.setPassword(Tst4.getText().toString().trim());
                member.setPh(phn);

                reff.setValue(member);
                Toast.makeText(RegistrationDetails.this,"Data Inserted Successfully",Toast.LENGTH_LONG).show();

                openAct();
            }

        });
    }

    public void openAct() {

        Intent I1 = new Intent(RegistrationDetails.this, LoginForm.class);
        startActivity(I1);
    }

}