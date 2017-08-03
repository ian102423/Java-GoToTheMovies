package com.theironyard.GO_TO_THE_MOVIES;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

    private String title;
    @JsonProperty("poster_path")
    private String posterPath;//TODO: You will need to annotate this with @JsonProperty to make it match to poster_path
    private String overview;
    private double popularity;
    private static final String URL_IMG = "http://image.tmdb.org/t/p/original";

    Movie() {
    }

    public Movie(String title, String posterPath, String overview, double popularity) {
        this.title = title;
        this.posterPath = posterPath;
        this.overview = overview;
        this.popularity = popularity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = URL_IMG + posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }
}
