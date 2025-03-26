package com.example.assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.assignment2.databinding.DetailsActivityBinding;

public class DetailsActivity extends AppCompatActivity {

    MovieDetails movieDetails;

    MovieDetailsViewModel detailsViewModel;

    DetailsActivityBinding detailsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set out bindings, inflate it and the set the contentview
        detailsBinding = DetailsActivityBinding.inflate(getLayoutInflater());

        setContentView(detailsBinding.getRoot());

        //Again, set up a view model provider to observe for movie details
        detailsViewModel = new ViewModelProvider(this).get(MovieDetailsViewModel.class);
        detailsViewModel.getDetailsData().observe(this, detailsData -> {
            Log.i("tag", "Update View");
            movieDetails = detailsData;

            //set all the information to it's respective boxes
            detailsBinding.detailsTitle.setText(detailsData.getTitle());
            detailsBinding.detailsYear.setText("Released: " + detailsData.getYear());
            detailsBinding.detailsRuntime.setText("Length: " + detailsData.getRuntime());
            detailsBinding.detailsGenre.setText("Genres: " + detailsData.getGenre());
            detailsBinding.detailsPlot.setText("Description: \n" + detailsData.getPlot());
            detailsBinding.detailsRating.setText("Rating: " + detailsData.getImdbRating());

            //use glide to get the image from the url string if the value isn't "N/A"
            //otherwise, use default image
            if (!detailsData.Poster.equals("N/A")) {
                Glide.with(this).load(detailsData.Poster).into(detailsBinding.detailsPoster);
            }
        });

        //get the intent sent from the previous activity
        Intent detailsIntent = getIntent();
        //Extract the id so we can search for it's details on the API
        String movieID = detailsIntent.getStringExtra("ID");
        detailsViewModel.LoadDetails(movieID);

        //on click listener on the back button to finish current activity and go back to previous one
        detailsBinding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
