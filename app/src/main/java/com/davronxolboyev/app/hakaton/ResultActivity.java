package com.davronxolboyev.app.hakaton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class ResultActivity extends AppCompatActivity {

    private PieChart chart;
    @SuppressLint("StaticFieldLeak")
    public static TextView fullname;
    private int it;
    private int art;
    private int business;
    private int technician;

    AppCompatButton backButton;
    TextView resIt;
    TextView resArt;
    TextView resBus;
    TextView resTech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resIt = findViewById(R.id.restextIT);
        resArt = findViewById(R.id.restextArt);
        resBus = findViewById(R.id.restextBusiness);
        resTech = findViewById(R.id.restextTech);
        fullname = findViewById(R.id.resName);
        fullname.setText(RegisterActivity.fullname);
        chart = findViewById(R.id.pieChart);
        backButton = findViewById(R.id.backBtn);

        Bundle bundle = getIntent().getExtras();
        //Qiymatlarni qabul qilish
        it = bundle.getInt("IT");
        art = bundle.getInt("ART");
        business = bundle.getInt("BUSINESS");
        technician = bundle.getInt("TECHNICIAN");

        resIt.append(" - " + it + " ball");
        resArt.append(" - " + art + " ball");
        resBus.append(" - " + business + " ball");
        resTech.append(" - " + technician + " ball");

        StartPieChart();

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    //Diagrammani hosil qilish
    private void StartPieChart() {

        chart.addPieSlice(new PieModel("IT", it, Color.parseColor("#D80000")));
        chart.addPieSlice(new PieModel("ART", art, Color.parseColor("#EEFF00")));
        chart.addPieSlice(new PieModel("BUSINSESS", business, Color.parseColor("#029709")));
        chart.addPieSlice(new PieModel("TECHNICIAN", technician, Color.parseColor("#00FFE9")));
        chart.setInnerPaddingColor(Color.parseColor("#1488CC"));
        chart.startAnimation();

    }
}