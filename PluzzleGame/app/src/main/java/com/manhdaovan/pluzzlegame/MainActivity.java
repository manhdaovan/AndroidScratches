package com.manhdaovan.pluzzlegame;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.manhdaovan.pluzzlegame.utils.Utils;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FrameLayout mainContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToNewGameSetting = new Intent(getApplicationContext(), NewGameSettingActivity.class);
                startActivity(goToNewGameSetting);
            }
        });

        mainContent = (FrameLayout) findViewById(R.id.layout_main_content);
        displaySavedGame();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void displaySavedGame() {
        List<File> allFiles = Utils.getDirs(MainActivity.this, Utils.MODE_DIR_ONLY);
//        TextView noGameMsg = (TextView) findViewById(R.id.textView_noGameMsg);
//
//        if (allFiles.size() > 0) {
//            noGameMsg.setVisibility(View.GONE);
//        } else {
//            noGameMsg.setVisibility(View.VISIBLE);
//        }

        Log.e("DIRRR", "" + getFilesDir().getAbsolutePath());
    }
}
