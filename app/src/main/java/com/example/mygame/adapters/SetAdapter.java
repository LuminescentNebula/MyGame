package com.example.mygame.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygame.R;
import com.example.mygame.card.Card;

import java.util.ArrayList;

import me.grantland.widget.AutofitTextView;

public class SetAdapter extends RecyclerView.Adapter<SetAdapter.TextRow> {
    private final String TAG = "SetAdapter";
    private ArrayList<Integer> set;
    public SetAdapter(ArrayList<Integer> set) {
        this.set = set;
    }

    @NonNull
    @Override
    public SetAdapter.TextRow onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_view,parent,false);
        return new SetAdapter.TextRow(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SetAdapter.TextRow holder, int position) {
        holder.textView.setText(Card.values()[set.get(position)].getHeadline());
    }

    @Override
    public int getItemCount() {
        return set.size();
    }

    /*private void addItem(Card item) {
        cards.add(item);
        MyAdapter.notifyDataSetChanged();
    }*/

    public class TextRow extends RecyclerView.ViewHolder{
        AutofitTextView textView;
        public TextRow(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.TextView);
        }
    }
}
