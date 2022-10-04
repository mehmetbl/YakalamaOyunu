package com.blmhmt.yakalamaoyunu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blmhmt.yakalamaoyunu.databinding.RecyclerRowBinding;

import java.util.ArrayList;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.gameHolder> {

    static ArrayList<Gamer> arrayList;

    public GameAdapter(ArrayList<Gamer> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public gameHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new gameHolder(recyclerRowBinding);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull gameHolder holder, int position) {
        holder.binding.recyclerText1.setText("İsim : " + arrayList.get(position).ad);
        holder.binding.recyclerText2.setText("Skor : " + arrayList.get(position).skor);
    }

    public class gameHolder extends RecyclerView.ViewHolder{

        private RecyclerRowBinding binding;

        public gameHolder(RecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
