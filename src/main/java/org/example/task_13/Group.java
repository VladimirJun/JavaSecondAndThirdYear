package org.example.task_13;

public class Group {
    private final int id;
    private final int[] data;

    public Group(int id, int... data) {
        this.id = id;
        this.data = data;
    }

    public int getId() {
        return id;
    }
//
    public int[] getData() {
        return data;
    }

    public int length() {
        return data.length;
    }
}
