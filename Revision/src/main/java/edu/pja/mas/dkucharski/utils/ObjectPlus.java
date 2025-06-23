package edu.pja.mas.dkucharski.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectPlus implements Serializable {
    private static Map<Class<? extends ObjectPlus>, List<ObjectPlus>>
        allExtents = new HashMap<>();

    public ObjectPlus() {
        List<ObjectPlus> extent = null;
        Class theClass = this.getClass();

        if (allExtents.containsKey(theClass)) {
            extent = allExtents.get(theClass);
        } else {
            extent = new java.util.ArrayList<>();
            allExtents.put(theClass, extent);
        }

        extent.add(this);
    }

    // Alternative version of the constructor
//    public ObjectPlus() {
//        Class<? extends ObjectPlus> theClass = this.getClass();
//        allExtents.computeIfAbsent(theClass, k -> new ArrayList<>()).add(this);
//    }

    public static void writeExtents(ObjectOutputStream stream) throws IOException {
        stream.writeObject(allExtents);
    }

    public static void readExtents(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        allExtents = (HashMap) stream.readObject();
    }

    public static <T> Iterable<T> getExtent(Class<T> type) throws ClassNotFoundException {
        if (allExtents.containsKey(type)) {
            return (Iterable<T>) allExtents.get(type);
        }

        throw new ClassNotFoundException(String.format("No such class %s", type.toString(), allExtents.keySet()));
    }

    public static void showExtent(Class theClass) throws Exception {
        List<ObjectPlus> extent = null;

        if (allExtents.containsKey(theClass)) {
            // Extent of this class already exists
            extent = allExtents.get(theClass);
        } else {
            throw new Exception("Unknown class: " + theClass);
        }

        System.out.println("Extent of the class: " + theClass.getSimpleName());

        for (Object obj : extent) {
            System.out.println(obj);
        }
    }
}

