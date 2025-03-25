package com.example.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment2.databinding.ActivityMainBinding;

import java.util.ArrayList;
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
                }
            }
        });
    }

    @Override
    public void onClick(View v, int pos) {
        //Toast.makeText(this, "You Choose: "+ movieList.get(pos).getTitle(), Toast.LENGTH_SHORT).show();
        Intent detailsIntent = new Intent(MainActivity.this, DetailsActivity.class);

        detailsIntent.putExtra("ID", movieList.get(pos).getImdbID());
        startActivity(detailsIntent);
    }
}