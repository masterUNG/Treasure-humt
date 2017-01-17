package rtc.phornthip.chutima.treasuvehumtmath;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SuccessGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_game);

        soundEffect(R.raw.intro_start_horse);

    }

    private void soundEffect(int indexSound) {
        MediaPlayer mediaPlayer = MediaPlayer.create(getBaseContext(), indexSound);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
    }   // soundEffect

}
