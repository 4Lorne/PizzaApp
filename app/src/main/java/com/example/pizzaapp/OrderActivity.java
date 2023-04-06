package com.example.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class OrderActivity extends AppCompatActivity {
    Button addTopping1, addTopping2, addTopping3, addTopping4;
    Button removeTopping1, removeTopping2, removeTopping3, removeTopping4;
    Button completeOrder;
    RadioButton small, medium, large;
    TextView size, pepperoni, sausage, mushroom, peppers, customerDetails;
    EditText numToppings1, numToppings2, numToppings3, numToppings4, customerName;


    boolean french;
    int totalToppings = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Bundle bundle = getIntent().getExtras();

        french = bundle.getBoolean("chosen_language");
        Language language = new Language();

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

        customerName = findViewById(R.id.ptCustomerName);
        customerDetails = findViewById(R.id.tvCustomerDetails);
        completeOrder = findViewById(R.id.completeOrder);

        //On click listeners
        addTopping1.setOnClickListener(onButtonClicked);
        addTopping2.setOnClickListener(onButtonClicked);
        addTopping3.setOnClickListener(onButtonClicked);
        addTopping4.setOnClickListener(onButtonClicked);

        removeTopping1.setOnClickListener(onButtonClicked);
        removeTopping2.setOnClickListener(onButtonClicked);
        removeTopping3.setOnClickListener(onButtonClicked);
        removeTopping4.setOnClickListener(onButtonClicked);

        size = findViewById(R.id.tvSize);
        small = findViewById(R.id.sizeSmall);
        medium = findViewById(R.id.sizeMedium);
        large = findViewById(R.id.sizeLarge);
        pepperoni = findViewById(R.id.tvPepperoni);
        sausage = findViewById(R.id.tvSausage);
        mushroom = findViewById(R.id.tvMushroom);
        peppers = findViewById(R.id.tvPepper);
        setLanguage(french,language);

    }

    public View.OnClickListener onButtonClicked = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnTopping1Left:
                    toppingTotal();
                    numToppings1 = minusTopping(numToppings1);
                    break;
                case R.id.btnTopping1Right:
                    toppingTotal();

                    numToppings1 = plusTopping(numToppings1);
                    break;
                case R.id.btnTopping2Left:
                    toppingTotal();

                    numToppings2 = minusTopping(numToppings2);
                    break;
                case R.id.btnTopping2Right:
                    toppingTotal();

                    numToppings2 = plusTopping(numToppings2);
                    break;
                case R.id.btnTopping3Left:
                    toppingTotal();

                    numToppings3 = minusTopping(numToppings3);
                    break;
                case R.id.btnTopping3Right:
                    toppingTotal();

                    numToppings3 = plusTopping(numToppings3);
                    break;
                case R.id.btnTopping4Left:
                    toppingTotal();

                    numToppings4 = minusTopping(numToppings4);
                    break;
                case R.id.btnTopping4Right:
                    toppingTotal();

                    numToppings4 = plusTopping(numToppings4);
                    break;
            }
        }
    };


    //TODO: Add a check for total toppings being greater than 3 and at least 1
    //      Add a date / time check
    //      Create a storyboard
    //
    public EditText minusTopping(EditText numOrder){
        if (totalToppings <= 0 ){
            return numOrder;
        }
        int numToppings = Integer.parseInt(String.valueOf(numOrder.getText())) - 1;
        totalToppings--;
        numOrder.setText(String.valueOf(numToppings));
        System.out.println(totalToppings);
        return numOrder;
    }
    public EditText plusTopping(EditText numOrder){
        if (totalToppings >= 3){
            System.out.println(totalToppings);
            return numOrder;
        }
        int numToppings = Integer.parseInt(String.valueOf(numOrder.getText())) + 1;
        if (numToppings >= 3){
            numToppings = 3;
        }
        totalToppings++;
        numOrder.setText(String.valueOf(numToppings));
        return numOrder;
    }

    public void setLanguage(Boolean french, Language language){
            if (french){
                size.setText(language.languageList.get("Size"));
                small.setText(language.languageList.get("Small"));
                medium.setText(language.languageList.get("Medium"));
                large.setText(language.languageList.get("Large"));
                pepperoni.setText(language.languageList.get("Pepperoni"));
                sausage.setText(language.languageList.get("Sausage"));
                mushroom.setText(language.languageList.get("Mushroom"));
                peppers.setText(language.languageList.get("Peppers"));

                customerDetails.setText(language.languageList.get("Customer Details"));
                customerName.setText(language.languageList.get("Name…"));
                completeOrder.setText(language.languageList.get("Complete Order"));
            }
    }

    //Get key by value - https://stackoverflow.com/a/2904266
    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void toppingTotal(){
        if (totalToppings < 0){
            totalToppings = 0;
        }
        if (totalToppings > 3){
            totalToppings = 3;
        }
    }
}