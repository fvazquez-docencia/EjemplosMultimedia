package com.wirtz.ejemplosmultimedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class EjemploMediaPlayerVideo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo_media_player_video);

        SurfaceView miSurfaceView;
        SurfaceHolder miSurfaceHolder;

        miSurfaceView = (SurfaceView) findViewById(R.id.miSurfaceView);
        miSurfaceHolder = miSurfaceView.getHolder();

        //Se le indica el tamaño del holder y se le añade el callback
        miSurfaceHolder.setFixedSize(176,144);
        miSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

            }
        });

        //Se instancia un elemento de tipo MediaPlayer y se asocia al holder
        MediaPlayer reproductor;

        reproductor= new MediaPlayer();
        reproductor.setAudioStreamType(AudioManager.STREAM_MUSIC);
        reproductor.setDisplay(miSurfaceHolder);
        Uri video;

        video= Uri.parse("URL en la red");
        video= Uri.parse("android.resource://" +getPackageName() + "/" +R.raw.video);
        video= Uri.parse(Environment.getExternalStorageDirectory().getPath() +  "/video.mp4");

        String videoStr = Environment.getExternalStorageDirectory().getAbsolutePath() +  "/video.mp4";

        try {
            //Mediante URI
            reproductor.setDataSource(getApplicationContext(),video);

            //Mediante String
            reproductor.setDataSource(videoStr);

            reproductor.prepare();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }


}