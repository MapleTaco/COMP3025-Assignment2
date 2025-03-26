package com.example.assignment2;

//Student Name: Daniel Perusse
//Student Number: 200551990
//Assignment 2 - Movie Search App
//COMP3025 - Mobile and Pervasive Computing
//Date: March 25, 2025
//Description: This application presents a screen with a edit text box
//This box allows a user to input the name of a movie to search for it.
//It will then list the first ten results of that search, listing the name
//year and a poster image.
//Tapping on a listing will then send you to a details page, showing further
//information about the movie.
//A back button is included to go back to the search activity
//Application ran/tested on Medium Phone API 33.

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment2.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieClickListener {

    List<Movie> movieList;

    MovieViewModel viewModel;

    ActivityMainBinding binding;


    RecyclerView recyclerView;

    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        viewModel.getMovieData().observe(this, movieData -> {
            Log.i("tag", "Update View");
            movieList = movieData;

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            binding.recyclerView.setLayoutManager(layoutManager);

            myAdapter = new MyAdapter(getApplicationContext(), movieData);
            binding.recyclerView.setAdapter(myAdapter);

            myAdapter.setClickListener(this);
        });

        binding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(binding.movieSearchBox.getText().toString().trim())) {
                    binding.movieSearchBox.setError("Please enter a movie to search");

                }

                else {
                    viewModel.Refresh(binding.movieSearchBox.getText().toString().trim());
                    if (movieList == null || movieList.isEmpty()) {
                        binding.movieSearchBox.setError("Bad Search. Try again.");
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v, int pos) {
        Intent detailsIntent = new Intent(MainActivity.this, DetailsActivity.class);

        detailsIntent.putExtra("ID", movieList.get(pos).getImdbID());
        startActivity(detailsIntent);
    }
}