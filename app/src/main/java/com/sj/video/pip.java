package com.sj.video;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PictureInPictureParams;
import android.content.Intent;
import android.content.res.Configuration;
import android.drm.DrmStore;
import android.graphics.Picture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Rational;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class pip extends AppCompatActivity {
    private Uri videoUri;
    private VideoView videoView;
    private ImageButton pipBtn;
    private ActionBar actionBar;
    private static final String TAG="PIP_TAG";

    private PictureInPictureParams.Builder pictureInPictureParams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pip);
        videoView=findViewById(R.id.video);
        pipBtn=findViewById(R.id.pipBtn);
        try {
            setVideoView((getIntent()));
        }
        catch (Exception e)
        {
            Toast.makeText(pip.this,e.toString(),Toast.LENGTH_SHORT).show();
        }

        actionBar=getSupportActionBar();


        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            pictureInPictureParams=new PictureInPictureParams.Builder();
        }
        else {

        }
        pipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pictureInPictureMode();
            }
        });

    }

    private void setVideoView(Intent intent) {
        String videoURL=intent.getStringExtra("video");
        Log.d(TAG, "setVideoView: URL:"+videoURL);

        MediaController mediaController=new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        videoUri=Uri.parse(videoURL);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(videoUri);



        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                Log.d(TAG,"OnPrepared:Video Prepared,Playing...");
                mediaPlayer.start();
            }
        });
    }
    private void pictureInPictureMode(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            Log.d(TAG,"pictureInPictureMode: Supports PIP");
            Rational aspectRaion =new Rational(videoView.getWidth(),videoView.getHeight());
            pictureInPictureParams.setAspectRatio(aspectRaion).build();
            enterPictureInPictureMode(pictureInPictureParams.build());
        }
        else {
            Log.d(TAG,"pictureInPictureMode: Doesn't support PIP");
        }
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            Log.d(TAG,":OnUserLeaveHint: was not in PIP");
            pictureInPictureMode();
        }
        else {
            Log.d(TAG,"onUserLeaveHint: Already in PIP");
        }

    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);
        if(isInPictureInPictureMode){
        pipBtn.setVisibility(View.GONE);
        actionBar.hide();
        }
        else {
            pipBtn.setVisibility(View.VISIBLE);
            actionBar.hide();

        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(videoView.isPlaying()){
            videoView.stopPlayback();
        }
    }
}