package com.manhdaovan.pluzzlegame;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.manhdaovan.pluzzlegame.utils.Constants;
import com.manhdaovan.pluzzlegame.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class GamePlayActivity extends AppCompatActivity {
    private static final String TAG = "GamePlayActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        Intent gameSetting = getIntent();
        Uri croppedImgUri = gameSetting.getData();
        int rowPieces = gameSetting.getIntExtra(Constants.INTENT_ROW_PIECES, Constants.DEFAULT_ROW_NUM_PIECES);
        int columnPieces = gameSetting.getIntExtra(Constants.INTENT_COLUMN_PIECES, Constants.DEFAULT_COLUMN_NUM_PIECES);

        initGame();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed(){
        // TODO: add confirm box here
        this.finish();
    }

    public void initGame(){
        String gameResourceFolder = getIntent().getStringExtra(Constants.INTENT_GAME_RESOURCE_FOLDER);
        if(gameResourceFolder == null){
            Log.e(TAG, "initGame FALSEEEE ");
            return;
        }
        File gamePiecesFolder = new File(getApplicationContext().getFilesDir(), gameResourceFolder);
        Log.e(TAG, "DMM 000000 " + gamePiecesFolder.getAbsolutePath());
        List<File> allFiles = Utils.getDirs(gamePiecesFolder, Utils.MODE_FILE_ONLY);
    }

}
