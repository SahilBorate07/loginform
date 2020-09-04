package com.example.demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    private Spinner spinner;
    private EditText editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spinner = findViewById(R.id.spinnerCountries);
        spinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames));
        editor = findViewById(R.id.phone);
        findViewById(R.id.but3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];

                String numb = editor.getText().toString().trim();

                if(numb.isEmpty() || numb.length() <10){
                    editor.setError("Valid Number Is Required");
                    editor.requestFocus();
                    return;

                }

                String phno = "+" + code + numb;

                Intent I3 = new Intent(Home.this,OtpActivity.class);
                I3.putExtra("phonenumber",phno);
                startActivity(I3);

            }
        });

    }

    protected void onStart(){
        super.onStart();

        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent I4 = new Intent(Home.this,WelcomeScreen.class);

            I4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(I4);
        }
    }
}