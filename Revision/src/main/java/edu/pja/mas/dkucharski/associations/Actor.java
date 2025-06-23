package edu.pja.mas.dkucharski.associations;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class Actor {
    private String name;
    private String surname;
    private LocalDate birthDate;

    private Map<String, Movie> moviesQualif = new TreeMap<>();

    public void addMovieQualif(Movie newMovie) {
        if (!moviesQualif.containsKey(newMovie.getTitle())) {
            moviesQualif.put(newMovie.getTitle(), newMovie);

            newMovie.addActor(this);
        }
    }

    public Movie findMovieQualif(String title) throws Exception {
        // Check if we have the info
        if(!moviesQualif.containsKey(title)) {
            throw new Exception("Unable to find a movie: " + title);
        }

        return moviesQualif.get(title);
    }

    public Actor(String name, String surname, LocalDate birthDate) {
        setName(name);
        setSurname(surname);
        setBirthDate(birthDate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (surname == null || surname.trim().isEmpty()) {
            throw new IllegalArgumentException("Surname cannot be null or empty");
        }
        this.surname = surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("Birth date cannot be null");
        }
        if (birthDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Birth date cannot be in the future");
        }
        this.birthDate = birthDate;
    }
}