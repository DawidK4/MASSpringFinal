package edu.pja.mas.dkucharski.oppexample;

import edu.pja.mas.dkucharski.utils.ObjectPlusPlus;

public class Group extends ObjectPlusPlus {
    private int number;

    public Group(int number) {
        super();
        this.number = number;
    }

    @Override
    public String toString() {
        return "Group: " + number;
    }
}
