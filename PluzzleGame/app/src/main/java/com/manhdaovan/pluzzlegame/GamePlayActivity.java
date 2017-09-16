package com.manhdaovan.pluzzlegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class GamePlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        Intent gameSetting = getIntent();
        Toast.makeText(GamePlayActivity.this, "Im in GamePlayActivity" + gameSetting.toString(), Toast.LENGTH_LONG).show();
    }
}
