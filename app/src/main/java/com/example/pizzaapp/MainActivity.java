package com.example.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton r1, r2;
    Button startBtn;

    private static final int r1_id = 1000;
    private static final int r2_id = 1001;

    HashMap<String,String>languageList = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        languageList.put("Start Order","Lancer la commande");
        r1 = findViewById(R.id.radioButton);
        r2 = findViewById(R.id.radioButton2);
        startBtn = findViewById(R.id.btnStartOrder);
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

            }
        });

    }

    public void clickRadio(){
            switch (radioGroup.getCheckedRadioButtonId()){
                case r1_id:
                    startBtn.setText(languageList.get("Start Order"));
                    break;
                case r2_id:
                    startBtn.setText(getKeyByValue(languageList,"Lancer la commande"));
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