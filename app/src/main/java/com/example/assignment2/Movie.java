package com.example.assignment2;

import java.util.List;

public class Movie {
    private String Title, Year, imdbID, Type, Poster;

    public Movie(String title, String year, String imdbID, String type, String poster) {
        Title = title;
        Year = year;
        this.imdbID = imdbID;
        Type = type;
        Poster = poster;
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

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }
}
