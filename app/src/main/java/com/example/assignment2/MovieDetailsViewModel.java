package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MovieDetailsViewModel extends ViewModel {

    private final MutableLiveData<MovieDetails> detailsData = new MutableLiveData<MovieDetails>();

    MovieDetails movieDetailsModel = new MovieDetails();

    public LiveData<MovieDetails> getDetailsData() {
        return detailsData;
    }

    public void LoadDetails(String movieID) {
        String urlString = "https://www.omdbapi.com/?i="+movieID+"&apikey=490c742a";

        ApiClient.get(urlString, new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                assert response.body() != null;
                String responseData = response.body().string();

                JSONObject json = null;

                try {

                    json = new JSONObject(responseData);
                    String strTitle, strYear, strRuntime, strGenre, strPlot, strImdbRating, strPoster;
                    strTitle = json.getString("Title");
                    strYear = json.getString("Year");
                    strRuntime = json.getString("Runtime");
                    strGenre = json.getString("Genre");
                    strPlot = json.getString("Plot");
                    strImdbRating = json.getString("imdbRating");
                    strPoster = json.getString("Poster");

                    movieDetailsModel.setTitle(strTitle);
                    movieDetailsModel.setYear(strYear);
                    movieDetailsModel.setRuntime(strRuntime);
                    movieDetailsModel.setGenre(strGenre);
                    movieDetailsModel.setPlot(strPlot);
                    movieDetailsModel.setImdbRating(strImdbRating);
                    movieDetailsModel.setPoster(strPoster);

                    detailsData.postValue(movieDetailsModel);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }
        });
    }
}
