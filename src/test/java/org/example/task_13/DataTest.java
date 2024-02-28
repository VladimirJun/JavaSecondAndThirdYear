package org.example.task_13;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        Group group1 = new Group(0,1,2,3);
        Group group2 = new Group(1,4,5,6);
        Data data = new Data("Test Data", group1, group2);
        Iterator<Integer> iterator = data.iterator();

        List<Integer> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        assertEquals(Arrays.asList(1,4), result);
    }


    }