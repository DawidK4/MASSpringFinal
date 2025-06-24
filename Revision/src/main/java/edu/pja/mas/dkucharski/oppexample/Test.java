package edu.pja.mas.dkucharski.oppexample;

public class Test {
    public static void main(String[] args) {
        try {
            testAssociationsObjectPlus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testAssociationsObjectPlus() throws Exception {
        // Create new objects (no links)
        Actor a1 = new Actor("Arnold Schwarzenegger");
        Actor a2 = new Actor("Michael Biehn");
        Actor a3 = new Actor("Kristanna Loken");
        Movie f1 = new Movie("Terminator 1");
        Movie f3 = new Movie("Terminator 3");
        Group g1 = new Group(1);
        Group g2 = new Group(2);
        // Add info about links
        f1.addLink("actor", "movie", a1);
        // f1.addLink("actor", "movie", a2);
        f1.addLink("actor", "movie", a2, "MB");
        f3.addLink("actor", "movie", a1);
        f3.addLink("actor", "movie", a3);
        // use the qualified association
        g1.addPart("part", "whole", a1);
        g1.addPart("part", "whole", a2);
        g2.addPart("part", "whole", a3);
        // g2.addPart("part", "whole", a1); // an exception because the part already belongs to another whole (group)
        f1.showLinks("actor", System.out);
        f3.showLinks("actor", System.out);
        a1.showLinks("movie", System.out);
        g1.showLinks("part", System.out);
        // Test the qualified association
        System.out.println(f1.getLinkedObject("actor", "MB"));
    }
}
