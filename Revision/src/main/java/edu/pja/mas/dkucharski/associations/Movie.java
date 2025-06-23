package edu.pja.mas.dkucharski.associations;

public class Movie {
    private String title;
    private float price;
    private Actor actor;

    public Movie(String title, float price) {
        setTitle(title);
        setPrice(price);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }

        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        this.price = price;
    }

    public Actor getActor() {
        return actor;
    }

    public void addActor(Actor actor) {
        if (actor == null) {
            throw new IllegalArgumentException("Actor cannot be null");
        }
        if (this.actor != null && !this.actor.equals(actor)) {
            throw new IllegalStateException("This movie already has an actor assigned");
        }
        this.actor = actor;
        actor.addMovieQualif(this);
    }
}
