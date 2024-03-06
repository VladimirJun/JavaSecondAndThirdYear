package org.example.task_13;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DataIterator implements Iterator<Integer> {
    private int groupIndex;
    private int elementIndex;
    private final Data data;

    public DataIterator(Data data) {
        this.data = data;
        this.groupIndex = 0;
        this.elementIndex=0;
    }

    @Override
    public boolean hasNext() {
        return groupIndex < data.getGroups().length && elementIndex < data.getGroups()[groupIndex].length();
    }
    @Override
    public Integer next() {
        int value = data.getGroups()[groupIndex].getNums()[elementIndex];
        elementIndex++;

        if (elementIndex >= data.getGroups()[groupIndex].getNums().length) {
            groupIndex++;
            elementIndex = 0;
        }

        return value;
    }
}

