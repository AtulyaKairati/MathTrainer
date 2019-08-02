package com.project.mathtrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Addition extends AppCompatActivity {

     TextView quesText, indicator,scoreText,timer,finalScore,efficiency;
     Integer range,locOfAns,score=0,noOfQues=0;
     ArrayList<String> answers= new ArrayList<>();
     Button button0,button1,button2,button3;
     LinearLayout linLayout;
     ImageView darkViel;
     GridLayout buttonGrid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);


        ////
        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        indicator =findViewById(R.id.indicator);
        scoreText=findViewById(R.id.score);
        timer=findViewById(R.id.timer);
        linLayout=findViewById(R.id.linLayout);
        efficiency=findViewById(R.id.efficiency);
        finalScore=findViewById(R.id.finalScore);
        darkViel=findViewById(R.id.darker);
        buttonGrid=findViewById(R.id.buttonGrid);


        generateQues();
        View v = null;
        playAgain(v);



    }


    // Called When Answers Buttons Are Clicked
    public void chooseAns(View v){
        if (v.getTag().toString().equals(Integer.toString(locOfAns))){
            score++;
            indicator.setText("CORRECT");
        }
        else
            indicator.setText("INCORRECT");
        noOfQues++;
        scoreText.setText("Score : "+score + "/" + noOfQues);
        answers.clear();
        generateQues();


    }



    //Genrates new question
    public void generateQues(){

        ////////////////////// Generating Range based on difficulty choosen
        switch (MainActivity.difficulty) {
            case "0":
                range = 20;
                break;
            case "1":
                range = 50;
                break;
            case "2":
                range = 200;
                break;
            case "3":
                range = 500;
                break;
            case "4":
                range = 1000;
                break;
        }


        // Generating random numbers for sum
        Random rand = new Random();
        int a = rand.nextInt(range+1);
        int b = rand.nextInt(range+1);

        quesText=findViewById(R.id.ques);
        quesText.setText(String.format("%d + %d", a, b));

        //Generating random location for correct answer in ArrayList
        locOfAns=rand.nextInt(4);

        for (int i=0; i<4; i++)
        {
            if (i == locOfAns)
                answers.add(String.valueOf(a+b));
            else {
                int incorrectAns = rand.nextInt(range/5) + a+b - range/10;

                // Making Sure that IncorrectAns Is NOt Same as a+b
                while ( incorrectAns == a + b || answers.contains(Integer.toString( incorrectAns )))
                    incorrectAns = rand.nextInt(range/5) + a+b -range/10;

                answers.add(String.valueOf(incorrectAns));
            }
        }


        button0.setText(answers.get(0));
        button1.setText(answers.get(1));
        button2.setText(answers.get(2));
        button3.setText(answers.get(3));

    }

    public void playAgain(View v){

        answers.clear();
        generateQues();

        score=0;
        noOfQues=0;
        linLayout.setVisibility(View.INVISIBLE);
        darkViel.setVisibility(View.INVISIBLE);
        buttonGrid.setVisibility(View.VISIBLE);

        timer.setText("30s");
        scoreText.setText("Score : 0/0");
        indicator.setText("");


        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {

                timer.setText((String.valueOf(millisUntilFinished / 1000))+"s");

            }

            @Override
            public void onFinish() {
                indicator.setText("DONE");
                timer.setText("0s");
                linLayout.setVisibility(View.VISIBLE);
                int efficiencyPercent=0;
                if (noOfQues != 0)
                    efficiencyPercent = score*100/noOfQues;

                efficiency.setText("Efficiency : "+(efficiencyPercent)+"%");
                finalScore.setText("Speed : "+ (score*100/30));
                darkViel.setVisibility(View.VISIBLE);

                buttonGrid.setVisibility(View.INVISIBLE);

            }
        }.start();


    }


    public  void gotoMainMenu(View v){

//        Intent mi = new Intent(this, MainActivity.class);
//        startActivity(mi);

        ///one end Linear Layout And Other the Relative Layout

        finish();
        finish();

    }

}


