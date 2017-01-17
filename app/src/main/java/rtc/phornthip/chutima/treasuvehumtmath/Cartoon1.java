package rtc.phornthip.chutima.treasuvehumtmath;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Cartoon1 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartoon1);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Cartoon1.this,Cartoon2.class));
            }
        },6000); //เดี๋ยวค่อยมาแก้เป็น 6หมื่นทีหลัง 1 นาที

    }   // Main Method
}   // Main Class
