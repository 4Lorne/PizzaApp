package com.example.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OrderActivity extends AppCompatActivity {
    Button removeTopping, addTopping;
    EditText numOrder1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        removeTopping = findViewById(R.id.btnTopping1Left);
        addTopping = findViewById(R.id.btnTopping1Right);
        numOrder1 = findViewById(R.id.numTopping1);

        removeTopping.setOnClickListener(onButtonClicked);
        addTopping.setOnClickListener(onButtonClicked);

    }

    public View.OnClickListener onButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnTopping1Left:
                    int numToppings = Integer.parseInt(String.valueOf(numOrder1.getText())) - 1;
                    numOrder1.setText(String.valueOf(numToppings));
                    break;
                case R.id.btnTopping1Right:
                    numToppings = Integer.parseInt(String.valueOf(numOrder1.getText())) + 1;
                    numOrder1.setText(String.valueOf(numToppings));
                    break;
            }
        }
    };
}