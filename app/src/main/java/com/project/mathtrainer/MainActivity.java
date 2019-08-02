package com.project.mathtrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    static String difficulty = "0";
    static String sumType = "+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setDifficulty(View v){

        difficulty= (String) v.getTag();
        startIntent();
        LinearLayout selLay,diffLay;
        selLay = findViewById(R.id.selectionLayout);
        selLay.setVisibility(View.VISIBLE);
        diffLay = findViewById(R.id.diffLayout);
        diffLay.setVisibility(View.INVISIBLE);


    }

    public void setSumType(View v){
        sumType= (String) v.getTag();
        LinearLayout selLay,diffLay;

        selLay = findViewById(R.id.selectionLayout);
        selLay.setVisibility(View.INVISIBLE);

        diffLay = findViewById(R.id.diffLayout);
        diffLay.setVisibility(View.VISIBLE);

    }

    public void startIntent(){

        switch (sumType) {
            case "+":
                Intent addIntent = new Intent(this, Addition.class);
                startActivity(addIntent);
                break;
            case "-":
                Intent subIntent = new Intent(this, Subtraction.class);
                startActivity(subIntent);
                break;
            case "*":
                Intent multiplyIntent = new Intent(this, Multiplication.class);
                startActivity(multiplyIntent);
                break;
            case "/":
                Intent divIntent = new Intent(this, Divide.class);
                startActivity(divIntent);
                break;
            case "+-*/":

                Toast.makeText(this, "Not Ready Yet!", Toast.LENGTH_SHORT).show();
//                Intent allIntent = new Intent(this, Addition.class);
//                startActivity(allIntent);
                break;

        }

    }

}
