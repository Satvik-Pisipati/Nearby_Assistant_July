package ch.zli.july;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    AudioManager audioManager;

    public void play(){
        mediaPlayer.start();
    }

    public void pause(){
        mediaPlayer.pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        Button help = findViewById(R.id.help);

        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        mediaPlayer = MediaPlayer.create(this, R.raw.music);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0);
        help.setOnClickListener(v -> {
            if(help.getText().equals("HELP")){
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVolume, 0);
                play();
                help.setText("STOP");

            } else {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
                pause();
                help.setText("HELP");
            }
        });

        Button info = (Button) findViewById(R.id.info);
        info.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), InfoPage.class);
                startActivity(myIntent);
            }
         });
    }
}
