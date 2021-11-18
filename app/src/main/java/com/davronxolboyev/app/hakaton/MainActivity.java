/* Xolboyev Davron
    Date : 02.10.2021
 */
package com.davronxolboyev.app.hakaton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.davronxolboyev.app.hakaton.model.JsonPlaceHolder;
import com.davronxolboyev.app.hakaton.model.Questions;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    AppCompatButton next;

    TextView question;
    TextView count;

    int id = 0;
    int sizequestion;
    int resIT = 0;
    int resArt = 0;
    int resBusiness = 0;
    int resTechnician = 0;
    int ID;

    String title;
    String category;
    public static boolean info = true;
    private static final String BASE_URL = "https://questions01.herokuapp.com/";

    LinearLayout variantA;
    LinearLayout variantB;
    LinearLayout variantC;
    LinearLayout variantD;
    private String textCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question = findViewById(R.id.question);
        next = findViewById(R.id.nextBtn);
        variantA = findViewById(R.id.variantA);
        variantB = findViewById(R.id.variantB);
        variantC = findViewById(R.id.variantC);
        variantD = findViewById(R.id.variantD);
        count = findViewById(R.id.counQuestion);
        count.setVisibility(View.VISIBLE);

        variantsClick();

        // Savol boshlashdan oldingi xabar
        if (info){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.drawable.ic_baseline_info_24)
                    .setTitle("Information")
                    .setMessage("The goal of our project is to determine what area you are interested in!\n\n\nRemember to turn on the INTERNET!")
                    .setPositiveButton("OK", (dialog, which) -> dialog.cancel());


            AlertDialog dialog = builder.create();
            dialog.show();
            info = false;
        }

        // Bazadan savolni olish
        addQuestions();
    }

    private void addQuestions() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        Call<List<Questions>> call = jsonPlaceHolder.getQuestions();

        call.enqueue(new Callback<List<Questions>>() {
            @Override
            public void onResponse(Call<List<Questions>> call, Response<List<Questions>> response) {
                if (!response.isSuccessful()) {
                    question.setText(response.code());
                    return;
                }
                List<Questions> questions = response.body();
                if (questions != null) {
                    sizequestion = questions.size();
                    title = "" + questions.get(id).getTitle();
                    category = "" + questions.get(id).getCategory();

                    // Nechanchi savolga kelganlikni aniqlash
                    textCount  = "" + (id+1) + "/" + sizequestion;
                    count.setText(textCount);
                    question.setText(title);

                    //Keyingi savolga o`tishi
                    next.setOnClickListener(v -> {

                        // Qiymatlarni olish jarayoni
                        addValue(category,ID);

                        id++;

                        // Nechanchi savolga kelganlikni aniqlash
                        textCount  = "" + (id+1)+ "/" + sizequestion;
                        count.setText(textCount);

                        if (id < sizequestion){
                            title = null;
                            category = null;
                            title = "" + questions.get(id).getTitle();
                            category = "" + questions.get(id).getCategory();
                            question.setText(title);

                            // Qiymat qo`shib ketish
                            variantA.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                            variantB.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                            variantC.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                            variantD.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                        }else {
                            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                            Log.d("result:", resIT + " " + resArt + " " + resBusiness + " " + resTechnician);
                            intent.putExtra("IT",resIT);
                            intent.putExtra("ART",resArt);
                            intent.putExtra("BUSINESS",resBusiness);
                            intent.putExtra("TECHNICIAN",resTechnician);
                            count.setVisibility(View.INVISIBLE);
                            startActivity(intent);
                            finish();
                        }
                        next.setVisibility(View.INVISIBLE);
                        next.setEnabled(false);
                    });

                }

            }

            @Override
            public void onFailure(Call<List<Questions>> call, Throwable t) {
                String s = "Connection problem! Check your INTERNET";
                question.setText(s);
                addQuestions();
            }
        });
    }

    private void variantsClick() {

        variantA.setOnClickListener(v -> {
            ID = 4;
            variantA.setBackgroundColor(Color.parseColor("#2AF653"));
            variantB.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            variantC.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            variantD.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            next.setVisibility(View.VISIBLE);
            next.setEnabled(true);
        });
        variantB.setOnClickListener(v -> {
            ID = 3;
            variantB.setBackgroundColor(Color.parseColor("#2AF653"));
            variantA.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            variantC.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            variantD.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            next.setVisibility(View.VISIBLE);
            next.setEnabled(true);
        });
        variantC.setOnClickListener(v -> {
            ID = 2;
            variantC.setBackgroundColor(Color.parseColor("#2AF653"));
            variantB.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            variantA.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            variantD.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            next.setVisibility(View.VISIBLE);
            next.setEnabled(true);
        });
        variantD.setOnClickListener(v -> {
            ID = 1;
            variantD.setBackgroundColor(Color.parseColor("#2AF653"));
            variantB.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            variantC.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            variantA.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            next.setVisibility(View.VISIBLE);
            next.setEnabled(true);
        });

    }

    private void addValue(String category, int i) {
        switch (category){
            case "IT" : resIT+=i; break;
            case "ART" : resArt+=i; break;
            case "BUSINESS" : resBusiness+=i; break;
            case "TECHNICIAN" : resTechnician+=i; break;
        }
    }

}