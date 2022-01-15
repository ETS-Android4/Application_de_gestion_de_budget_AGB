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

public class ClothingTransaction extends AppCompatActivity {

    private Button ADDC;
    private EditText somme;
    private TextView categorie;

    private FirebaseUser user;
    private String userID;

    static ArrayList<String> TransactionList = new ArrayList<>();

    public static String Clothingaff="0";

    private ImageButton retourCl;

    private int sommeClothing = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing_transaction);

        somme = findViewById(R.id.SommeDeLaTransaction);
        categorie = findViewById(R.id.CategorieDeLaTransaction);

        // Go Back to Expenses
        retourCl = findViewById(R.id.imageButtonClo);
        retourCl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphaCL = new AlphaAnimation(0f, 1f);
                alphaCL.setDuration(200);
                retourCl.startAnimation(alphaCL);
                //end animation button
                Intent ret = new Intent(ClothingTransaction.this, MyExpenses.class);
                startActivity(ret);
            }
        });


        // Liste des transactions: Clothing Transactions
        final ListView listView = findViewById(R.id.listView);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.agb", Context.MODE_PRIVATE);
        final HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("Transactions", null);

        ADDC = findViewById(R.id.buttonAddC);
        ADDC.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphaADDCL = new AlphaAnimation(0f, 1f);
                alphaADDCL.setDuration(200);
                ADDC.startAnimation(alphaADDCL);
                //end animation button
                String sommeT = somme.getText().toString();
                String categorieT = categorie.getText().toString().trim();
                Transaction transaction = new Transaction(sommeT, categorieT);

                //OnClick
                //get User Id
                user = FirebaseAuth.getInstance().getCurrentUser();
                userID = user.getUid();
                //getText
                //ToFirebase
                FirebaseDatabase dataBase = FirebaseDatabase.getInstance("https://app-gestion-de-budget-default-rtdb.europe-west1.firebasedatabase.app/");
                DatabaseReference Ref = dataBase.getReference("Expenses");
                user = FirebaseAuth.getInstance().getCurrentUser();
                userID = user.getUid();

                if (set == null) {
                    TransactionList.add(transaction.AfficheTransaction());
                    // sommation des transations entrées
                    sommeClothing = sommeClothing + parseInt(sommeT);
                    Recuperer ClothingExpenses = new Recuperer(sommeClothing);
                    Clothingaff = String.valueOf(ClothingExpenses.Afficher());
                    //TransactionList.add(aff);
                    Ref.child(userID).child("Clothing").setValue(sommeClothing);
                } else {
                    TransactionList = new ArrayList(set);
                }

                // Ajout a la liste des transactions
                final ArrayAdapter arrayAdapter = new ArrayAdapter(ClothingTransaction.this, android.R.layout.simple_list_item_1, TransactionList);
                listView.setAdapter(arrayAdapter);
            }
        });

    }
}