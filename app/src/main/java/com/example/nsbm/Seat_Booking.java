package com.example.nsbm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Seat_Booking extends AppCompatActivity {

    TextView G11,G22,G33,C11,C22,C33;
    RadioGroup radioGroup;
    RadioButton radioButton;
    DatabaseReference Reff;
    Button book;
    FirebaseAuth fAuth;

    DatabaseReference Data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat__booking);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottem_navigation_view);

        bottomNavigationView.setSelectedItemId(R.id.NAVIGATION_booking);

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



        G11 = (TextView)findViewById(R.id.g1);
        G22 = (TextView)findViewById(R.id.g2);
        G33 = (TextView)findViewById(R.id.g3);
        C11 = (TextView)findViewById(R.id.c1);
        C22 = (TextView)findViewById(R.id.c2);
        C33 = (TextView)findViewById(R.id.c3);

        fAuth  = FirebaseAuth.getInstance();

        radioGroup = findViewById(R.id.RadioGroop);

        book = findViewById(R.id.Book);

        Data = FirebaseDatabase.getInstance().getReference().child("Booking");

        Reff = FirebaseDatabase.getInstance().getReference().child("Tables");
        Reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String c1c = dataSnapshot.child("c1").getValue().toString();
                String c2c = dataSnapshot.child("c2").getValue().toString();
                String c3c = dataSnapshot.child("c3").getValue().toString();
                String g1g = dataSnapshot.child("g1").getValue().toString();
                String g2g = dataSnapshot.child("g2").getValue().toString();
                String g3g = dataSnapshot.child("g3").getValue().toString();

                C11.setText(c1c);
                C22.setText(c2c);
                C33.setText(c3c);
                G11.setText(g1g);
                G22.setText(g2g);
                G33.setText(g3g);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);


                String name;
                String id;

                name = radioButton.getText().toString();
                id = fAuth.getCurrentUser().getUid();


                Booking booking = new Booking(name,id);
                Data.child(id).setValue(booking);
                Toast.makeText(Seat_Booking.this,"Seat Booked",Toast.LENGTH_SHORT).show();


            }



        });


    }


}