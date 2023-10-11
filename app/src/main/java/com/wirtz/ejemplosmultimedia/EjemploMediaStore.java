package com.wirtz.ejemplosmultimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

class Video {
    private final Uri uri;
    private final String name;
    private final int duration;
    private final int size;

    public Video(Uri uri, String name, int duration, int size) {
        this.uri = uri;
        this.name = name;
        this.duration = duration;
        this.size = size;
    }
}
public class EjemploMediaStore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo_media_store);

        ArrayList<Video> listaVideos= new ArrayList<Video>();

        Uri coleccion = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

        // campos a recuperar de la base de datos del MediaStore
        String[] campos = new String[]{
                MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.DURATION,
                MediaStore.Video.Media.SIZE
        };

        //Clausula where
        String seleccion = MediaStore.Video.Media.DURATION + " >= ?";

        //argumentos del where
        String[] argumentosSeleccion = new String[]{
                String.valueOf(TimeUnit.MILLISECONDS.convert(5, TimeUnit.MINUTES))
        };
        //clausula order by;
        String orden = MediaStore.Video.Media.DISPLAY_NAME + " ASC";


        try (Cursor cursor = getApplicationContext().getContentResolver().query(
                coleccion,
                campos,
                seleccion,
                argumentosSeleccion,
                orden
        )) {
            // índices de columnas solicitadas.
            int idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
            int nameColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME);
            int durationColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION);
            int sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE);

            //Nos movemos por el cursor mientras haya elementos
            while (cursor.moveToNext()) {
                long id = cursor.getLong(idColumn);
                String name = cursor.getString(nameColumn);
                int duration = cursor.getInt(durationColumn);
                int size = cursor.getInt(sizeColumn);

                //Obtenemos la Uri del elemento
                Uri contentUri = ContentUris.withAppendedId(
                        MediaStore.Video.Media.EXTERNAL_CONTENT_URI, id);

                // Almacenamos la uri en un objeto local que representa el
                // archivo de medios.
                listaVideos.add(new Video(contentUri, name, duration, size));
            }//while
        }//try
        catch (Exception ex){
            Log.w("","");
        }
    }//OnCreate


    private void anhadirElemento(){

        // Necesitamos crear un ContentResolver para gestionar el contenido multimedia
        ContentResolver resolver = getApplicationContext()
                .getContentResolver();

        // Indicamos dónde buscar todos los archivos de audio en la ubicación deseada.
        Uri audioCollection = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;


        // Publicamos el contenido.
        ContentValues newSongDetails = new ContentValues();
        newSongDetails.put(MediaStore.Audio.Media.DISPLAY_NAME,
                "My Song.mp3");

        // insertamos el elemento en la colección de archivos (y guardamos la URL
        // por si en el código tuviesemos que modificar el archivo).
        Uri myFavoriteSongUri = resolver
                .insert(audioCollection, newSongDetails);


    }
}//class