package com.wirtz.ejemplosmultimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;

public class EjemploMediaRecorder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo_media_recorder);

        MediaRecorder miSonido = new MediaRecorder();
        miSonido.setAudioSource(MediaRecorder.AudioSource.MIC);
        miSonido.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        miSonido.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        //Se construye en este caso la ruta externa del fichero de salida
        String ficheroSalida= Environment.getExternalStorageDirectory().getAbsolutePath() + "/fichero_salida.3gp";
        miSonido.setOutputFile(ficheroSalida);



    }
}