package org.example.task13;

import java.util.Random;

class Data {
    private final int[] values;

    public Data() {
        Random rand = new Random();
        values = new int[5];
        for (int i = 0; i < values.length; i++) {
            values[i] = rand.nextInt(100);
        }
    }

    public int[] get() {
        return values;
    }
}

