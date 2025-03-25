package com.example.assignment2;

import android.os.Bundle;
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
            //binding.textViewUSD.setText(movieData.getUsdPrice());
            //binding.textViewJPY.setText(movieData.getJPYPrice());
            //binding.textViewEUR.setText(movieData.getEuroPrice());

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            binding.recyclerView.setLayoutManager(layoutManager);

            myAdapter = new MyAdapter(getApplicationContext(), movieData);
            binding.recyclerView.setAdapter(myAdapter);

            myAdapter.setClickListener(this);
        });

        binding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.Refresh();

            }
        });
    }

    @Override
    public void onClick(View v, int pos) {
        Toast.makeText(this, "You Choose: "+ movieList.get(pos).getTitle(), Toast.LENGTH_SHORT).show();
    }
}