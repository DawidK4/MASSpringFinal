package edu.pja.mas.dkucharski.extenstion;

import java.util.ArrayList;
import java.util.List;

public class MovieExtent {
    private List<Movie> extent = new ArrayList<>();

    public void addMovie(Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("Movie cannot be null");
        }
        extent.add(movie);
    }

    public void removeMovie(Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("Movie cannot be null");
        }
        extent.remove(movie);
    }

    public void showExtent() {
        if (extent.isEmpty()) {
            System.out.println("No movies in the extent.");
        } else {
            System.out.println("Movies in the extent:");
            for (Movie movie : extent) {
                System.out.println("Title: " + movie.getTitle() + ", Release Date: " + movie.getReleaseDate());
            }
        }
    }
}
