package com.example.houserentalsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class HouseAdapter extends RecyclerView.Adapter<HouseAdapter.MyViewHolder> {

    String[] data;
    String[] data2;
    int[] images;
    Context context;
    String rate;

    public HouseAdapter(Context zin, String[] s1, String[] s2, int[] emg, String rating){
        context = zin;
        data = s1;
        data2 = s2;
        images = emg;
        rate = rating;
    }

    @NonNull

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  HouseAdapter.MyViewHolder holder, int position) {
        holder.mText1.setText(data[position]);
        // holder.mText2.setText(data2[position]);
        holder.mImage.setImageResource(images[position]);

        holder.houseDetails.setOnClickListener(view -> {
            Intent intent = new Intent(context, HouseDetails.class);
            intent.putExtra("data", data[position]);
            intent.putExtra("data2", data2[position]);
            intent.putExtra("mImage", images[position]);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mText1;
        TextView mText2;
        ImageView mImage;
        ConstraintLayout houseDetails;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mText1 = itemView.findViewById(R.id.textRow1);
            mText2 = itemView.findViewById(R.id.textView2);
            mImage = itemView.findViewById(R.id.rowView);
            houseDetails = itemView.findViewById(R.id.houseDetails);
        }
    }
}