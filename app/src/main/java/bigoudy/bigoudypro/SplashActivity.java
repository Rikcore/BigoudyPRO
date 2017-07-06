package bigoudy.bigoudypro;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.VideoView;


public class SplashActivity extends Activity {

    VideoView videoView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        videoView = (VideoView) findViewById(R.id.splash_vid);

        Uri video = Uri.parse("android.resource://" + getPackageName() + "/"
                + R.raw.splash);
        videoView.setVideoURI(video);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        try {
            videoView.start();;
        } catch (Exception ex) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}