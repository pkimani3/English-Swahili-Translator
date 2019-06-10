package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class TheAdapter extends RecyclerView.Adapter<TheAdapter.ViewHolder> {
    private String[] dataSet = new String[]{};

    void setDataSet(String[] set){
        this.dataSet = set;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        TextView textView = (TextView) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_main,viewGroup,false);
        return new ViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        if(!dataSet[i].isEmpty()){
            viewHolder.textView.setText((i+1)+"." +dataSet[i]);
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ViewHolder(@NonNull TextView textView){
            super(textView);
            this.textView = textView;
        }
    }
}
