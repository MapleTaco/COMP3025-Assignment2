package com.example.assignment2;

import android.graphics.Bitmap;

public class Movie {
    private String Title, Year, imdbID, PosterURL;

    private Bitmap Poster;

    public Movie(String title, String year, String imdbID, String poster) {
        Title = title;
        Year = year;
        this.imdbID = imdbID;
        PosterURL = poster;
    }

    public Movie() {

    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getPosterURL() {
        return PosterURL;
    }

    public void setPosterURL(String posterURL) {
        PosterURL = posterURL;
    }

    public Bitmap getPoster() {
        return Poster;
    }

    public void setPoster(Bitmap poster) {
        Poster = poster;
    }
}
