package com.theironyard.GO_TO_THE_MOVIES;

import java.util.List;

public class ResultPage {

    private List<Movie> results;

    ResultPage() {

    }

    public ResultPage(List<Movie> results) {
        this.results = results;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

}
