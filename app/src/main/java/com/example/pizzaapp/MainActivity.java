package com.example.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton r1, r2;

    private static final int r1_id = 1000;
    private static final int r2_id = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        r1 = findViewById(R.id.radioButton);
        r2 = findViewById(R.id.radioButton2);
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

    }

    public void clickRadio(){
        System.out.println("Test");
            switch (radioGroup.getCheckedRadioButtonId()){
                case r1_id:
                    Toast.makeText(this,"French Clicked", Toast.LENGTH_LONG).show();
                    break;
                case r2_id:
                    Toast.makeText(this,"English Clicked", Toast.LENGTH_LONG).show();
                    break;
                default:
                    System.out.println("Nothing fired");
                    break;
            }



    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sh = getSharedPreferences("SharedPreferences",MODE_PRIVATE);
        String language = sh.getString("language","");
    }
}