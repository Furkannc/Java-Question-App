package com.example.sorubankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class ResultActivity extends AppCompatActivity {

    TextView txtTrue,txtFalse,txtPercentageTrue,txtPercentageFalse;
    PieChart pieChart;
    Button btnRes;
    int totalQuetion=20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        pieChart=findViewById(R.id.piechart);
        txtFalse=findViewById(R.id.txtFalse);
        txtTrue=findViewById(R.id.txtTrue);
        txtPercentageFalse=findViewById(R.id.txtPercentageFalse);
        txtPercentageTrue=findViewById(R.id.txtPercentageTrue);
        btnRes=findViewById(R.id.btnRestart);

        Intent intent=getIntent();
        int trueAnswer=intent.getIntExtra("trueAnswers",-1);
        int falseAnswer=totalQuetion-trueAnswer;
        slicePie(trueAnswer,falseAnswer);

        txtTrue.setText(String.valueOf(trueAnswer));
        txtFalse.setText(String.valueOf(falseAnswer));
        Log.d("TAG", "onCreate: "+"%"+trueAnswer*5);
        txtPercentageTrue.setText("%"+ trueAnswer * 5);
        txtPercentageFalse.setText("%"+ falseAnswer * 5);

        btnRes.setOnClickListener(view -> {
            Intent goToMain=new Intent(this,MainActivity.class);
            startActivity(goToMain);
            this.finish();
        });
    }

    void slicePie(int trueAnswer,int falseAnswer){


        pieChart.addPieSlice(
                new PieModel(
                        "False ",
                        falseAnswer,
                        Color.parseColor("#F94C66")));
        pieChart.addPieSlice(
                new PieModel(
                        "True ",
                        trueAnswer,
                        Color.parseColor("#5FD068")));

        // To animate the pie chart
        pieChart.startAnimation();
    }
}