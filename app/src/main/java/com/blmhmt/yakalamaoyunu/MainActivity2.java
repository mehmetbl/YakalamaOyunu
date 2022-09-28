package com.blmhmt.yakalamaoyunu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    TextView scoreText;
    TextView timeText;
    Boolean kontrol = false;
    int skor;
    ImageView imageView0;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        scoreText = findViewById(R.id.scoreText);
        timeText = findViewById(R.id.timeText);
        imageView0 = findViewById(R.id.imageView0);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageArray = new ImageView[]{imageView0, imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8};
        button = findViewById(R.id.button);
        skor = 0;
        button.setVisibility(View.VISIBLE);
        Intent intent = getIntent();
        boolean resimKontrol = intent.getBooleanExtra("kontrol",false);
        if(resimKontrol==true){
            for(int i=0 ; i<9 ; i++){
                imageArray[i].setImageResource(R.drawable.sam);
            }
        }
    }

    public void baslat(View view){
        kontrol = true;
        button.setVisibility(View.INVISIBLE);
        hideImages();
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                timeText.setText("Time: " + l / 1000);
            }
            @Override
            public void onFinish() {
                timeText.setText("Süre bitti!");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity2.this);
                alert.setTitle("Oyun bitti!");
                alert.setMessage("Yeniden başlamak ister misiniz?");
                alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                        intent.putExtra("skor",skor);
                        startActivity(intent);
                    }
                });
                alert.show();
            }
        }.start();
    }

    public void skorAlma(View view) {
      if(kontrol){
          skor++;
          scoreText.setText("Skor: " + skor);
      }
    }

    public void hideImages(){

        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {

                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,500);
            }
        };

        handler.post(runnable);
    }

   /*
   Alternatif yol :
   public void generateNumber(){
        handler = new Handler();
        run = new Runnable() {
            @Override
            public void run() {
                randomX = new Random().nextInt(1000 - 0) + 0;
                randomY = new Random().nextInt(1000 - 0) + 0;
                handler.postDelayed(this, 400);
                kenny.setX(randomX);
                kenny.setY(randomY);
                textView2.setText(Integer.toString(score));
            }
        };
        handler.post(run);*/

}