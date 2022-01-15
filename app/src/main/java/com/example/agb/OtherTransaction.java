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

public class OtherTransaction extends AppCompatActivity {

    private Button ADDO;
    private EditText somme;
    private TextView categorie;
    private FirebaseUser user;
    private String userID;
    static ArrayList<String> TransactionList = new ArrayList<>();
    public static String Otheraff="0";
    private ImageButton retourO;
    private int sommeOther = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_transaction);

        retourO = findViewById(R.id.imageButtonO);
        retourO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphaRO = new AlphaAnimation(0f, 1f);
                alphaRO.setDuration(200);
                retourO.startAnimation(alphaRO);
                //end animation button
                Intent ret = new Intent(OtherTransaction.this, MyExpenses.class);
                startActivity(ret);
            }
        });

        somme = findViewById(R.id.SommeDeLaTransaction);
        categorie = findViewById(R.id.CategorieDeLaTransaction);


        final ListView listView = findViewById(R.id.listView);

        ADDO = findViewById(R.id.buttonAddO);
        ADDO.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphaADDO = new AlphaAnimation(0f, 1f);
                alphaADDO.setDuration(200);
                ADDO.startAnimation(alphaADDO);
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


                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.agb", Context.MODE_PRIVATE);
                HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("Transactions", null);
                if (set == null) {
                    TransactionList.add(transaction.AfficheTransaction());
                    sommeOther = sommeOther + parseInt(sommeT);
                    Recuperer OtherExpenses = new Recuperer(sommeOther);
                    Otheraff = String.valueOf(OtherExpenses.Afficher());
                    //TransactionList.add(aff);
                    Ref.child(userID).child("Other").setValue(sommeOther);
                } else {
                    TransactionList = new ArrayList(set);
                }


                final ArrayAdapter arrayAdapter = new ArrayAdapter(OtherTransaction.this, android.R.layout.simple_list_item_1, TransactionList);
                listView.setAdapter(arrayAdapter);

            }
        });
    }
}