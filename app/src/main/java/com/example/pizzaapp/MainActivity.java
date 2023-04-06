package com.example.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton r1, r2;
    Button startBtn, orderHistory;

    Language language = new Language();

    boolean french = false;

    private static final int r1_id = 1000;
    private static final int r2_id = 1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        r1 = findViewById(R.id.radioButton);
        r2 = findViewById(R.id.radioButton2);
        startBtn = findViewById(R.id.btnStartOrder);
        orderHistory = findViewById(R.id.btnOrderHistory);
        radioGroup = findViewById(R.id.radioGroup);

        //Setting ID
        r1.setId(r1_id);
        r2.setId(r2_id);

        //Click listener
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                clickRadio();
            }

        });
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                Bundle bundle = new Bundle();

                bundle.putBoolean("chosen_language",french);
                intent.putExtra("chosen_language",french);

                startActivity(intent);
            }
        });

        orderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DatabaseActivity.class);
                startActivity(intent);
            }
        });

    }

    public void clickRadio(){
            switch (radioGroup.getCheckedRadioButtonId()){
                case r1_id:
                    french = true;
                    startBtn.setText(language.languageList.get("Start Order"));
                    orderHistory.setText(language.languageList.get("Order History"));
                    break;
                case r2_id:
                    french = false;
                    startBtn.setText(getKeyByValue(language.languageList,"Lancer la commande"));
                    orderHistory.setText(getKeyByValue(language.languageList,"Historique des commandes"));
                    break;
            }
    }

    //Applies the saved data of the radio button
    @Override
    protected void onResume() {
        System.out.println("Testing");
        super.onResume();
        SharedPreferences sh = getSharedPreferences("SharedPreferences",MODE_PRIVATE);
        int languageResume = sh.getInt("Language",0);
        if (languageResume == 0){
            r1.setChecked(true);
        } else {
            r2.setChecked(true);
        }
    }

    //Saves the selection of the radio buttons
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sh = getSharedPreferences("SharedPreferences",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sh.edit();
        myEdit.putInt("Language",radioGroup.indexOfChild(findViewById(radioGroup.getCheckedRadioButtonId())));
        myEdit.apply();
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
}