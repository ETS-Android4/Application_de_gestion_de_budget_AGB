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

public class BusinessTransaction extends AppCompatActivity {

    private Button ADDBu;
    private ImageButton retourBu;

    private EditText somme;
    private TextView categorie;

    private FirebaseUser user;
    private String userID;

    static ArrayList<String> TransactionList = new ArrayList<>();

    public static String Businessaff="0";


    private int sommeBusiness = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_transaction);

        // Go Back To Incomes
        retourBu = findViewById(R.id.imageButtonBus);
        retourBu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphaBu = new AlphaAnimation(0f, 1f);
                alphaBu.setDuration(100);
                retourBu.startAnimation(alphaBu);
                //end animation button
                Intent ret = new Intent(BusinessTransaction.this, IncomesActivity.class);
                startActivity(ret);
            }
        });

        // find View By Id
        somme = findViewById(R.id.SommeDeLaTransaction);
        categorie = findViewById(R.id.CategorieDeLaTransaction);

        // Liste des transactions: Business Transactions
        final ListView listView = findViewById(R.id.listView);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.agb", Context.MODE_PRIVATE);
        final HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("Transactions", null);

        ADDBu = findViewById(R.id.buttonAddBu);
        ADDBu.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphaDBU = new AlphaAnimation(0f, 1f);
                alphaDBU.setDuration(100);
                ADDBu.startAnimation(alphaDBU);
                //end animation button

                // Get Text
                String sommeT = somme.getText().toString();
                String categorieT = categorie.getText().toString().trim();
                // Creation d'un nouveau Objet: transaction
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
                    // Sommation des transactions entrées
                    sommeBusiness = sommeBusiness + parseInt(sommeT);
                    Recuperer BusinessExpenses = new Recuperer(sommeBusiness);
                    Businessaff = String.valueOf(BusinessExpenses.Afficher());
                    //TransactionList.add(aff);
                    Ref.child(userID).child("Business").setValue(sommeBusiness);
                } else {
                    TransactionList = new ArrayList(set);
                }

                //Ajout a la liste des transactions
                final ArrayAdapter arrayAdapter = new ArrayAdapter(BusinessTransaction.this, android.R.layout.simple_list_item_1, TransactionList);
                listView.setAdapter(arrayAdapter);

            }
        });

    }
}