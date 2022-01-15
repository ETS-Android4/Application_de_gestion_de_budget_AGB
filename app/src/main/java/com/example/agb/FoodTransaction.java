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

public class FoodTransaction extends AppCompatActivity {

    private Button ADD;
    private EditText somme;
    private TextView categorie;

    private FirebaseUser user;
    private String userID;

    static ArrayList<String> TransactionList = new ArrayList<>();

    public static String Foodaff="0";

    private ImageButton retourFo;

    private int sommeFood = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_transaction);

        retourFo = findViewById(R.id.imageButtonFo);
        retourFo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphaFo = new AlphaAnimation(0f, 1f);
                alphaFo.setDuration(200);
                retourFo.startAnimation(alphaFo);
                //end animation button
                Intent ret = new Intent(FoodTransaction.this, MyExpenses.class);
                startActivity(ret);
            }
        });

        somme = findViewById(R.id.SommeDeLaTransaction);
        categorie = findViewById(R.id.CategorieDeLaTransaction);


        final ListView listView = findViewById(R.id.listView);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.agb", Context.MODE_PRIVATE);
        final HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("Transactions", null);

        ADD = findViewById(R.id.buttonAdd);
        ADD.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                //animation button
                AlphaAnimation alphaADD = new AlphaAnimation(0f, 1f);
                alphaADD.setDuration(200);
                ADD.startAnimation(alphaADD);
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
            sommeFood = sommeFood + parseInt(sommeT);
            Recuperer FoodExpenses = new Recuperer(sommeFood);
            Foodaff = String.valueOf(FoodExpenses.Afficher());
            //TransactionList.add(HomeTransaction.Homeaff);
            Ref.child(userID).child("Food").setValue(sommeFood);
        } else {
            TransactionList = new ArrayList(set);
        }


        final ArrayAdapter arrayAdapter = new ArrayAdapter(FoodTransaction.this, android.R.layout.simple_list_item_1, TransactionList);
        listView.setAdapter(arrayAdapter);

        /*
        sommeFood = sommeFood + parseInt(sommeT);
        Recuperer FoodExpenses = new Recuperer(sommeFood);
        FoodExpenses.Afficher();

         */
    }
});



    }
}