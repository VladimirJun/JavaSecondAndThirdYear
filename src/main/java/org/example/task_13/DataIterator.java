package org.example.task_13;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DataIterator implements Iterator<Integer> {
    private int index;
    private final Data data;

    public DataIterator(Data data) {
        this.data = data;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < data.length();
    }
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int result = data.getGroups()[index].getData()[0];
        index++;
        return result;
    }
}

