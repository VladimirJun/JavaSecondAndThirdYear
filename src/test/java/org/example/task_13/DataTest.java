package org.example.task_13;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataTest {

    @Test
    public void testGetName() {
        Group group1 = new Group(1);
        Data data = new Data("Test Data", group1);
        assertEquals("Test Data", data.getName());
    }

    @Test
    public void testGetGroups() {
        Group group1 = new Group(1);
        Group group2 = new Group(2);
        Data data = new Data("Test Data", group1, group2);
        assertArrayEquals(new Group[]{group1, group2}, data.getGroups());
    }

    @Test
    public void testLength() {
        Group group1 = new Group(1);
        Group group2 = new Group(2);
        Data data = new Data("Test Data", group1, group2);
        assertEquals(2, data.length());
    }

    @Test
    public void testIterator() {
        Group group1 = new Group(0, 1, 2, 3,-4);
        Group group2 = new Group(1, 4, 5, 6);
        Data data = new Data("Test Data", group1, group2);
        Iterator<Integer> iterator = data.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}