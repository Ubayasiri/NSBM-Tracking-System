package com.example.nsbm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    EditText mName,mEmail,mPassword,mPhone;
    Button register;
    FloatingActionButton backButton;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    DatabaseReference database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mName       =  findViewById(R.id.Name);
        mEmail      =  findViewById(R.id.Email);
        mPassword   =  findViewById(R.id.Password);
        mPhone      =  findViewById(R.id.Phone);
        register   =  findViewById(R.id.Register);
        backButton =  findViewById(R.id.backButton);

        fAuth      = FirebaseAuth.getInstance();
        progressBar= findViewById(R.id.progressBar);

        database = FirebaseDatabase.getInstance().getReference().child("User Data");


        if (fAuth.getCurrentUser() != null){

            startActivity(new Intent(getApplicationContext(),MainActivity2.class));
            finish();
        }


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmail.getText().toString().trim();
                String  password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){

                    mEmail.setError("Email is Required.");
                    return;
                }

                if (TextUtils.isEmpty(password)){

                    mPassword.setError("Password is Required.");
                    return;
                }

                if (password.length() < 6){

                    mPassword.setError("Password Must Be >= 6 Characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                // registerig ................................................................................................


                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(Register.this, "User Created.",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity2.class));


                            //data to database .............................................................................................

                            String vname,vemail,vpassword,vphone;

                            vname = mName.getText().toString();

                            vemail = mEmail.getText().toString();

                            vpassword = fAuth.getCurrentUser().getUid();

                            vphone = mPhone.getText().toString();

                            UserData userData = new UserData(vname,vemail,vpassword,vphone);
                            database.child(vpassword).setValue(userData);
                            Toast.makeText(Register.this,"Data Added",Toast.LENGTH_SHORT).show();


                        }else {

                            Toast.makeText(Register.this,"Erorr !" +task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);

                        }

                    }
                });




            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),Login.class));

            }
        });


    }
}