package edu.pja.mas.dkucharski.extenstion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String title;
    private LocalDate releaseDate;
    /**
     * The extent. Non-final required - see further (persistency).
     */
    private static List<Movie> extent = new ArrayList<>();

    public Movie(String title, LocalDate releaseDate) {
        setTitle(title);
        setReleaseDate(releaseDate);
    }

    private Movie() {
        // Used for persistence
    }

    /**
     * Adds a movie to the extent.
     *
     * @param movie the movie
     */
    private static void addMovie(Movie movie) {
        extent.add(movie);
    }
    /**
     * Removes a movie from the extent.
     *
     * @param movie the movie
     */
    private static void removeMovie(Movie movie) {
        extent.remove(movie);
    }

    /** Shows the extent of movies.
     *
     * @return the list of movies
     */
    public static void showExtent() {
        if (extent.isEmpty()) {
            System.out.println("No movies in the extent.");
        } else {
            System.out.println("Movies in the extent:");
            for (Movie movie : extent) {
                System.out.println("Title: " + movie.getTitle() + ", Release Date: " + movie.getReleaseDate());
            }
        }
    }

    // Extension methods for persistency
    public static void writeExtent(DataOutputStream stream) throws IOException {
        stream.writeInt(extent.size());

        for (Movie movie : extent) {
            movie.write(stream);
        }
    }

    public static void readExtent(DataInputStream stream) throws IOException {
        int objectCount = stream.readInt();
        extent.clear();
        for (int i = 0; i < objectCount; i++) {
            Movie movie = new Movie();  // requires a private no-arg constructor
            movie.read(stream);
            addMovie(movie);
        }
    }

    private void write(DataOutputStream stream) throws IOException {
        stream.writeUTF(title);
        stream.writeLong(releaseDate.toEpochDay()); // count of days where day 0 is 1970 01-01 (ISO)
    }

    private void read(DataInputStream stream) throws IOException {
        title = stream.readUTF();
        long epochDay = stream.readLong();
        releaseDate = LocalDate.ofEpochDay(epochDay); // convert days since epoch to LocalDate
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        if (releaseDate == null) {
            throw new IllegalArgumentException("Release date cannot be null");
        }
        if (releaseDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Release date cannot be in the future");
        }
        this.releaseDate = releaseDate;
    }
}