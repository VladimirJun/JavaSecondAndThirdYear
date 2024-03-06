package org.example.task_13;

public class Group {
    private final int id;
    private final int[] nums;

    public Group(int id, int... data) {
        this.id = id;
        this.nums = data;
    }

    public int getId() {
        return id;
    }

    public int[] getNums() {
        return nums;
    }

    public int length() {
        return nums.length;
    }
}
