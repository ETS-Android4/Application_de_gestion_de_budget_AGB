package com.example.agb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Toast;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
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

public class RadarActivity extends AppCompatActivity {

    //declaration de variables

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar);

        final RadarChart radarChart = findViewById(R.id.radarChart);

        //radar
        final RadarData radarData = new RadarData();
        final String[] labels = {"clothing", "health", "transportation", "studies", "technology", "other", "food", "home", "entertainement", "groceries"};

        ArrayList<RadarEntry> visitorsForFirstWebsite = new ArrayList<>();
        visitorsForFirstWebsite.add(new RadarEntry(parseInt(Clothingaff)));
        visitorsForFirstWebsite.add(new RadarEntry(parseInt(Healthaff)));
        visitorsForFirstWebsite.add(new RadarEntry(parseInt(Transportaff)));
        visitorsForFirstWebsite.add(new RadarEntry(parseInt(Studiesaff)));
        visitorsForFirstWebsite.add(new RadarEntry(parseInt(Technologyaff)));
        visitorsForFirstWebsite.add(new RadarEntry(parseInt(Otheraff)));
        visitorsForFirstWebsite.add(new RadarEntry(parseInt(Foodaff)));
        visitorsForFirstWebsite.add(new RadarEntry(parseInt(Homeaff)));
        visitorsForFirstWebsite.add(new RadarEntry(parseInt(Entertainmentaff)));
        visitorsForFirstWebsite.add(new RadarEntry(parseInt(Groceriesaff)));

        RadarDataSet radarDataSetForFirstWebsite = new RadarDataSet(visitorsForFirstWebsite, "Website 1");
        radarDataSetForFirstWebsite.setColor(Color.RED);
        radarDataSetForFirstWebsite.setLineWidth(2f);
        radarDataSetForFirstWebsite.setValueTextColor(Color.RED);
        radarDataSetForFirstWebsite.setValueTextSize(14f);

        radarData.addDataSet(radarDataSetForFirstWebsite);

        XAxis xAxis = radarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

        radarChart.setData(radarData);

        //Go Bach To Statistics
        findViewById(R.id.rBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animation button
                AlphaAnimation alpharadar = new AlphaAnimation(0f, 1f);
                alpharadar.setDuration(100);
                findViewById(R.id.rBack).startAnimation(alpharadar);
                //end animation button
                startActivity(new Intent(getApplicationContext(), statistics.class));
            }
        });

    }

}

