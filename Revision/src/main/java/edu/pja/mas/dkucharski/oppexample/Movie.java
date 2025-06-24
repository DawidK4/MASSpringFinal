package edu.pja.mas.dkucharski.oppexample;

import edu.pja.mas.dkucharski.utils.ObjectPlusPlus;

public class Movie extends ObjectPlusPlus {
    private String title;

    public Movie(String title) {
        super();
        this.title = title;
    }

    @Override
    public String toString() {
        return "Movie: " + title;
    }
}
