package org.example.task_13;

import java.util.Iterator;

public class Data {
    private final String name;
    private final Group[] groups;

    public Data(String name, Group... groups) {
        this.name = name;
        this.groups = groups;
    }
    public String getName() {
        return name;
    }

    public Group[] getGroups() {
        return groups;
    }

    public int length() {
        return groups.length;
    }
    public Iterator<Integer> iterator() {
        return new DataIterator(this);
    }


}
