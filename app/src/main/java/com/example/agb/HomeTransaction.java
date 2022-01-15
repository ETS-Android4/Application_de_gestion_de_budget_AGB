package com.example.agb;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashSet;

import static java.lang.Integer.parseInt;

public class HomeTransaction extends AppCompatActivity {

    private Button ADDH;
    private EditText somme;
    private TextView categorie;

    //declaration de variables

    private FirebaseUser user;


    private DatabaseReference Ref;

    public static String Homeaff="0";

    private String userID;

    static ArrayList<String> TransactionList = new ArrayList<>();

    private ImageButton retourHo;

    private int sommeHome = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_transaction);


        retourHo = findViewById(R.id.imageButtonHo);
        retourHo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphaRHO = new AlphaAnimation(0f, 1f);
                alphaRHO.setDuration(200);
                retourHo.startAnimation(alphaRHO);
                //end animation button
                Intent ret = new Intent(HomeTransaction.this, MyExpenses.class);
                startActivity(ret);
            }
        });


        somme = findViewById(R.id.SommeDeLaTransaction);
        categorie = findViewById(R.id.CategorieDeLaTransaction);


        final ListView listView = findViewById(R.id.listView);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.agb", Context.MODE_PRIVATE);
        final HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("Transactions", null);

        ADDH = findViewById(R.id.buttonAddH);
        ADDH.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                //animation button
                AlphaAnimation alphaADDH = new AlphaAnimation(0f, 1f);
                alphaADDH.setDuration(200);
                ADDH.startAnimation(alphaADDH);
                //end animation button

                String sommeT = somme.getText().toString();
                String categorieT = categorie.getText().toString().trim();
                Transaction transaction = new Transaction(sommeT, categorieT);

                //OnClick
                //get User Id
                //ToFirebase
                FirebaseDatabase dataBase = FirebaseDatabase.getInstance("https://app-gestion-de-budget-default-rtdb.europe-west1.firebasedatabase.app/");
                Ref = dataBase.getReference("Expenses");
                user = FirebaseAuth.getInstance().getCurrentUser();
                userID = user.getUid();

                if (set == null) {
                    TransactionList.add(transaction.AfficheTransaction());
                    sommeHome = sommeHome + parseInt(sommeT);
                    Recuperer HomeExpenses = new Recuperer(sommeHome);
                    Homeaff = String.valueOf(HomeExpenses.Afficher());
                    //TransactionList.add(aff);
                    Ref.child(userID).child("Home").setValue(sommeHome);
                } else {
                    TransactionList = new ArrayList(set);
                }


                final ArrayAdapter arrayAdapter = new ArrayAdapter(HomeTransaction.this, android.R.layout.simple_list_item_1, TransactionList);
                listView.setAdapter(arrayAdapter);

                //sommeHome = sommeHome + parseInt(sommeT);
                //Recuperer HomeExpenses = new Recuperer(sommeHome);
                //HomeExpenses.Afficher();
            }
        });



    }
}