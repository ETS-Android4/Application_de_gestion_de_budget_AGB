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

public class RealEstateTransaction extends AppCompatActivity {

    // Declarations des variables
    private Button ADDRE;
    private EditText somme;
    private TextView categorie;
    private FirebaseUser user;
    private String userID;
    static ArrayList<String> TransactionList = new ArrayList<>();
    public static String RealEaff="0";
    private ImageButton retourRE;
    private int sommeRealEstate = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_estate_transaction);

        retourRE = findViewById(R.id.imageButtonRE);
        retourRE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphaRE = new AlphaAnimation(0f, 1f);
                alphaRE.setDuration(200);
                retourRE.startAnimation(alphaRE);
                //end animation button
                Intent ret = new Intent(RealEstateTransaction.this, IncomesActivity.class);
                startActivity(ret);
            }
        });

        somme = findViewById(R.id.SommeDeLaTransaction);
        categorie = findViewById(R.id.CategorieDeLaTransaction);

        final ListView listView = findViewById(R.id.listView);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.agb", Context.MODE_PRIVATE);
        final HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("Transactions", null);

        ADDRE = findViewById(R.id.buttonAddRE);
        ADDRE.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                //animation button
                AlphaAnimation alphaDRE = new AlphaAnimation(0f, 1f);
                alphaDRE.setDuration(200);
                ADDRE.startAnimation(alphaDRE);
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
                DatabaseReference Ref = dataBase.getReference("Incomes");
                user = FirebaseAuth.getInstance().getCurrentUser();
                userID = user.getUid();

                if (set == null) {
                    TransactionList.add(transaction.AfficheTransaction());
                    sommeRealEstate = sommeRealEstate + parseInt(sommeT);
                    Recuperer RealEstateExpenses = new Recuperer(sommeRealEstate);
                    RealEaff = String.valueOf(RealEstateExpenses.Afficher());
                    //TransactionList.add(aff);
                    Ref.child(userID).child("Real Estate").setValue(sommeRealEstate);
                } else {
                    TransactionList = new ArrayList(set);
                }

                final ArrayAdapter arrayAdapter = new ArrayAdapter(RealEstateTransaction.this, android.R.layout.simple_list_item_1, TransactionList);
                listView.setAdapter(arrayAdapter);

            }
        });
    }
}