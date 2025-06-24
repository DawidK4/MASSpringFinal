package edu.pja.mas.dkucharski.associations;

public class Part {
    private String name;
    private Whole whole;

    private Part(Whole whole, String name) {
        setWhole(whole);
        setName(name);
    }

    public static Part createPart(Whole whole, String name) throws Exception {
        if (whole == null) {
            throw new IllegalArgumentException("The given whole does not exist!");
        }

        Part part = new Part(whole, name);

        whole.addPart(part);

        return part;
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

    public void setWhole(Whole whole) {
        if (whole == null) {
            throw new IllegalArgumentException("Whole cannot be null");
        }
        if (this.whole != null && !this.whole.equals(whole)) {
            throw new IllegalStateException("This part is already assigned to a different whole");
        }

        this.whole = whole;
    }
}
