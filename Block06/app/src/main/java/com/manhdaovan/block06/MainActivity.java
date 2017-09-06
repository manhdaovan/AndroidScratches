package com.manhdaovan.block06;

import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    static final int COLOR_STEP = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView imageLarge = (ImageView) findViewById(R.id.imageView_large);
        ImageView imageSmall = (ImageView) findViewById(R.id.imageView_small);

        imageLarge.setImageResource(R.drawable.songoku_tiny);
        imageSmall.setImageResource(R.drawable.songoku_tiny);

        SeekBar colorTuner = (SeekBar) findViewById(R.id.seekBar_tuner);
        colorTuner.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int lastProgress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                lastProgress = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                imageLarge.setColorFilter(Color.argb(100, 0, lastProgress, 255 - lastProgress));
            }
        });
    }
}
