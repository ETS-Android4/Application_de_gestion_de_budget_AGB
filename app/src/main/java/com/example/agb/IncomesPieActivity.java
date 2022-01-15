package com.example.agb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.agb.BusinessTransaction.Businessaff;
import static com.example.agb.OtherIncomesTransaction.OtherIaff;
import static com.example.agb.RealEstateTransaction.RealEaff;
import static com.example.agb.SalaryTransaction.Salaryaff;
import static java.lang.Integer.parseInt;

public class IncomesPieActivity extends AppCompatActivity {

    //declaration de variables

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incomes_pie);

        final PieChart pieChart = findViewById(R.id.pieChart);

        final Integer Somme_incomes = parseInt(Businessaff) + parseInt(RealEaff) + parseInt(Salaryaff) + parseInt(OtherIaff);

        ArrayList<PieEntry> incomes = new ArrayList<>();
        incomes.add(new PieEntry(parseInt(Businessaff)*100/Somme_incomes,"Buisness"));
        incomes.add(new PieEntry(parseInt(RealEaff)*100/Somme_incomes,"Real Estate"));
        incomes.add(new PieEntry(parseInt(Salaryaff)*100/Somme_incomes,"Salary"));
        incomes.add(new PieEntry(parseInt(OtherIaff)*100/Somme_incomes,"Other"));


        PieDataSet pieDataSet = new PieDataSet(incomes, "Incomes");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Incomes");
        pieChart.animate();

        /*

        // Firebase get Id

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance("https://app-gestion-de-budget-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Incomes");
        userID = user.getUid();

        // reference child
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Incomes userProfile = dataSnapshot.getValue(Incomes.class);
                if(userProfile != null){
                    String Busness =  userProfile.Buisness;
                    String realEstate = userProfile.RealEstate;
                    String Salary = userProfile.Salary;
                    String Other = userProfile.Other;

                    final Integer Somme_incomes = parseInt(Busness) + parseInt(realEstate) + parseInt(Salary) + parseInt(Other);

                    ArrayList<PieEntry> incomes = new ArrayList<>();
                    incomes.add(new PieEntry(parseInt(Busness)*100/Somme_incomes,"Buisness"));
                    incomes.add(new PieEntry(parseInt(realEstate)*100/Somme_incomes,"Real Estate"));
                    incomes.add(new PieEntry(parseInt(Salary)*100/Somme_incomes,"Salary"));
                    incomes.add(new PieEntry(parseInt(Other)*100/Somme_incomes,"Other"));


                    PieDataSet pieDataSet = new PieDataSet(incomes, "Incomes");
                    pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                    pieDataSet.setValueTextColor(Color.BLACK);
                    pieDataSet.setValueTextSize(16f);

                    PieData pieData = new PieData(pieDataSet);

                    pieChart.setData(pieData);
                    pieChart.getDescription().setEnabled(false);
                    pieChart.setCenterText("Incomes");
                    pieChart.animate();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(IncomesPieActivity.this, "Something Wrong Happened!", Toast.LENGTH_SHORT).show();
            }
        });

         */

        findViewById(R.id.piBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphapi = new AlphaAnimation(0f, 1f);
                alphapi.setDuration(100);
                findViewById(R.id.piBack).startAnimation(alphapi);
                //end animation button
                startActivity(new Intent(getApplicationContext(), statistics.class));
            }
        });
    }
}