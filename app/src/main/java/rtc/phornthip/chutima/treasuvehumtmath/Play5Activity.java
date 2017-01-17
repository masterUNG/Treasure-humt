package rtc.phornthip.chutima.treasuvehumtmath;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Play5Activity extends AppCompatActivity implements View.OnClickListener {

    //Explicit
    private TextView questTextView, ch1TextView, ch2TextView,
            ch3TextView, scoreTextView, timeTextView;
    private Random random;
    private int firstAnInt, secondAnInt, answerAnInt, trueChoiceAnInt;
    private int scoreAnInt = 0; // คะแนน
    private int timeAnInt = 20; // กำหนดเวลาที่นี่
    private int endScoreAnInt = 5; // ขอบเขตคะแนน
    private int falseAnInt = 0;
    private ImageView[] boatImageViews = new ImageView[4];
    private int[] widgitImageInts = new int[]{R.id.imageView36_p5, R.id.imageView37_p5,
            R.id.imageView38_p5, R.id.imageView39_p5};
    private boolean aBoolean = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_outpost5);

        random = new Random();

        //Bind widget
        bindWidget();

        //Choice Controller

        ch1TextView.setOnClickListener(this);
        ch2TextView.setOnClickListener(this);
        ch3TextView.setOnClickListener(this);

        playController();

       // countTime();


    }//Main Method

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

    private void bindWidget() {
        questTextView = (TextView) findViewById(R.id.textView5_p5);
        ch1TextView = (TextView) findViewById(R.id.textView2_p5);
        ch2TextView = (TextView) findViewById(R.id.textView3_p5);
        ch3TextView = (TextView) findViewById(R.id.textView4_p5);
        scoreTextView = (TextView) findViewById(R.id.textView6_p5);
        timeTextView = (TextView) findViewById(R.id.textView7_p5);

        for (int i = 0; i < boatImageViews.length; i++) {
            boatImageViews[i] = (ImageView) findViewById(widgitImageInts[i]);
        }
    }

    private void countTime() {

        timeAnInt -= 1;
        timeTextView.setText(Integer.toString(timeAnInt) + "วินาที");
        if (timeAnInt < 0) {
            //สิ่งที่จะทำหลังเวลาหมด
            aBoolean = false;
            myAlertDialog("เวลาหมด", "เวลาหมด เริ่มเกมร์ ใหม่");
        }  //if


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (aBoolean) {
                    countTime();
                }
            }
        }, 1000);

    }    //countTime

    private void playController() {

        firstAnInt = random.nextInt(100);   // Random ตัวแรก
        secondAnInt = random.nextInt(100);  // Random ตัวต่อไป
        answerAnInt = firstAnInt * secondAnInt; // บวกกัน
        trueChoiceAnInt = random.nextInt(3);
        Log.d("4janV1", "ข้อเลือกที่ถูก ==> " + (trueChoiceAnInt + 1));

        //Show Qurstion
        questTextView.setText(Integer.toString(firstAnInt) + " x " +
                Integer.toString(secondAnInt) + " = ?");

        //Show Choice
        TextView[] textViews = new TextView[]{ch1TextView, ch2TextView, ch3TextView};
        for (int i = 0; i < textViews.length; i++) {
            textViews[i].setText(Integer.toString(random.nextInt(100)));
        }   // for

        //Show True Choice
        textViews[trueChoiceAnInt].setText(Integer.toString(answerAnInt));


    }   // playController


    @Override
    public void onClick(View view) {

        soundEffect(R.raw.effect_btn_shut);

        switch (view.getId()) {
            case R.id.textView2_p5:
                checkAnser(Integer.parseInt(ch1TextView.getText().toString()));
                break;
            case R.id.textView3_p5:
                checkAnser(Integer.parseInt(ch2TextView.getText().toString()));
                break;
            case R.id.textView4_p5:
                checkAnser(Integer.parseInt(ch3TextView.getText().toString()));
                break;
        }

        playController();

    }   // onClick

    private void checkAnser(int intChoice) {

        //กำหนดให้ภาพเรือ มองไม่เห็นทั้งหมด
        for (int i = 0; i < boatImageViews.length; i++) {
            boatImageViews[i].setVisibility(View.INVISIBLE);
        }   // for

        //กำหนด รูปที่เรือรม

        if (scoreAnInt < 5) {
            boatImageViews[0].setVisibility(View.VISIBLE);
        } else if (scoreAnInt < 10) {
            boatImageViews[1].setVisibility(View.VISIBLE);
        } else if (scoreAnInt < 15) {
            boatImageViews[2].setVisibility(View.VISIBLE);
        } else {
            boatImageViews[3].setVisibility(View.VISIBLE);
        }


        //เช็คคะแนน
        if (intChoice == answerAnInt) {
            scoreAnInt += 1;
        } else {

            if (falseAnInt >= 3) {
                Toast.makeText(Play5Activity.this, "Game Over", Toast.LENGTH_SHORT).show();
                aBoolean = false;
                myAlertDialog("ผิดเกิน 3 ข้อ", "คุณผิดมากกว่า 3 ข้อ ให้เริ่มเล่นใหม่");


            }

            falseAnInt += 1;
            Log.d("4janV1", "จำนวนข้อที่ตอบผิด false ==> " + falseAnInt);
            spShowImage();

            int[] ints = new int[]{R.drawable.kan1, R.drawable.kan2, R.drawable.kan2, R.drawable.ken3};


            try {

                for (int i = 0; i < boatImageViews.length; i++) {
                    boatImageViews[i].setImageResource(ints[falseAnInt]);
                }   // for

            } catch (Exception e) {
                e.printStackTrace();
            }

        }   // if

        Log.d("4janV1", "Score ==> " + scoreAnInt);

        scoreTextView.setText("Score = " + Integer.toString(scoreAnInt));

        // เช็ค คะแนน
        if (scoreAnInt >= endScoreAnInt) {



            //ย้ายไปด่านต่อไป
            aBoolean = false;
            MyAlert myAlert = new MyAlert(Play5Activity.this, "กุญแจ ดอกที่ 5", "ยินดีด้วย คุณได้ กุญแจ ดอกที่ 5 ละ", SuccessGameActivity.class);
            myAlert.myDialog();


        }


    }   // checkAnser

    private void spShowImage() {

        Log.d("17janV1", "index false ==> " + falseAnInt);
        for (int i = 0; i < boatImageViews.length; i++) {
            boatImageViews[i].setVisibility(View.INVISIBLE);
        }   // for

        switch (falseAnInt) {
            case 0:
                boatImageViews[0].setVisibility(View.VISIBLE);
                break;
            case 1:
                boatImageViews[1].setVisibility(View.VISIBLE);
                break;
            case 2:
                boatImageViews[2].setVisibility(View.VISIBLE);
                break;
            case 3:
                boatImageViews[3].setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }


    }

    private void myAlertDialog(String strTitle, String strMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Play5Activity.this);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.doremon48);
        builder.setTitle(strTitle);
        builder.setMessage(strMessage);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                myRestartApp();
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    private void myRestartApp() {

        scoreAnInt = 0;
        timeAnInt = 30;

        Intent intent = getIntent();
        finish();
        startActivity(intent);

    }

}   //Main Class
