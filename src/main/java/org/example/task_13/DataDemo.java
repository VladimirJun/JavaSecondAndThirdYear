package org.example.task_13;

import java.util.ArrayList;
import java.util.List;
//
public class DataDemo {
    public static List<Integer> getAll(Data data) {
        List<Integer> result = new ArrayList<>();
        for (DataIterator it = new DataIterator(data); it.hasNext(); ) {
            int i = it.next();
            result.add(i);
        }
        return result;
    }
}
