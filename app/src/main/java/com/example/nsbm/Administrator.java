package com.example.nsbm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Administrator extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottem_navigation_view);

        bottomNavigationView.setSelectedItemId(R.id.NAVIGATION_ADMIN);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case  R.id.NAVIGATION_MAP:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivity2.class));
                        overridePendingTransition(0,0);
                        return true;

                    case  R.id.NAVIGATION_booking:
                        startActivity(new Intent(getApplicationContext()
                                ,Seat_Booking.class));
                        overridePendingTransition(0,0);

                        return true;

                    case  R.id.NAVIGATION_ACHART:
                        startActivity(new Intent(getApplicationContext()
                                ,Time_Table.class));
                        overridePendingTransition(0,0);

                        return true;

                    case  R.id.NAVIGATION_ADMIN:
                        startActivity(new Intent(getApplicationContext()
                                ,Administrator.class));
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });


        Name   =  (EditText)findViewById(R.id.adName);
        Password  =  (EditText)findViewById(R.id.adPassword);
        Login  = (Button) findViewById(R.id.adLogin);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validate(Name.getText().toString(),Password.getText().toString());

            }
        });


    }

    private void  validate(String userName, String userPassword){

        if ((userName.equals ("Admin")) && (userPassword.equals ("1234"))){

            Intent intent = new  Intent(Administrator.this,AdministirPage.class);
            startActivity(intent);

        }



    }

}