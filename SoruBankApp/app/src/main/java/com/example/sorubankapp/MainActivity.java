package com.example.sorubankapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;

import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sorubankapp.Api.ApiClient;
import com.example.sorubankapp.Api.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<Boolean> answerList= new ArrayList<>();
    TextView txtQuestion,txtCategory;
    Button btnA,btnB,btnC,btnD;
    String cvp;
    RecyclerView recyclerView;
    Quiz quiz;
    int index=-1;
    ItemAdapter itemAdapter;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar=findViewById(R.id.progress_circular);
        recyclerView = findViewById(R.id.rcView);
        txtQuestion = findViewById(R.id.txtQuestion);
        txtCategory = findViewById(R.id.txtCategory);
        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);
        recyclerView= findViewById(R.id.rcView);

        ApiInterface apiService= ApiClient.getRetrofit().create(ApiInterface.class);//Created api service

        Call<Quiz> call=apiService.getQuizzes();

        call.enqueue(new Callback<Quiz>() {
            @Override
            public void onResponse(@NonNull Call<Quiz> call, @NonNull Response<Quiz> response) {
                quiz=response.body();
                getQuiz();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(@NonNull Call<Quiz> call, @NonNull Throwable t) {
                Log.d("TAG", "onFailure: "+t);
            }
        });


        btnA.setOnClickListener(view -> {

            if(btnA.getText().equals(cvp)){
                answerList.add(true);
            }
            else{
                answerList.add(false);
            }
            getQuiz();

        });

        btnB.setOnClickListener(view -> {
            if(btnB.getText().equals(cvp)){
                answerList.add(true);
            }
            else{
                answerList.add(false);
            }
            getQuiz();

        });

        btnC.setOnClickListener(view -> {
            if(btnC.getText().equals(cvp)){
                answerList.add(true);
            }
            else{
                answerList.add(false);
            }
            getQuiz();

        });

        btnD.setOnClickListener(view -> {
            if(btnD.getText().equals(cvp)){
                answerList.add(true);
            }
            else{
                answerList.add(false);
            }
            getQuiz();

        });
    }

     void getQuiz(){
            index++;

            if(index==20)
                goToResult();
            else{

                getData();

                txtQuestion.setText((index+1)+"."+quiz.getResults().get(index).getQuestion());
                txtCategory.setText(quiz.getResults().get(index).getCategory());

                List<String> myAnswerList=quiz.getResults().get(index).getAllAnswers();

                btnA.setText(myAnswerList.get(0));
                btnB.setText(myAnswerList.get(1));
                btnC.setText(myAnswerList.get(2));
                btnD.setText(myAnswerList.get(3));

                cvp=quiz.getResults().get(index).getCorrectAnswer();
            }
    }

    void goToResult(){

        int trueAnswer=0;
        Intent intent=new Intent(this,ResultActivity.class);
        for (Boolean aBool : answerList) {
            if(aBool)
                trueAnswer++;
        }
        intent.putExtra("trueAnswers",trueAnswer);
        startActivity(intent);

        this.finish();
    }

     void getData(){
        itemAdapter = new ItemAdapter(answerList);
        recyclerView.setAdapter(itemAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
