package com.example.assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

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
        //holder.imageView.setImageResource(movie.getMoviePoster());

    }

    @Override
    public int getItemCount() {

        return movies.size();
    }
}
