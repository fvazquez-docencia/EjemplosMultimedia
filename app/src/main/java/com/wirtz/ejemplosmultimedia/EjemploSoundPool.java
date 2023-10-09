package com.wirtz.ejemplosmultimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class EjemploSoundPool extends AppCompatActivity {
    int idAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo_sound_pool);
        SoundPool sndPool;




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sndPool = new SoundPool(16, AudioManager.STREAM_MUSIC,100);
        } else {
            SoundPool.Builder builder = new SoundPool.Builder();
            AudioAttributes atr = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();
            sndPool = builder.setAudioAttributes(atr)
                    .setMaxStreams(16)
                    .build();
        }


        Button btPlay=(Button) findViewById(R.id.btReproducir);




        idAudio = sndPool.load(getBaseContext(), R.raw.audio, 1);

        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    sndPool.play(idAudio, 100, 100, 1, 0, 1);
                }catch (Exception ex){
                    Log.w("error",ex.getMessage());
                }
            }
        });


    }
}