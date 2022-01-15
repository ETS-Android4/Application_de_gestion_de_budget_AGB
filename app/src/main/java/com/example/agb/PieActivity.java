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

import static com.example.agb.ClothingTransaction.Clothingaff;
import static com.example.agb.EntertainmentTransaction.Entertainmentaff;
import static com.example.agb.FoodTransaction.Foodaff;
import static com.example.agb.GroceriesTransaction.Groceriesaff;
import static com.example.agb.HealthTransaction.Healthaff;
import static com.example.agb.HomeTransaction.Homeaff;
import static com.example.agb.OtherTransaction.Otheraff;
import static com.example.agb.StudiesTransaction.Studiesaff;
import static com.example.agb.TechnologyTransaction.Technologyaff;
import static com.example.agb.TransportTransaction.Transportaff;
import static java.lang.Integer.parseInt;

public class PieActivity extends AppCompatActivity {

    //declaration de variables

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie);

        final PieChart pieChart = findViewById(R.id.pieChart);

        Integer Somme_depenses = parseInt(Clothingaff) + parseInt(Foodaff) + parseInt(Studiesaff) + parseInt(Homeaff) + parseInt(Transportaff) + parseInt(Technologyaff) + parseInt(Healthaff) + parseInt(Groceriesaff) + parseInt(Entertainmentaff) + parseInt(Otheraff);

        ArrayList<PieEntry> depenses = new ArrayList<>();
        depenses.add(new PieEntry(parseInt(Clothingaff)*100/Somme_depenses,"clothing"));
        depenses.add(new PieEntry(parseInt(Foodaff)*100/Somme_depenses,"food"));
        depenses.add(new PieEntry(parseInt(Studiesaff)*100/Somme_depenses,"studies"));
        depenses.add(new PieEntry(parseInt(Homeaff)*100/Somme_depenses,"home"));
        depenses.add(new PieEntry(parseInt(Transportaff)*100/Somme_depenses,"transport"));
        depenses.add(new PieEntry(parseInt(Technologyaff)*100/Somme_depenses,"technology"));
        depenses.add(new PieEntry(parseInt(Homeaff)*100/Somme_depenses,"health"));
        depenses.add(new PieEntry(parseInt(Groceriesaff)*100/Somme_depenses,"groceries"));
        depenses.add(new PieEntry(parseInt(Entertainmentaff)*100/Somme_depenses,"entertainement"));
        depenses.add(new PieEntry(parseInt(Otheraff)*100/Somme_depenses,"other"));

        PieDataSet pieDataSet = new PieDataSet(depenses, "Depenses");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Depenses");
        pieChart.animate();

        // Go Back To Statistics
        findViewById(R.id.pBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphap = new AlphaAnimation(0f, 1f);
                alphap.setDuration(200);
                findViewById(R.id.pBack).startAnimation(alphap);
                //end animation button
                startActivity(new Intent(getApplicationContext(), statistics.class));
            }
        });
    }
}