package com.theironyard.GO_TO_THE_MOVIES.controller;

import com.theironyard.GO_TO_THE_MOVIES.Movie;
import com.theironyard.GO_TO_THE_MOVIES.ResultPage;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import java.util.List;
import java.util.stream.Collectors;

public class Controller {

    private static final String API = "?api_key=be2a38521a7859c95e2d73c48786e4bb";
    private static final String URL = "https://api.themoviedb.org/3/movie/now_playing" + API;
    private static final RestTemplate restTemplate = new RestTemplate();
    private static Random random = new Random();

    public static List<Movie> getMovies(String route) {
        ResultPage resultPage = restTemplate.getForObject(URL, ResultPage.class);
        return resultPage.getResults();
    }

    @RequestMapping(path = "./home", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping(path = "./now-playing", method = RequestMethod.GET)
    public String nowPlaying(Model model) {
        model.addAttribute("movies", getMovies(URL));
        return "now-playing";
    }

    @RequestMapping(path = "./medium-popular-long-name", method = RequestMethod.GET)
    public String mediumPopularLongName(Model model) {
        model.addAttribute("movies", getMovies(URL)
                .stream()
                .filter(movie -> movie.getTitle().length() >= 10)
                .filter(movie -> movie.getPopularity() >= 30 && movie.getPopularity() <= 80)
                .collect(Collectors.toList()));
        return "medium-popular-long-name";
    }

    @RequestMapping(path = "./overview-mashup", method = RequestMethod.GET)
    public String overviewMashup(Model model) {
        String mashupString = "";
        List<String> randomSentences = new ArrayList<>();
        getMovies(URL).stream()
                .limit(5)
                .forEach((movie -> {
                    randomSentences.add(movie.getOverview()
                            .split("\\.")[random.nextInt(
                            movie.getOverview().split("\\.").length)]);
                }));
        Collections.sort(randomSentences, (e1, e2) -> random.nextInt(3) - 1);
        for (String sentence : randomSentences) {
            mashupString += sentence + ". ";
        }
        model.addAttribute("mashupString", mashupString);
        return "overview-mashup";
    }
}
