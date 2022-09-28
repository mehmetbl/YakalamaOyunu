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
import android.preference.ListPreference;
import android.view.ContentInfo;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements Serializable {

    TextView rekorText;
    SharedPreferences sharedPreferences;
    boolean kontrol = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = this.getSharedPreferences("com.blmhmt.yakalamaoyunu", Context.MODE_PRIVATE);
        rekorText = findViewById(R.id.rekorText);
        Intent intent = getIntent();
        int puan = intent.getIntExtra("skor",0);
        int rekor = sharedPreferences.getInt("rekor",0);

        if(puan>rekor){
            sharedPreferences.edit().putInt("rekor",puan).apply();
            rekor = sharedPreferences.getInt("rekor",0);
            rekorText.setText("En yüksek skor: " + rekor);
        }
        else{
            rekorText.setText("En yüksek skor: " + rekor);
        }
    }
    public void basla(View view){
        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
        startActivity(intent);
    }

    public void change(View view){
        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
        kontrol = true;
        intent.putExtra("kontrol",kontrol);
        startActivity(intent);
    }
}