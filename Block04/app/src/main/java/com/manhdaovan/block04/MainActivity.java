package com.manhdaovan.block04;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    MediaPlayer musicPlayer;
    Switch loopSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        musicPlayer = (MediaPlayer.create(this, R.raw.mien_cat_trang_quang_vinh));
        loopSwitch = (Switch) findViewById(R.id.switch_loop);
        loopSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton btn, boolean isChecked){
                musicPlayer.setLooping(isChecked);
            }
        });
    }

    public void playMusic(View v){
        musicPlayer.start();
    }

    public void pauseMusic(View v){
        if(musicPlayer.isPlaying()){
            musicPlayer.pause();
        }
    }
}
