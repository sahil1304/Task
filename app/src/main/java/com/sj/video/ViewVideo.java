package com.sj.video;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Rational;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class ViewVideo extends AppCompatActivity {
    VideoView videoView;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_viedo);
        videoView=findViewById(R.id.video);
        try {
            Uri uri = Uri.parse(variable.link);
            videoView.setMediaController(new MediaController(this));
            videoView.setVideoURI(uri);
            videoView.requestFocus();
            videoView.start();


        }
        catch (Exception e)
        {
            Toast.makeText(ViewVideo.this,e.toString(),Toast.LENGTH_SHORT).show();
        }

    }



}