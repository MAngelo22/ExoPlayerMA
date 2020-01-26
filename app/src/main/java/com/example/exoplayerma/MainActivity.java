package com.example.exoplayerma;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class MainActivity extends AppCompatActivity {

    private PlayerView playerView;
    private SimpleExoPlayer player;
    String URL = "https://blueappsoftware.in/layout_design_android_blog.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerView = findViewById(R.id.PlayerView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //crearemos el reproductor
        player = ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector());

        //añadiremos el reproductor a la view de la aplicación
        playerView.setPlayer(player);

        //creamos un origen de datos donde cargaremos los datos del archivo:
        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "ExoPlayerMA"));


        ExtractorMediaSource archivoMultimedia = new
                ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(URL));

        //Preparamos el reproductor con el archivo que queremos reproducir

        player.prepare(archivoMultimedia);
        player.setPlayWhenReady(true);
    }


    @Override
    protected void onStop() {
        super.onStop();

        playerView.setPlayer(null);
        player.release();
        player = null;
    }

}
