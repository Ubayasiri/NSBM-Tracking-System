package com.example.nsbm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class tableUpdate extends AppCompatActivity {

    EditText G11,G22,G33,D11,C11,C22,C33;
    DatabaseReference Reff;
    Button update;


    @Override
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_update);

        G11 = findViewById(R.id.g12);
        G22 = findViewById(R.id.g22);
        G33 = findViewById(R.id.g32);
        D11 = findViewById(R.id.d12);
        C11 = findViewById(R.id.c12);
        C22 = findViewById(R.id.c22);
        C33 = findViewById(R.id.c32);

        update = findViewById(R.id.update);


        Reff = FirebaseDatabase.getInstance().getReference().child("Tables");
        Reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String c1 = dataSnapshot.child("c1").getValue().toString();
                String c2 = dataSnapshot.child("c2").getValue().toString();
                String c3 = dataSnapshot.child("c3").getValue().toString();
                String d1 = dataSnapshot.child("d1").getValue().toString();
                String go1 = dataSnapshot.child("g1").getValue().toString();
                String go2 = dataSnapshot.child("g2").getValue().toString();
                String go3 = dataSnapshot.child("g3").getValue().toString();

                C11.setText(c1);
                C22.setText(c2);
                C33.setText(c3);
                D11.setText(d1);
                G11.setText(go1);
                G22.setText(go2);
                G33.setText(go3);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               insortIntoTabale();

            }
        });

    }

    private  void insortIntoTabale(){

        String c1 = C11.getText().toString();
        String c2 = C22.getText().toString();
        String c3 = C33.getText().toString();
        String d1 = D11.getText().toString();
        String g1 = G11.getText().toString();
        String g2 = G22.getText().toString();
        String g3 = G33.getText().toString();

        Tabales tabales = new Tabales(c1,c2,c3,d1,g1,g2,g3);

        Reff.setValue(tabales);
        Toast.makeText(tableUpdate.this,"Data inserted",Toast.LENGTH_SHORT).show();

    }

}