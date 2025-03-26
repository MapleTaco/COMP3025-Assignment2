package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import java.util.ArrayList;

import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class MovieViewModel extends ViewModel {

    private final MutableLiveData<List<Movie>> movieData = new MutableLiveData<List<Movie>>();

    List<Movie> movieModel = new ArrayList<Movie>();

    public LiveData<List<Movie>> getMovieData() {
        return movieData;
    }

    public void Refresh(String search) {
        movieModel = new ArrayList<Movie>();
        //url search string using out api key and only showing movie types
        String urlString = "https://www.omdbapi.com/?s=" + search + "&type=movie&apikey=490c742a";

        ApiClient.get(urlString, new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                assert response.body() != null;
                String responseData = response.body().string();

                JSONObject json = null;

                try {

                    json = new JSONObject(responseData);

                    //check in case we get an invalid search result. i.e. no movies pop up
                    if (json.getString("Response").equals("True")) {

                        JSONArray jsonArray = json.getJSONArray("Search");
                        JSONObject jsonMovieResult = null;
                        String strTitle, strYear, strID, strPoster;
                        for (int i = 0; i < jsonArray.length(); i++) {
                            int pos = i;
                            jsonMovieResult = jsonArray.getJSONObject(i);
                            strTitle = jsonMovieResult.getString("Title");
                            strYear = jsonMovieResult.getString("Year");
                            strID = jsonMovieResult.getString("imdbID");
                            strPoster = jsonMovieResult.getString("Poster");

                            movieModel.add(new Movie(strTitle, strYear, strID, strPoster));

                        }
                        movieData.postValue(movieModel);
                    }
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
