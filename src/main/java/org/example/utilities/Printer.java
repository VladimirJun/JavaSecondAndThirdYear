package org.example.utilities;

import java.util.List;

public class Printer {
    public static void print(List<String> list) {
        int i = 1;
        for (String line : list) {
            System.out.printf("%d: %s%n", i, line);
            i++;
        }
    }
}