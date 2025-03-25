package com.example.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.assignment2.databinding.ActivityMainBinding;
import com.example.assignment2.databinding.DetailsActivityBinding;

public class DetailsActivity extends AppCompatActivity {

    MovieDetails movieDetails;

    MovieDetailsViewModel detailsViewModel;

    DetailsActivityBinding detailsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        detailsBinding = DetailsActivityBinding.inflate(getLayoutInflater());

        setContentView(detailsBinding.getRoot());

        detailsViewModel = new ViewModelProvider(this).get(MovieDetailsViewModel.class);
        detailsViewModel.getDetailsData().observe(this, detailsData -> {
            Log.i("tag", "Update View");
            movieDetails = detailsData;
            detailsBinding.detailsTitle.setText(detailsData.getTitle());
            detailsBinding.detailsYear.setText(detailsData.getYear());
            detailsBinding.detailsRuntime.setText(detailsData.getRuntime());
            detailsBinding.detailsGenre.setText(detailsData.getGenre());
            detailsBinding.detailsPlot.setText(detailsData.getPlot());
            detailsBinding.detailsRating.setText(detailsData.getImdbRating());
            //***don't forget poster binding as well!!!***
        });

        //get the intent sent from the previous activity
        Intent detailsIntent = getIntent();
        String movieID = detailsIntent.getStringExtra("ID");
        detailsViewModel.LoadDetails(movieID);

        detailsBinding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
