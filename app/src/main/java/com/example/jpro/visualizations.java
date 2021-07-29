package com.example.jpro;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class visualizations extends AppCompatActivity {
    int countmale=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //from database
        Query query = FirebaseDatabase.getInstance().getReference("Citizens").orderByChild("gender")
                .startAt("Male").endAt("Male"+"\uf8ff");
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            countmale = (int) dataSnapshot.getChildrenCount();
                            Toast.makeText(visualizations.this, "Number of males are "+countmale, Toast.LENGTH_SHORT).show();
                            Log.d("males", "onDataChange: "+countmale);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
        setContentView(R.layout.activity_visualizations);
        BarChart barChart = findViewById(R.id.barchart);

        ArrayList<BarEntry> visitors = new ArrayList<>();
        visitors.add(new BarEntry(2010,countmale));
        visitors.add(new BarEntry(2011, 60));
        visitors.add(new BarEntry(2012, 10));
        visitors.add(new BarEntry(2013,30));

        BarDataSet barDataSet = new BarDataSet(visitors,"Visitors");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Bar Chart Example");
        barChart.animateY(2000);





        PieChart pieChart = findViewById(R.id.pieChart);
        ArrayList<PieEntry> Pievistors = new ArrayList<>();
        Pievistors.add(new PieEntry(countmale,2010));
        Pievistors.add(new PieEntry(60, 2011));
        Pievistors.add(new PieEntry(10, 2012));
        Pievistors.add(new PieEntry(30,2013));

        PieDataSet pieDataSet = new PieDataSet(Pievistors,"Visitors");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setText("Visitors");
        pieChart.animate();


    }
}