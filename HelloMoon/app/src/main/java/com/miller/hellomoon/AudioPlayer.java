package com.miller.hellomoon;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by miller on 16/6/30.
 */
public class AudioPlayer {
    private MediaPlayer mPlayer;
    public void stop() {
        if(mPlayer != null){
            mPlayer.release();
            mPlayer = null;
        }

    }

    public void play(Context c) {
        stop();

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            @Override
            public void onCompletion(MediaPlayer mp) {
               stop();
            }
        });
        mPlayer = MediaPlayer.create(c, R.raw.one_small_step);
        mPlayer.start();
    }
}

