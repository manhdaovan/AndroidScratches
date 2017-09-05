package com.manhdaovan.block05;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    static final String COLOR_PREFERENCES = "COLORS_PREFERENCES";
    static final String COLOR_KEY = "colorCode";
    static final String COLOR_INDEX = "colorIndex";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.main_layout);
        RadioGroup colors = (RadioGroup) findViewById(R.id.radioGroup_colors);
        SharedPreferences preferences = getSharedPreferences(COLOR_PREFERENCES, MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();

        initColor(mainLayout, colors, preferences);

        colors.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup rdGroup, int checkedId) {
                int newColorCode = 0;
                switch (checkedId) {
                    case R.id.radioButton_blue:
                        newColorCode = Color.BLUE;
                        break;
                    case R.id.radioButton_yellow:
                        newColorCode = Color.YELLOW;
                        break;
                    case R.id.radioButton_red:
                        newColorCode = Color.RED;
                        break;
                }

                mainLayout.setBackgroundColor(newColorCode);
                editor.putInt(COLOR_KEY, newColorCode);
                editor.putInt(COLOR_INDEX, checkedId);
                editor.apply();
            }
        });
    }

    private void initColor(RelativeLayout layout, RadioGroup colors, SharedPreferences preferences) {
        int initColorCode = 0;
        int initColorIndex = 0;
        if (preferences.contains(COLOR_KEY) && preferences.contains(COLOR_INDEX)) {
            initColorCode = preferences.getInt(COLOR_KEY, 0);
            initColorIndex = preferences.getInt(COLOR_INDEX, 0);
        }
        if (initColorCode != 0) {
            layout.setBackgroundColor(initColorCode);
            colors.check(initColorIndex);
        }
    }
}
