package com.example.agb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

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
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.agb.FoodTransaction.Foodaff;

public class MyExpenses extends AppCompatActivity {

    //declaration de variables

    private ImageButton Food,Clothing,Studies,Home,Technology,Transport,Health,Groceries,Entertainment,Other;

     FirebaseUser user;

    DatabaseReference Ref;

    private String userID;

    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_expenses2);

        drawerLayout = findViewById(R.id.drawer_layout);

        //ToFirebase
        FirebaseDatabase dataBase = FirebaseDatabase.getInstance("https://app-gestion-de-budget-default-rtdb.europe-west1.firebasedatabase.app/");
        Ref = dataBase.getReference("Expenses");
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();

        // Image Button Food

        Food = findViewById(R.id.imagefood);
        Food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphaFood = new AlphaAnimation(0f, 1f);
                alphaFood.setDuration(100);
                Food.startAnimation(alphaFood);
                //end animation button
                Intent intToTran = new Intent(MyExpenses.this, FoodTransaction.class);
                startActivity(intToTran);
            }
        });

        // Image Button Clothing

        Clothing = findViewById(R.id.imageclothing);
        Clothing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphaCloth = new AlphaAnimation(0f, 1f);
                alphaCloth.setDuration(100);
                Clothing.startAnimation(alphaCloth);
                //end animation button
                Intent intToTran = new Intent(MyExpenses.this, ClothingTransaction.class);
                startActivity(intToTran);
            }
        });

        // Image Button Studies

        Studies = findViewById(R.id.imagestudies);
        Studies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphaStud = new AlphaAnimation(0f, 1f);
                alphaStud.setDuration(100);
                Studies.startAnimation(alphaStud);
                //end animation button
                Intent intToTran = new Intent(MyExpenses.this, StudiesTransaction.class);
                startActivity(intToTran);
            }
        });

        // Image Button Home

        Home = findViewById(R.id.imagehome);
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphaHome = new AlphaAnimation(0f, 1f);
                alphaHome.setDuration(100);
                Home.startAnimation(alphaHome);
                //end animation button
                Intent intToTran = new Intent(MyExpenses.this, HomeTransaction.class);
                startActivity(intToTran);
            }
        });

        // Image Button Technology

        Technology = findViewById(R.id.imagetechnology);
        Technology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphaTech = new AlphaAnimation(0f, 1f);
                alphaTech.setDuration(100);
                Technology.startAnimation(alphaTech);
                //end animation button
                Intent intToTran = new Intent(MyExpenses.this, TechnologyTransaction.class);
                startActivity(intToTran);
            }
        });

        // Image Button Transport

        Transport = findViewById(R.id.imagetransport);
        Transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphaTrans = new AlphaAnimation(0f, 1f);
                alphaTrans.setDuration(100);
                Transport.startAnimation(alphaTrans);
                //end animation button
                Intent intToTran = new Intent(MyExpenses.this, TransportTransaction.class);
                startActivity(intToTran);
            }
        });

        // Image Button Health

        Health = findViewById(R.id.imagehealth);
        Health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphaHealth = new AlphaAnimation(0f, 1f);
                alphaHealth.setDuration(100);
                Health.startAnimation(alphaHealth);
                //end animation button
                Intent intToTran = new Intent(MyExpenses.this, HealthTransaction.class);
                startActivity(intToTran);
            }
        });

        // Image Button Groceries

        Groceries = findViewById(R.id.imagegroceries);
        Groceries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphaGro = new AlphaAnimation(0f, 1f);
                alphaGro.setDuration(100);
                Groceries.startAnimation(alphaGro);
                //end animation button
                Intent intToTran = new Intent(MyExpenses.this, GroceriesTransaction.class);
                startActivity(intToTran);
            }
        });

        // Image Button Entertainment

        Entertainment = findViewById(R.id.imagejeux);
        Entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphaEnt = new AlphaAnimation(0f, 1f);
                alphaEnt.setDuration(100);
                Entertainment.startAnimation(alphaEnt);
                //end animation button
                Intent intToTran = new Intent(MyExpenses.this, EntertainmentTransaction.class);
                startActivity(intToTran);
            }
        });

        // Image Button Other

        Other = findViewById(R.id.imageothers);
        Other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphaOther = new AlphaAnimation(0f, 1f);
                alphaOther.setDuration(100);
                Other.startAnimation(alphaOther);
                //end animation button
                Intent intToTran = new Intent(MyExpenses.this, OtherTransaction.class);
                startActivity(intToTran);
            }
        });

        final TextView clothingTextView = findViewById(R.id.clothingTV);
        final TextView foodTextView = findViewById(R.id.foodTV);
        final TextView studiesTextView = findViewById(R.id.studiesTV);
        final TextView homeTextView = findViewById(R.id.homeTV);
        final TextView technologyTextView = findViewById(R.id.technologyTV);
        final TextView healthTextView = findViewById(R.id.healthTV);
        final TextView transportTextView = findViewById(R.id.transportTV);
        final TextView groceriesTextView = findViewById(R.id.groceriesTV);
        final TextView entertainementTextView = findViewById(R.id.entertainementTV);
        final TextView otherTextView = findViewById(R.id.otherTV);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        final FirebaseUser firebaseUser= auth.getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Expenses");

        // reference child
        reference.child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // New Object
                Expenses exp_user = snapshot.getValue(Expenses.class);if(exp_user != null){
                    String ExpClothing = exp_user.getClothing();
                    String ExpFood = exp_user.getFood();
                    String ExpHome = exp_user.getHome();
                    String ExpTechnology = exp_user.getTechnology();
                    String ExpTransport = exp_user.getTransport();
                    String ExpHealth = exp_user.getHealth();
                    String ExpStudies = exp_user.getStudies();
                    String ExpGroceries = exp_user.getGroceries();
                    String ExpEntertainment = exp_user.getEntertainment();
                    String ExpOther = exp_user.getOther();

                    foodTextView.setText(ExpFood);
                    clothingTextView.setText(ExpClothing);
                    homeTextView.setText(ExpHome);
                    technologyTextView.setText(ExpTechnology);
                    transportTextView.setText(ExpTransport);
                    healthTextView.setText(ExpHealth);
                    studiesTextView.setText(ExpStudies);
                    groceriesTextView.setText(ExpGroceries);
                    entertainementTextView.setText(ExpEntertainment);
                    otherTextView.setText(ExpOther);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MyExpenses.this, "Something Wrong Happened!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    // Click Menu
    public void ClickMenu(View view){
        //animation button
        AlphaAnimation alphaOpenMenu = new AlphaAnimation(0f, 1f);
        alphaOpenMenu.setDuration(100);
        findViewById(R.id.MenuClickable).startAnimation(alphaOpenMenu);
        //end animation button
        // open drawer
        openDrawer(drawerLayout);

    }

    // Method open Drawer
    public static void openDrawer(DrawerLayout drawerLayout) {
        // Open Drawer Layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    //Click Logo
    public void ClickLogo(View view){
        //animation button
        AlphaAnimation alphaCloseLogo = new AlphaAnimation(0f, 1f);
        alphaCloseLogo.setDuration(100);
        findViewById(R.id.imageView17).startAnimation(alphaCloseLogo);
        //end animation button
        // close drawer
        closeDrawer(drawerLayout);
    }

    // Method close Drawer
    public static void closeDrawer(DrawerLayout drawerLayout) {
        // close drawer layout
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    //Click Home
    public void ClickHome(View view){
        //animation button
        AlphaAnimation alphaOpenStat = new AlphaAnimation(0f, 1f);
        alphaOpenStat.setDuration(100);
        findViewById(R.id.StatisticsClickable).startAnimation(alphaOpenStat);
        //end animation button
        redirectActivity(this,statistics.class);
    }

    // Click Profile
    public void ClickProfile(View view){
        //animation button
        AlphaAnimation alphaOpenProfile = new AlphaAnimation(0f, 1f);
        alphaOpenProfile.setDuration(100);
        findViewById(R.id.ProfileClickable).startAnimation(alphaOpenProfile);
        //end animation button
        redirectActivity(this,ProfileActivity.class);
    }

    // CLick Dashboard
    public void ClickDashboard(View view){
        //animation button
        AlphaAnimation alphaOpenDash = new AlphaAnimation(0f, 1f);
        alphaOpenDash.setDuration(100);
        findViewById(R.id.DashboardClickable).startAnimation(alphaOpenDash);
        //end animation button
        redirectActivity(this,HomeActivity.class);
    }

    // Click Expenses
    public void ClickExpenses(View view){
        //animation button
        AlphaAnimation alphaOpenExpen = new AlphaAnimation(0f, 1f);
        alphaOpenExpen.setDuration(100);
        findViewById(R.id.ExpensesClickable).startAnimation(alphaOpenExpen);
        //end animation button
        redirectActivity(this,MyExpenses.class);
    }

    // Click Incomes
    public void ClickIncomes(View view){
        //animation button
        AlphaAnimation alphaOpenInco = new AlphaAnimation(0f, 1f);
        alphaOpenInco.setDuration(100);
        findViewById(R.id.IncomesClickable).startAnimation(alphaOpenInco);
        //end animation button
        redirectActivity(this,IncomesActivity.class);
    }

    //Click Know More
    public void ClickKnowMore(View view){
        //animation button
        AlphaAnimation alphaOpenKnow = new AlphaAnimation(0f, 1f);
        alphaOpenKnow.setDuration(100);
        findViewById(R.id.KnowMoreClickable).startAnimation(alphaOpenKnow);
        //end animation button
        redirectActivity(this,KnowMoreActivity.class);
    }

    //Click About Us
    public void ClickAboutUs(View view){
        //animation button
        AlphaAnimation alphaOpenAbout = new AlphaAnimation(0f, 1f);
        alphaOpenAbout.setDuration(100);
        findViewById(R.id.AboutUsClickable).startAnimation(alphaOpenAbout);
        //end animation button
        redirectActivity(this,AboutUs.class);
    }

    // Click Log Out
    public void ClickLogout(View view){
        //animation button
        AlphaAnimation alphaOpenLogOut = new AlphaAnimation(0f, 1f);
        alphaOpenLogOut.setDuration(100);
        findViewById(R.id.LogOutClickable).startAnimation(alphaOpenLogOut);
        //end animation button
        Logout(this);
    }

    // Log Out Method + dialog Box
    @SuppressLint("ResourceAsColor")
    public static void Logout(final Activity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout ?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.finishAffinity();
                System.exit(0);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });
        builder.show();

    }

    // redirectActivity : Assurant la navigabilité entre les interfaces
    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity,aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    // Method onPause
    @Override
    protected void onPause(){
        super.onPause();
        closeDrawer(drawerLayout);
    }


}