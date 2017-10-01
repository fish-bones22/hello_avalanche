package com.avalanche.hello.helloavalanche;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

public class Splash extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        String uriPath = "android.resource://com.avalanche.hello.helloavalanche/"+R.raw.video_splash;
        Uri uri = Uri.parse(uriPath);

        videoView = (VideoView)findViewById(R.id.videoView);
        //MediaController mediaController = new MediaController(this);
        // mediaController.setAnchorView(videoView);
        //videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                finish();
                Intent intent = new Intent(Splash.this, Drawer.class);
                Splash.this.startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        videoView.start();
        super.onStart();
    }
}
