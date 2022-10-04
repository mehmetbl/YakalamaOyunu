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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blmhmt.yakalamaoyunu.databinding.ActivityMainBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements Serializable {


    SharedPreferences sharedPreferences;
    boolean kontrol = false;
    String ad;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        sharedPreferences = this.getSharedPreferences("com.blmhmt.yakalamaoyunu", Context.MODE_PRIVATE);

        Intent intent = getIntent();
        int puan = intent.getIntExtra("skor",0);
        int rekor = sharedPreferences.getInt("rekor",0);

        if(puan>rekor){
            sharedPreferences.edit().putInt("rekor",puan).apply();
            rekor = sharedPreferences.getInt("rekor",0);
            binding.rekorText.setText("En yüksek skor: " + rekor);
        }
        else{
            binding.rekorText.setText("En yüksek skor: " + rekor);
        }
    }
    public void basla(View view){
        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
        ad = binding.editName.getText().toString();
        intent.putExtra("isim",ad);
        startActivity(intent);
    }

    public void change(View view){
        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
        kontrol = true;
        ad = binding.editName.getText().toString();
        intent.putExtra("kontrol",kontrol);
        intent.putExtra("isim",ad);
        startActivity(intent);
    }

    public void leaderBoard(View view){
        Intent intent = new Intent(MainActivity.this,MainActivity3.class);
        startActivity(intent);
    }
}