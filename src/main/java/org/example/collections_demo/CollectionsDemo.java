package org.example.collections_demo;

import org.example.human_student.ListDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CollectionsDemo {

    Logger logger = Logger.getLogger(CollectionsDemo.class.getName());
//
    public static int countMatchingFirstChar(List<String> list, char c) {
        int count = 0;
        for (String str : list) {
            if (str.charAt(0) == c) {
                count++;
            }
        }
        return count;
    }
}

