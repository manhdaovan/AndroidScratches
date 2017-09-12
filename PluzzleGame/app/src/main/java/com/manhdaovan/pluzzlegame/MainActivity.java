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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    LinearLayout mainContent;

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

        mainContent = (LinearLayout) findViewById(R.id.layout_main_content);
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
        File fileDir = getFilesDir();
        File[] allFiles = fileDir.listFiles();
        List<File> dirs = new ArrayList<>();
        TextView noGameMsg = (TextView) findViewById(R.id.textView_noGameMsg);

        for (File f : allFiles) {
            Log.e("DIRRR getAbsolutePath", "" + f.getAbsolutePath());
            Log.e("DIRRR isDirectory", "" + f.isDirectory());
            if (f.isDirectory()) dirs.add(f);
        }

        if (dirs.size() > 0) {
            noGameMsg.setVisibility(View.GONE);
        } else {
            noGameMsg.setVisibility(View.VISIBLE);
        }

        Log.e("DIRRR", "" + fileDir.getAbsolutePath());
    }
}
