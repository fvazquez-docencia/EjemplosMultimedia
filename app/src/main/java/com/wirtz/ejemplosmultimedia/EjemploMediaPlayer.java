package com.wirtz.ejemplosmultimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;

import java.io.IOException;

public class EjemploMediaPlayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo_media_player);

        MediaPlayer sonido;

        //Desde recurso raw
        sonido = MediaPlayer.create(getBaseContext(),R.raw.audio);
        //Desde fichero local
        sonido = MediaPlayer.create(getBaseContext(),
                Uri.parse("file://"+ Environment.getExternalStorageDirectory().getPath() +"/sonido.mp3"));
        //Desde URL
        sonido = MediaPlayer.create(getBaseContext(), Uri.parse("http://sitio.es/sonido.mp3"));
        //Desde el proveedor de contenido
        sonido = MediaPlayer.create(getBaseContext(), Settings.System.DEFAULT_RINGTONE_URI);

        sonido = new MediaPlayer();
        try {
            sonido.setDataSource(Environment.getExternalStorageDirectory().getPath() +"/sonido.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            //Con esta forma de construir el objeto es necesario llamar al método prepare()
            sonido.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sonido.start();

    }
}