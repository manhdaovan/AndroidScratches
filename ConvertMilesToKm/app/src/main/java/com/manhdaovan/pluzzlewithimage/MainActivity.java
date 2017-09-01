package com.manhdaovan.pluzzlewithimage;

import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button convMilesToKmBtn = (Button) findViewById(R.id.convMilesToKmBtn);
        convMilesToKmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText milesValText = (EditText) findViewById(R.id.milesValueText);
                EditText kmValText = (EditText) findViewById(R.id.kmValueText);
                String milesValInText = milesValText.getText().toString();
                if(milesValInText.length() > 0){
                    Double milesToKm = new Double(milesValInText) / 0.621371;
                    kmValText.setText(String.format("%.2f", milesToKm));
                }
            }
        });

        Button convKmToMiles = (Button) findViewById(R.id.convKmToMilesBtn);
        convKmToMiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                EditText milesValText = (EditText) findViewById(R.id.milesValueText);
                EditText kmValText = (EditText) findViewById(R.id.kmValueText);
                String kmValInText = kmValText.getText().toString();
                if(kmValInText.length() > 0){
                    Double kmToMiles = new Double(kmValInText) * 0.621371;
                    milesValText.setText(String.format("%.2f", kmToMiles));
                }
            }
        });
    }
}
