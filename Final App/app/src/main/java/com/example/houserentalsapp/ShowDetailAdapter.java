package com.example.houserentalsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ShowDetailAdapter extends RecyclerView.Adapter<ShowDetailAdapter.MyViewHolder> {

    int randomNum;

    Context context;
    ArrayList<ShowDetails> list;
    public ShowDetailAdapter(Context context, ArrayList<ShowDetails> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.row,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ShowDetails showDetails = list.get(position);
        holder.location.setText("Location: "+showDetails.getLocation());
        holder.price.setText("Price: Rs." + showDetails.getPrice()+" pm");
        Picasso.get().load(showDetails.getImg()).into(holder.image);
        //        holder.image.setImageResource();

        //Uri uri = Uri.create(showDetails.getImg());
        //Uri uri = Uri.
        //holder.image.setImageURI(uri);

        randomNum = ThreadLocalRandom.current().nextInt(1, 5 + 1);
        String str = Integer.toString(randomNum);
        holder.image.setOnClickListener(view -> {
            Intent intent = new Intent(context, HouseDetails.class);
            intent.putExtra("data", showDetails.getLocation());
            intent.putExtra("data2", showDetails.getPrice());
            intent.putExtra("data3", showDetails.getDescription());
            intent.putExtra("data4", showDetails.getDeposit());
            intent.putExtra("data5", showDetails.getContact());
            intent.putExtra("mImage", showDetails.getImg());
            intent.putExtra("rating", str);
            context.startActivity(intent);
        });


    }


    @Override
    public int getItemCount(){
        return list.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView location,price,deposit,description,contact;
        ImageView image;
        ConstraintLayout houseDetails;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            location = itemView.findViewById(R.id.textRow1);
            price = itemView.findViewById(R.id.textRow2);
            image = itemView.findViewById(R.id.rowView);
        }
    }
    public static class MyViewHolder2 extends RecyclerView.ViewHolder{

        TextView location,price,deposit,description,contact;
        ImageView image;
        ConstraintLayout houseDetails;


        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);

            location = itemView.findViewById(R.id.textRow1);
            price = itemView.findViewById(R.id.textRow2);
            image = itemView.findViewById(R.id.rowView);
        }
    }
}
