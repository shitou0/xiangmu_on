package com.example.cartoon.stop;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.cartoon.R;

/**
 * Created by 石头 on 2017/12/17.
 */

public class StopMusic {

    private MediaPlayer diaPlayer;

    private Context con;

    public StopMusic(Context con) {
        this.con = con;
    }


    public void start() {
        diaPlayer = MediaPlayer.create(con, R.raw.wenrouxiang);
        boolean playing = diaPlayer.isPlaying();
        if (!playing){
            diaPlayer.start();
        }
    }
    public void stop() {
        diaPlayer.stop();
    }


}
