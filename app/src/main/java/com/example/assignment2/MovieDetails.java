package com.example.assignment2;

public class MovieDetails {

    String Title, Year, Runtime, Genre, Plot, imdbRating, Poster;

    public MovieDetails(String title, String year, String runtime, String genre, String plot, String imdbRating, String poster) {
        Title = title;
        Year = year;
        Runtime = runtime;
        Genre = genre;
        Plot = plot;
        this.imdbRating = imdbRating;
        Poster = poster;
    }

    public MovieDetails() {

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

    public String getRuntime() {
        return Runtime;
    }

    public void setRuntime(String runtime) {
        Runtime = runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getPlot() {
        return Plot;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }
}
