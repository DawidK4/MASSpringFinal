package edu.pja.mas.dkucharski.associations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Whole {
    private List<Part> parts = new ArrayList<>();
    private static Set<Part> allParts = new HashSet<>();

    private String name;

    public Whole(String name) {
        setName(name);
    }

    public void addPart(Part part) throws Exception {
        if (!parts.contains(part)) {
            if (allParts.contains(part)) {
                throw new Exception("This part already exists in another whole");
            }

            parts.add(part);

            allParts.add(part);
        }
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }
}
