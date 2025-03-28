package com.example.assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

//Adapter based off of in class demos. Small addition in
//onBindViewHolder override
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    List<Movie> movies;

    Context context;

    public MovieClickListener clickListener;

    public MyAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    public void setClickListener(MovieClickListener myListener) {
        this.clickListener = myListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View movieView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(movieView, this.clickListener);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Movie movie = movies.get(position);

        holder.title.setText(movie.getTitle());
        holder.year.setText(movie.getYear());
        //added glide here to load up the poster image for each recycler view list item if it exists
        if (!movie.getPosterURL().equals("N/A")) {
            Glide.with(context).load(movie.getPosterURL()).into(holder.imageView);
        }

    }

    @Override
    public int getItemCount() {

        return movies.size();
    }
}
