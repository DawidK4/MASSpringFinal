package edu.pja.mas.dkucharski.extenstion;

import edu.pja.mas.dkucharski.utils.ObjectPlus;

import java.io.Serializable;
import java.time.LocalDate;

public class Movie2 extends ObjectPlus implements Serializable {
    private String title;
    private float price;
    private LocalDate additionDate;

    public Movie2(String title, LocalDate additionDate, float price) {
        super();

        this.title = title;
        this.additionDate = additionDate;
        this.price = price;
    }

    public static void showExtent() throws Exception {
        ObjectPlus.showExtent(Movie2.class);
    }
}
