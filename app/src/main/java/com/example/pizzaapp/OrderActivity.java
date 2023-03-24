package com.example.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OrderActivity extends AppCompatActivity {
    Button addTopping1, addTopping2, addTopping3, addTopping4;
    Button removeTopping1, removeTopping2, removeTopping3, removeTopping4;
    EditText numToppings1, numToppings2, numToppings3, numToppings4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        //Add toppings
        addTopping1 = findViewById(R.id.btnTopping1Right);
        addTopping2 = findViewById(R.id.btnTopping2Right);
        addTopping3 = findViewById(R.id.btnTopping3Right);
        addTopping4 = findViewById(R.id.btnTopping4Right);
        //Remove toppings
        removeTopping1 = findViewById(R.id.btnTopping1Left);
        removeTopping2 = findViewById(R.id.btnTopping2Left);
        removeTopping3 = findViewById(R.id.btnTopping3Left);
        removeTopping4 = findViewById(R.id.btnTopping4Left);
        //Number of toppings
        numToppings1 = findViewById(R.id.numTopping1);
        numToppings2 = findViewById(R.id.numTopping2);
        numToppings3 = findViewById(R.id.numTopping3);
        numToppings4 = findViewById(R.id.numTopping4);


        addTopping1.setOnClickListener(onButtonClicked);
        addTopping2.setOnClickListener(onButtonClicked);
        addTopping3.setOnClickListener(onButtonClicked);
        addTopping4.setOnClickListener(onButtonClicked);

        removeTopping1.setOnClickListener(onButtonClicked);
        removeTopping2.setOnClickListener(onButtonClicked);
        removeTopping3.setOnClickListener(onButtonClicked);
        removeTopping4.setOnClickListener(onButtonClicked);

    }

    public View.OnClickListener onButtonClicked = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnTopping1Left:
                    numToppings1 = minusTopping(numToppings1);
                    break;
                case R.id.btnTopping1Right:
                    numToppings1 = plusTopping(numToppings1);
                    break;
                case R.id.btnTopping2Left:
                    numToppings2 = minusTopping(numToppings2);
                    break;
                case R.id.btnTopping2Right:
                    numToppings2 = plusTopping(numToppings2);
                    break;
                case R.id.btnTopping3Left:
                    numToppings3 = minusTopping(numToppings3);
                    break;
                case R.id.btnTopping3Right:
                    numToppings3 = plusTopping(numToppings3);
                    break;
                case R.id.btnTopping4Left:
                    numToppings4 = minusTopping(numToppings4);
                    break;
                case R.id.btnTopping4Right:
                    numToppings4 = plusTopping(numToppings4);
            }
        }
    };


    public EditText minusTopping(EditText numOrder){
        int numToppings = Integer.parseInt(String.valueOf(numOrder.getText())) - 1;
        numOrder.setText(String.valueOf(numToppings));
        return numOrder;
    }
    public EditText plusTopping(EditText numOrder){
        int numToppings = Integer.parseInt(String.valueOf(numOrder.getText())) + 1;
        numOrder.setText(String.valueOf(numToppings));
        return numOrder;
    }
}