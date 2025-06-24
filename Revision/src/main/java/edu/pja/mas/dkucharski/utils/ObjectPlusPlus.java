package edu.pja.mas.dkucharski.utils;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.*;

public abstract class ObjectPlusPlus extends ObjectPlus implements Serializable {
    /**
     * Stores information about all connections of this object.
     */
    private Map<String, Map<Object, ObjectPlusPlus>> links = new Hashtable<>();

    /**
     * Stores information about all parts connected with any objects.
     */
    private static Set<ObjectPlusPlus> allParts = new HashSet<>();

    /**
     * The constructor.
     */
    public ObjectPlusPlus(){
        super();
    }

    private void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject, Object qualifier,
                         int counter) {

        Map<Object, ObjectPlusPlus> objectLinks;

        if (counter < 1) {
            return;
        }

        if (links.containsKey(roleName)) {
            // Get the links
            objectLinks = links.get(roleName);
        } else {
            // No links => create them
            objectLinks = new HashMap<>();
            links.put(roleName, objectLinks);
        }

        // Check if there is already a connection
        // If yes, then ignore the creation
        if (!objectLinks.containsKey(qualifier)) {
            // Add a link for the target object
            objectLinks.put(qualifier, targetObject);

            // Add a reverse connection
            targetObject.addLink(reverseRoleName, roleName, this, this, counter - 1);
        }
    }

    public void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject, Object qualifier) {
        addLink(roleName, reverseRoleName, targetObject, qualifier, 2);
    }

    public void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject) {
        addLink(roleName, reverseRoleName, targetObject, targetObject);
    }

    public void addPart(String roleName, String reverseRoleName, ObjectPlusPlus partObject) throws Exception{
        // Check if the part exists somewhere
        if (allParts.contains(partObject)) {
            throw new Exception("Part already exists in the system.");
        }

        addLink(roleName, reverseRoleName, partObject);

        allParts.add(partObject);
    }

    public ObjectPlusPlus[] getLinks(String roleName) throws Exception{
        Map<Object, ObjectPlusPlus> objectLinks;

        if (!links.containsKey(roleName)) {
            // No links for the role
            throw new Exception("No links for the role: " + roleName);
        }

        objectLinks = links.get(roleName);
        return (ObjectPlusPlus[]) objectLinks.values().toArray(new ObjectPlusPlus[0]);
    }

    public void showLinks(String roleName, PrintStream stream) throws Exception {
        Map<Object, ObjectPlusPlus> objectLinks;

        if (!links.containsKey(roleName)) {
            // No links
            throw new  Exception("No links for the role: " + roleName);
        }

        objectLinks = links.get(roleName);
        Collection col = objectLinks.values();
        stream.println(this.getClass().getSimpleName() + " links, role '" + roleName + "':");

        for (Object obj : col) {
            stream.println(" " + obj);
        }
    }

    public ObjectPlusPlus getLinkedObject(String roleName, Object qualifier) throws Exception {
        Map<Object, ObjectPlusPlus> objectLinks;

        if (!links.containsKey(roleName)) {
            // No links
            throw new Exception("No links for the role: " + roleName);
        }

        objectLinks = links.get(roleName);
        if (!objectLinks.containsKey(qualifier)) {
            // No link for the qualifier
            throw new Exception("No link for the qualifier: " + qualifier);
        }

        return objectLinks.get(qualifier);
    }
}
