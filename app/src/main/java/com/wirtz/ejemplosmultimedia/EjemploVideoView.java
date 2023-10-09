package com.wirtz.ejemplosmultimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.MediaController;
import android.widget.VideoView;

public class EjemploVideoView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo_video_view);

        VideoView miReproductorVideo = (VideoView) findViewById(R.id.miReproductorVideo);
        //Por streaming
        miReproductorVideo.setVideoURI(Uri.parse(""));
        //Video guardado
        miReproductorVideo.setVideoPath(Environment.getExternalStorageDirectory().getPath() + "/video.mp4");

        //Añadir MediaController
        MediaController controlador= new MediaController(getBaseContext());
        controlador.setAnchorView(miReproductorVideo);
        miReproductorVideo.setMediaController(controlador);

    }
}