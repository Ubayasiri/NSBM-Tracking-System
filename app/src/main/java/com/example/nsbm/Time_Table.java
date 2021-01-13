package com.example.nsbm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Time_Table extends AppCompatActivity {

    TextView G11,G22,G33,D11,C11,C22,C33;
    DatabaseReference Reff;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time__table);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottem_navigation_view);

        bottomNavigationView.setSelectedItemId(R.id.NAVIGATION_ACHART);

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
        D11 = (TextView)findViewById(R.id.d1);
        C11 = (TextView)findViewById(R.id.c1);
        C22 = (TextView)findViewById(R.id.c2);
        C33 = (TextView)findViewById(R.id.c3);


        Reff = FirebaseDatabase.getInstance().getReference().child("Tables");
        Reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String c1c = dataSnapshot.child("c1").getValue().toString();
                String c2c = dataSnapshot.child("c2").getValue().toString();
                String c3c = dataSnapshot.child("c3").getValue().toString();
                String d1d = dataSnapshot.child("d1").getValue().toString();
                String g1g=  dataSnapshot.child("g1").getValue().toString();
                String g2g = dataSnapshot.child("g2").getValue().toString();
                String g3g = dataSnapshot.child("g3").getValue().toString();

                C11.setText(c1c);
                C22.setText(c2c);
                C33.setText(c3c);
                D11.setText(d1d);
                G11.setText(g1g);
                G22.setText(g2g);
                G33.setText(g3g);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }
}