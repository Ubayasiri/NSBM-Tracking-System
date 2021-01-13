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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText mEmail,mPassword;
    Button mlogin;
    TextView mbutten;
    FirebaseAuth fAuth;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail      =  findViewById(R.id.editTextTextEmailAddress);
        mPassword   =  findViewById(R.id.editTextTextPassword);
        mlogin      = findViewById(R.id.Login);
        mbutten      = findViewById(R.id.textView2);

        fAuth      = FirebaseAuth.getInstance();
        progressBar= findViewById(R.id.progressBar);

        if (fAuth.getCurrentUser() != null){

            startActivity(new Intent(getApplicationContext(),MainActivity2.class));
            finish();
        }


        mlogin.setOnClickListener(new View.OnClickListener() {
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

                //athenticate ...................................................................................

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(Login.this, "Login Sucssefuly.",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity2.class));
                        }else {

                            Toast.makeText(Login.this,"Erorr !" +task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);

                        }

                    }
                });

            }
        });

        mbutten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),Register.class));

            }
        });

    }
}