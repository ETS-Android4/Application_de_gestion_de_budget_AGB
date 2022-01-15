package com.example.agb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class IncomesActivity extends AppCompatActivity {

    //private Button IncomeButton;
    //private TextView Buisness, Salary, realEstate, otherIncome;

    private ImageButton Business, SalaryIm, RealEstate, OtherIn;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;


    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incomes);

        drawerLayout = findViewById(R.id.drawer_layout);

        // Image Button Business

        Business = findViewById(R.id.imageBusiness);
        Business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphaBusiness = new AlphaAnimation(0f, 1f);
                alphaBusiness.setDuration(100);
                Business.startAnimation(alphaBusiness);
                //end animation button
                Intent intToTran = new Intent(IncomesActivity.this, BusinessTransaction.class);
                startActivity(intToTran);
            }
        });

        // Image Button RealEstate

        RealEstate = findViewById(R.id.imageInvestment);
        RealEstate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphaRealEstate = new AlphaAnimation(0f, 1f);
                alphaRealEstate.setDuration(100);
                RealEstate.startAnimation(alphaRealEstate);
                //end animation button
                Intent intToTran = new Intent(IncomesActivity.this, RealEstateTransaction.class);
                startActivity(intToTran);
            }
        });

        // Image Button Salary

        SalaryIm = findViewById(R.id.imageSalary);
        SalaryIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphaSalaryIm = new AlphaAnimation(0f, 1f);
                alphaSalaryIm.setDuration(100);
                SalaryIm.startAnimation(alphaSalaryIm);
                //end animation button
                Intent intToTran = new Intent(IncomesActivity.this, SalaryTransaction.class);
                startActivity(intToTran);
            }
        });

        // Image Button Other Incomes

        OtherIn = findViewById(R.id.imageRent);
        OtherIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphaOtherIn = new AlphaAnimation(0f, 1f);
                alphaOtherIn.setDuration(100);
                OtherIn.startAnimation(alphaOtherIn);
                //end animation button
                Intent intToTran = new Intent(IncomesActivity.this, OtherIncomesTransaction.class);
                startActivity(intToTran);
            }
        });

        //FindViewById
        //IncomeButton = findViewById(R.id.validIncome);

        final TextView BuisnessTV = findViewById(R.id.businessTV);
        final TextView SalaryTV =findViewById(R.id.salaryTV);
        final TextView realEstateTV = findViewById(R.id.real_estateTV);
        final TextView otherIncomeTV = findViewById(R.id.otherIncomeTV);

        //get User Id
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance("https://app-gestion-de-budget-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Incomes");
        userID = user.getUid();

        // reference child
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Incomes userIncomes = dataSnapshot.getValue(Incomes.class);
                if(userIncomes != null){
                    String Business = userIncomes.Buisness;
                    String RealEstate = userIncomes.RealEstate;
                    String Salary = userIncomes.Salary;
                    String OtherIncomes = userIncomes.Other;

                    BuisnessTV.setText(Business);
                    SalaryTV.setText(Salary);
                    realEstateTV.setText(RealEstate);
                    otherIncomeTV.setText(OtherIncomes);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(IncomesActivity.this, "Something Wrong Happened!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    // onClick Methods

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

    //Click Know More
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

    //Click Log Out
    public void ClickLogout(View view){
        //animation button
        AlphaAnimation alphaOpenLogOut = new AlphaAnimation(0f, 1f);
        alphaOpenLogOut.setDuration(100);
        findViewById(R.id.LogOutClickable).startAnimation(alphaOpenLogOut);
        //end animation button
        MyExpenses.Logout(this);
    }

    // Click onPause
    @Override
    protected void onPause(){
        super.onPause();
        MyExpenses.closeDrawer(drawerLayout);
    }

}