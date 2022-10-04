package com.blmhmt.yakalamaoyunu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.blmhmt.yakalamaoyunu.databinding.ActivityMain3Binding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity3 extends AppCompatActivity {

    ActivityMain3Binding binding;
    SQLiteDatabase database;
    ArrayList<Gamer> gamerList;
    GameAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        gamerList = new ArrayList<>();

        Collections.sort(gamerList, new Comparator<Gamer>() {
            @Override
            public int compare(Gamer o1, Gamer o2) {
                return Integer.valueOf(o1.skor).compareTo(Integer.valueOf(o2.skor));
            }
        });

        adapter = new GameAdapter(gamerList);
        recyclerView = findViewById(R.id.recyclerView);

        database = this.openOrCreateDatabase("skorboard",MODE_PRIVATE,null);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GameAdapter(gamerList);
        recyclerView.setAdapter(adapter);

        getData();
    }

    private void getData(){

        try{
            Cursor cursor = database.rawQuery("SELECT * FROM skorlar", null);
            int nameIndex = cursor.getColumnIndex("ad");
            int skorIndex = cursor.getColumnIndex("puan");

            while(cursor.moveToNext()){
                String name = cursor.getString(nameIndex);
                String skor = cursor.getString(skorIndex);
                Gamer gamer = new Gamer(name,skor);
                gamerList.add(gamer);
            }

            Collections.sort(gamerList, new Comparator<Gamer>() {
                @Override
                public int compare(Gamer o1, Gamer o2) {
                    return Integer.valueOf(o2.skor).compareTo(Integer.valueOf(o1.skor));
                }
            });

            adapter.notifyDataSetChanged();
            cursor.close();

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}