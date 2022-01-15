package com.example.agb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;


import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;

public class AboutUs extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        drawerLayout = findViewById(R.id.drawer_layout);
    }

    // OnClick Methods

    // Click Menu
    public void ClickMenu(View view){
        //animation button
        AlphaAnimation alphaOpenMenu = new AlphaAnimation(0f, 1f);
        alphaOpenMenu.setDuration(100);
        findViewById(R.id.MenuClickable).startAnimation(alphaOpenMenu);
        //end animation button
        MyExpenses.openDrawer(drawerLayout);
    }

    // Click Logo
    public void ClickLogo(View view){
        //animation button
        AlphaAnimation alphaCloseLogo = new AlphaAnimation(0f, 1f);
        alphaCloseLogo.setDuration(100);
        findViewById(R.id.imageView17).startAnimation(alphaCloseLogo);
        //end animation button
        MyExpenses.closeDrawer(drawerLayout);
    }

    // Click Home
    public void ClickHome(View view){
        //animation button
        AlphaAnimation alphaOpenStat = new AlphaAnimation(0f, 1f);
        alphaOpenStat.setDuration(100);
        findViewById(R.id.StatisticsClickable).startAnimation(alphaOpenStat);
        //end animation button
        MyExpenses.redirectActivity(this,statistics.class);
    }

    // Click Know More
    public void ClickKnowMore(View view){
        //animation button
        AlphaAnimation alphaOpenKnow = new AlphaAnimation(0f, 1f);
        alphaOpenKnow.setDuration(100);
        findViewById(R.id.KnowMoreClickable).startAnimation(alphaOpenKnow);
        //end animation button
        MyExpenses.redirectActivity(this,KnowMoreActivity.class);
    }

    // Click Incomes
    public void ClickIncomes(View view){
        //animation button
        AlphaAnimation alphaOpenInco = new AlphaAnimation(0f, 1f);
        alphaOpenInco.setDuration(100);
        findViewById(R.id.IncomesClickable).startAnimation(alphaOpenInco);
        //end animation button
        MyExpenses.redirectActivity(this,IncomesActivity.class);
    }

    // Click Profile
    public void ClickProfile(View view){
        //animation button
        AlphaAnimation alphaOpenProfile = new AlphaAnimation(0f, 1f);
        alphaOpenProfile.setDuration(100);
        findViewById(R.id.ProfileClickable).startAnimation(alphaOpenProfile);
        //end animation button
        MyExpenses.redirectActivity(this,ProfileActivity.class);
    }

   // Click Dashbpoard
    public void ClickDashboard(View view){
        //animation button
        AlphaAnimation alphaOpenDash = new AlphaAnimation(0f, 1f);
        alphaOpenDash.setDuration(100);
        findViewById(R.id.DashboardClickable).startAnimation(alphaOpenDash);
        //end animation button
        MyExpenses.redirectActivity(this,HomeActivity.class);
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

    // Click AboutUs
    public void ClickAboutUs(View view){
        //animation button
        AlphaAnimation alphaOpenAbout = new AlphaAnimation(0f, 1f);
        alphaOpenAbout.setDuration(100);
        findViewById(R.id.AboutUsClickable).startAnimation(alphaOpenAbout);
        //end animation button
        recreate();
    }

    //Click LogOut
    public void ClickLogout(View view){
        //animation button
        AlphaAnimation alphaOpenLogOut = new AlphaAnimation(0f, 1f);
        alphaOpenLogOut.setDuration(100);
        findViewById(R.id.LogOutClickable).startAnimation(alphaOpenLogOut);
        //end animation button
        MyExpenses.Logout(this);
    }

    //Method onPause
    @Override
    protected void onPause(){
        super.onPause();
        MyExpenses.closeDrawer(drawerLayout);
    }
}