package com.example.agb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registration extends AppCompatActivity {

    private Button buttonRegistartion, buttonBack;
    private EditText editTextFullName, editTextEmail, editTextAge, editTextPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        editTextAge = findViewById(R.id.register_age);
        editTextEmail = findViewById(R.id.register_mail);
        editTextFullName = findViewById(R.id.register_name);
        editTextPassword = findViewById(R.id.register_password);

        //Already registered : Back to Main Activty to Connect
        buttonBack = findViewById(R.id.buttonBackSignIn);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphaRegB = new AlphaAnimation(0f, 1f);
                alphaRegB.setDuration(100);
                buttonBack.startAnimation(alphaRegB);
                //end animation button
                Intent toSign = new Intent(registration.this, MainActivity.class);
                startActivity(toSign);
            }
        });

        // button Register : Registration done successfully and back to main activity to connect
        buttonRegistartion = findViewById(R.id.buttonRegistration);
        buttonRegistartion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphaReg = new AlphaAnimation(0f, 1f);
                alphaReg.setDuration(100);
                buttonRegistartion.startAnimation(alphaReg);
                //end animation button
                registerUser();
                startActivity(new Intent(registration.this, MainActivity.class));
            }
        });
    }

    public void registerUser(){
        final String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        final String fullName = editTextFullName.getText().toString().trim();
        final String age = editTextAge.getText().toString();

        if(fullName.isEmpty()){
            editTextFullName.setError("Full name is required!");
            editTextFullName.requestFocus();
            return;
        }

        if(age.isEmpty()){
            editTextAge.setError("Age is required!");
            editTextAge.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("please provide valid email!");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() < 6){
            editTextPassword.setError("Min Password length should be 6 characters!");
            editTextPassword.requestFocus();
            return;
        }
//"app-gestion-de-budget-default-rtdb"
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(fullName,age,email);
                            FirebaseDatabase dataBase = FirebaseDatabase.getInstance("https://app-gestion-de-budget-default-rtdb.europe-west1.firebasedatabase.app/");
                            DatabaseReference Ref = dataBase.getReference("Users");
                            Ref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user);
                            //Toast.makeText(registration.this,"User has been registered successfully",Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(registration.this,"Failed to register, Try Again!",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}