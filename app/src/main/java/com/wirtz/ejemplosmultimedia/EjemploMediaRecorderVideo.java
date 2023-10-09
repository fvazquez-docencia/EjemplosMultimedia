package com.wirtz.ejemplosmultimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;

import java.io.IOException;

public class EjemploMediaRecorderVideo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo_media_recorder_video);

        String ruta = Environment.getExternalStorageDirectory() + "/miVideo.mp4";
        MediaRecorder grabador = new MediaRecorder();
        grabador.setOutputFile(ruta);
        grabador.setAudioSource(MediaRecorder.AudioSource.MIC);
        grabador.setVideoSource(MediaRecorder.VideoSource.CAMERA);

        grabador.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        grabador.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);

        grabador.setOutputFile(ruta);
        grabador.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);

        try {
            grabador.prepare();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        grabador.start();

    }
}