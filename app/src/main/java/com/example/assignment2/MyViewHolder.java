package com.example.assignment2;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//viewholder based off of in class demos.
public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView title;
    TextView year;

    MovieClickListener clickListener;
    public MyViewHolder(@NonNull View itemView, MovieClickListener clickListener) {
        super(itemView);

        imageView = itemView.findViewById(R.id.item_imageview);
        title = itemView.findViewById(R.id.item_title_text);
        year = itemView.findViewById(R.id.item_year_text);

        this.clickListener = clickListener;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("tag", "onViewHolder Click");
                clickListener.onClick(view, getAdapterPosition());
            }
        });
    }
}
