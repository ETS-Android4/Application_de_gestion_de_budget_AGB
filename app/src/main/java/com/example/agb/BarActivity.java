package com.example.agb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
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

public class BarActivity extends AppCompatActivity {

    //declaration de variables

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);

        // Bar Chart

        final BarChart barChart = findViewById(R.id.barChart);

        Integer Somme_depenses = parseInt(Clothingaff) + parseInt(Foodaff) + parseInt(Studiesaff) + parseInt(Homeaff) + parseInt(Transportaff) + parseInt(Technologyaff) + parseInt(Healthaff) + parseInt(Groceriesaff) + parseInt(Entertainmentaff) + parseInt(Otheraff);

        ArrayList<BarEntry> depenses = new ArrayList<>();
        depenses.add(new BarEntry(6,420));
        depenses.add(new BarEntry(7,475));
        depenses.add(new BarEntry(8,508));
        depenses.add(new BarEntry(9,660));
        depenses.add(new BarEntry(10,550));
        depenses.add(new BarEntry(11,630));
        depenses.add(new BarEntry(12,Somme_depenses));

        BarDataSet barDataSet = new BarDataSet(depenses, "Depenses");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Bar Chart Example");
        barChart.animateY(2000);


        //Go Bach To Statistics
        findViewById(R.id.bBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alphab = new AlphaAnimation(0f, 1f);
                alphab.setDuration(100);
                findViewById(R.id.bBack).startAnimation(alphab);
                //end animation button
                startActivity(new Intent(getApplicationContext(), statistics.class));
            }
        });

    }
}
