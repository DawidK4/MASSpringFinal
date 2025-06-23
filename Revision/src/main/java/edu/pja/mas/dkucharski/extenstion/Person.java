package edu.pja.mas.dkucharski.extenstion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable {
    private String name;
    private String surname;
    private LocalDate dateOfBirth;

    private static List<Person> extent = new ArrayList<>();

    public static void writeExtent(ObjectOutputStream stream) throws IOException {
        stream.writeObject(extent);
    }

    public static void readExtent(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        extent = (ArrayList<Person>) stream.readObject();
    }
}
