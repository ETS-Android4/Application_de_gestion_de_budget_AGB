package com.example.agb;


import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity {


    private Button bt_valid;
    private EditText name, last_name, PhoneNumber, Income, Savings;

    //declaration de variables

    private FirebaseUser user;


    private DatabaseReference Ref;


    private String userID;


    DrawerLayout drawerLayout;

    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawer_layout);

        myDialog = new Dialog(this);

        //findviewByID
        bt_valid = findViewById(R.id.bt_valid);
        name = findViewById(R.id.name);
        last_name = findViewById(R.id.last_name);
        PhoneNumber = findViewById(R.id.PhoneNumber);
        Income = findViewById(R.id.Income);
        Savings = findViewById(R.id.Savings);

        //get User Id
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();

        //ButtonOnClick
        bt_valid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getText
                String str = name.getText().toString().trim();
                //name.setHint(str);
                String str2 = last_name.getText().toString().trim();
                //last_name.setHint(str2);
                String str3 = PhoneNumber.getText().toString().trim();
                //PhoneNumber.setHint(str3);
                String str4 = Income.getText().toString().trim();
                //Income.setHint(str4);
                String str5 = Savings.getText().toString().trim();
                //Savings.setHint(str5);
                //newObject
                Dashboard dashboard = new Dashboard(str,str2,str3,str4,str5);
                //ToFirebase
                FirebaseDatabase dataBase = FirebaseDatabase.getInstance("https://app-gestion-de-budget-default-rtdb.europe-west1.firebasedatabase.app/");
                Ref = dataBase.getReference("Dashboard");
                user = FirebaseAuth.getInstance().getCurrentUser();
                userID = user.getUid();
                Ref.child(userID).setValue(dashboard);
                //Appel du dialog Box personnalisé
                showCustomPopUp();

            }
        });
    }

    // onClick Methods

    //Click Menu
    public void ClickMenu(View view){
        //animation button
        AlphaAnimation alphaOpenMenu = new AlphaAnimation(0f, 1f);
        alphaOpenMenu.setDuration(100);
        findViewById(R.id.MenuClickable).startAnimation(alphaOpenMenu);
        //end animation button
        MyExpenses.openDrawer(drawerLayout);
    }

    // Click LogOut
    public void ClickLogo(View view){
        //animation button
        AlphaAnimation alphaCloseLogo = new AlphaAnimation(0f, 1f);
        alphaCloseLogo.setDuration(100);
        findViewById(R.id.imageView17).startAnimation(alphaCloseLogo);
        //end animation button
        MyExpenses.closeDrawer(drawerLayout);
    }

    //Click Home
    public void ClickHome(View view){
        //animation button
        AlphaAnimation alphaOpenStat = new AlphaAnimation(0f, 1f);
        alphaOpenStat.setDuration(100);
        findViewById(R.id.StatisticsClickable).startAnimation(alphaOpenStat);
        //end animation button
        MyExpenses.redirectActivity(this,statistics.class);
    }

    //Click Incomes
    public void ClickIncomes(View view){
        //animation button
        AlphaAnimation alphaOpenInco = new AlphaAnimation(0f, 1f);
        alphaOpenInco.setDuration(100);
        findViewById(R.id.IncomesClickable).startAnimation(alphaOpenInco);
        //end animation button
        MyExpenses.redirectActivity(this,IncomesActivity.class);
    }

    //Click Profile
    public void ClickProfile(View view){
        //animation button
        AlphaAnimation alphaOpenProfile = new AlphaAnimation(0f, 1f);
        alphaOpenProfile.setDuration(100);
        findViewById(R.id.ProfileClickable).startAnimation(alphaOpenProfile);
        //end animation button
        MyExpenses.redirectActivity(this,ProfileActivity.class);
    }

    // Click Dashboard
    public void ClickDashboard(View view){
        //animation button
        AlphaAnimation alphaOpenDash = new AlphaAnimation(0f, 1f);
        alphaOpenDash.setDuration(100);
        findViewById(R.id.DashboardClickable).startAnimation(alphaOpenDash);
        //end animation button
        recreate();
    }

    // Click Expenses
    public void ClickExpenses(View view){
        //animation button
        AlphaAnimation alphaOpenExpen = new AlphaAnimation(0f, 1f);
        alphaOpenExpen.setDuration(100);
        findViewById(R.id.ExpensesClickable).startAnimation(alphaOpenExpen);
        //end animation button
        MyExpenses.redirectActivity(this,MyExpenses.class);
    }

    //Click Know Morte
    public void ClickKnowMore(View view){
        //animation button
        AlphaAnimation alphaOpenKnow = new AlphaAnimation(0f, 1f);
        alphaOpenKnow.setDuration(100);
        findViewById(R.id.KnowMoreClickable).startAnimation(alphaOpenKnow);
        //end animation button
        MyExpenses.redirectActivity(this,KnowMoreActivity.class);
    }

    //Click About Us
    public void ClickAboutUs(View view){
        //animation button
        AlphaAnimation alphaOpenAbout = new AlphaAnimation(0f, 1f);
        alphaOpenAbout.setDuration(100);
        findViewById(R.id.AboutUsClickable).startAnimation(alphaOpenAbout);
        //end animation button
        MyExpenses.redirectActivity(this,AboutUs.class);
    }

    // Click About Us
    public void ClickLogout(View view){
        //animation button
        AlphaAnimation alphaOpenLogOut = new AlphaAnimation(0f, 1f);
        alphaOpenLogOut.setDuration(100);
        findViewById(R.id.LogOutClickable).startAnimation(alphaOpenLogOut);
        //end animation button
        MyExpenses.Logout(this);
    }

    // Method onPause
    @Override
    protected void onPause(){
        super.onPause();
        MyExpenses.closeDrawer(drawerLayout);
    }

    // Method showCustomPoP: Creation du dialog box Personnalisé (Partie Code Java).

    @SuppressLint("ResourceAsColor")
    public void showCustomPopUp(){

        myDialog.setContentView(R.layout.custom_dialog);

        TextView txt;
        final Button exitBtn;

        txt = myDialog.findViewById(R.id.txt);
        exitBtn = myDialog.findViewById(R.id.exitBtn);

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(R.color.colorTransparent));
        myDialog.setCancelable(false);
        myDialog.setCanceledOnTouchOutside(false);

        myDialog.show();
    }
}