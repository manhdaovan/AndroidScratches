package com.manhdaovan.pluzzlegame;

import android.content.Intent;
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
        Log.e(TAG, "rowPieces: " + rowPieces + " columnPieces: " + columnPieces);

        if (croppedImgUri == null) {
            Toast.makeText(getApplicationContext(), "Cannot fetch image from Game Setting screen", Toast.LENGTH_LONG).show();
            this.finish();
            return;
        }

        String md5File = Utils.uriToMd5(getContentResolver(), croppedImgUri);
        if (md5File == null) {
            Toast.makeText(getApplicationContext(), "Cannot get md5 of image from Game Setting screen", Toast.LENGTH_LONG).show();
            this.finish();
            return;
        }

        File gameImgsFolder = Utils.createFolder(GamePlayActivity.this, md5File);
        if (gameImgsFolder == null) {
            Toast.makeText(getApplicationContext(), "Cannot create folder: " + gameImgsFolder.getAbsolutePath(), Toast.LENGTH_LONG).show();
            this.finish();
            return;
        }

        Utils.getDirs(GamePlayActivity.this, Utils.MODE_ALL);

        try {
            Utils.saveFile(GamePlayActivity.this, gameImgsFolder.getAbsolutePath(), Constants.defaultCroppedFileName(), croppedImgUri.getPath());
        }catch (IOException e){
            Log.e(TAG, "DMMMMM " + e.getMessage());
            Toast.makeText(getApplicationContext(), "Cannot saveFile" + gameImgsFolder.getAbsolutePath(), Toast.LENGTH_LONG).show();
        }

        Utils.getAllSubFilesAndFolders(gameImgsFolder);
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

}
