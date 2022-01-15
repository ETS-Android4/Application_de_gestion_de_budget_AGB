package com.example.agb;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class statistics extends AppCompatActivity {

   private ImageButton BarInfo;
   Dialog myBarDialog;

    private ImageButton PieInfo;
    Dialog myPieDialog;

    private ImageButton RadarInfo;
    Dialog myRadarDialog;

    private ImageButton PieIncomesInfo;
    Dialog myPieIncomesDialog;

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //CollapsingToolbarLayout toolBarLayout = findViewById(R.id.toolbar_layout);
        //toolBarLayout.setTitle(getTitle());

        drawerLayout = findViewById(R.id.drawer_layout);

        BarInfo = findViewById(R.id.info_bar);
        myBarDialog = new Dialog(this);

        BarInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBarPopUp();
                //animation button
                AlphaAnimation alphaBarInfo = new AlphaAnimation(0f, 1f);
                alphaBarInfo.setDuration(100);
                BarInfo.startAnimation(alphaBarInfo);
                //end animation button
            }
        });

        PieInfo = findViewById(R.id.info_pie_expenses);
        myPieDialog = new Dialog(this);

        PieInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphaPieInfo = new AlphaAnimation(0f, 1f);
                alphaPieInfo.setDuration(100);
                PieInfo.startAnimation(alphaPieInfo);
                //end animation button
                showPiePopUp();
            }
        });

        RadarInfo = findViewById(R.id.info_radar);
        myRadarDialog = new Dialog(this);

        RadarInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphaRadarInfo = new AlphaAnimation(0f, 1f);
                alphaRadarInfo.setDuration(100);
                RadarInfo.startAnimation(alphaRadarInfo);
                //end animation button
                showRadarPopUp();
            }
        });

        PieIncomesInfo = findViewById(R.id.info_pie_incomes);
        myPieIncomesDialog = new Dialog(this);

        PieIncomesInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphaPieIncomesInfo = new AlphaAnimation(0f, 1f);
                alphaPieIncomesInfo.setDuration(100);
                PieIncomesInfo.startAnimation(alphaPieIncomesInfo);
                //end animation button
                showPieIncomesPopUp();
            }
        });


        findViewById(R.id.buttonBarChart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphaBarChart = new AlphaAnimation(0f, 1f);
                alphaBarChart.setDuration(100);
                findViewById(R.id.buttonBarChart).startAnimation(alphaBarChart);
                //end animation button
                Intent intToBar = new Intent(statistics.this, BarActivity.class);
                startActivity(intToBar);
            }
        });

        findViewById(R.id.buttonPieChartExpenses).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphaPieChart = new AlphaAnimation(0f, 1f);
                alphaPieChart.setDuration(100);
                findViewById(R.id.buttonPieChartExpenses).startAnimation(alphaPieChart);
                //end animation button
                Intent intToPie = new Intent(statistics.this, PieActivity.class);
                startActivity(intToPie);
            }
        });

        findViewById(R.id.buttonRadarChart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphaRadarChart = new AlphaAnimation(0f, 1f);
                alphaRadarChart.setDuration(100);
                findViewById(R.id.buttonRadarChart).startAnimation(alphaRadarChart);
                //end animation button
                Intent intToRadar = new Intent(statistics.this, RadarActivity.class);
                startActivity(intToRadar);
            }
        });

        findViewById(R.id.buttonPieChart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphaPieChart = new AlphaAnimation(0f, 1f);
                alphaPieChart.setDuration(100);
                findViewById(R.id.buttonPieChart).startAnimation(alphaPieChart);
                //end animation button
                Intent intToPieIncomes = new Intent(statistics.this, IncomesPieActivity.class);
                startActivity(intToPieIncomes);
            }
        });

    }
//Info Bar
    @SuppressLint("ResourceAsColor")
    public void showBarPopUp(){

        myBarDialog.setContentView(R.layout.bar_info);

        TextView txtBar;
        Button exitBtnBar;

        txtBar = myBarDialog.findViewById(R.id.txtBar);
        exitBtnBar = myBarDialog.findViewById(R.id.exitBtnBar);

        exitBtnBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myBarDialog.dismiss();
            }
        });

        myBarDialog.getWindow().setBackgroundDrawable(new ColorDrawable(R.color.colorTransparent));
        myBarDialog.setCancelable(false);
        myBarDialog.setCanceledOnTouchOutside(false);

        myBarDialog.show();
    }
    //Pie Info Expenses
    @SuppressLint("ResourceAsColor")
    public void showPiePopUp(){

        myPieDialog.setContentView(R.layout.pie_info);

        TextView txtBar;
        Button exitBtnBar;

        txtBar = myPieDialog.findViewById(R.id.txtBar);
        exitBtnBar = myPieDialog.findViewById(R.id.exitBtnBar);

        exitBtnBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myPieDialog.dismiss();
            }
        });

        myPieDialog.getWindow().setBackgroundDrawable(new ColorDrawable(R.color.colorTransparent));
        myPieDialog.setCancelable(false);
        myPieDialog.setCanceledOnTouchOutside(false);

        myPieDialog.show();
    }
    //Radar Info Expenses
    @SuppressLint("ResourceAsColor")
    public void showRadarPopUp(){

        myRadarDialog.setContentView(R.layout.radar_info);

        TextView txtBar;
        Button exitBtnBar;

        txtBar = myRadarDialog.findViewById(R.id.txtBar);
        exitBtnBar = myRadarDialog.findViewById(R.id.exitBtnBar);

        exitBtnBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRadarDialog.dismiss();
            }
        });

        myRadarDialog.getWindow().setBackgroundDrawable(new ColorDrawable(R.color.colorTransparent));
        myRadarDialog.setCancelable(false);
        myRadarDialog.setCanceledOnTouchOutside(false);

        myRadarDialog.show();
    }
    //Pie Incomes Info
    @SuppressLint("ResourceAsColor")
    public void showPieIncomesPopUp(){

        myPieIncomesDialog.setContentView(R.layout.pie_incomes_info);

        TextView txtBar;
        Button exitBtnBar;

        txtBar = myPieIncomesDialog.findViewById(R.id.txtBar);
        exitBtnBar = myPieIncomesDialog.findViewById(R.id.exitBtnBar);

        exitBtnBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myPieIncomesDialog.dismiss();
            }
        });

        myPieIncomesDialog.getWindow().setBackgroundDrawable(new ColorDrawable(R.color.colorTransparent));
        myPieIncomesDialog.setCancelable(false);
        myPieIncomesDialog.setCanceledOnTouchOutside(false);

        myPieIncomesDialog.show();
    }

    //OnClick Methods

    //Click Menu
    public void ClickMenu(View view){
        //animation button
        AlphaAnimation alphaOpenMenu = new AlphaAnimation(0f, 1f);
        alphaOpenMenu.setDuration(100);
        findViewById(R.id.MenuClickable).startAnimation(alphaOpenMenu);
        //end animation button
        MyExpenses.openDrawer(drawerLayout);
    }

    //Click Logo
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

    // Click Profile
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
        MyExpenses.redirectActivity(this,HomeActivity.class);
    }

    // Clixk Expenses
    public void ClickExpenses(View view){
        //animation button
        AlphaAnimation alphaOpenExpen = new AlphaAnimation(0f, 1f);
        alphaOpenExpen.setDuration(100);
        findViewById(R.id.ExpensesClickable).startAnimation(alphaOpenExpen);
        //end animation button
        MyExpenses.redirectActivity(this,MyExpenses.class);
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

    //Click About Us
    public void ClickAboutUs(View view){
        //animation button
        AlphaAnimation alphaOpenAbout = new AlphaAnimation(0f, 1f);
        alphaOpenAbout.setDuration(100);
        findViewById(R.id.AboutUsClickable).startAnimation(alphaOpenAbout);
        //end animation button
        MyExpenses.redirectActivity(this,AboutUs.class);
    }

    // Click LogOut
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


}
