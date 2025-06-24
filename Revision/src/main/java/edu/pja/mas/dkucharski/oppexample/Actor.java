package edu.pja.mas.dkucharski.oppexample;

import edu.pja.mas.dkucharski.utils.ObjectPlusPlus;

public class Actor extends ObjectPlusPlus {
    private String name;

    public Actor(String name) {
        super();
        this.name = name;
    }

    @Override
    public String toString() {
        return "Actor: " + name;
    }
}
