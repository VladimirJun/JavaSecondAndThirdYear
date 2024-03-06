package org.example.task_13;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;

public class DataDemoTest extends TestCase {

        public void testGetAll1() {
           Group group1 = new Group(1,1,2,3);
           Group group2 = new Group(2,4,5,6);
           Group group3 = new Group(3);
           Data data = new Data("Test Data", group1, group2);
            Data dataEmpty = new Data("Test Empty", group3);
           List<Integer> expected = Arrays.asList(1,2,3,4,5,6);
           assertEquals(expected,DataDemo.getAll(data));
            List<Integer> expected2 = Arrays.asList();
            assertEquals(expected2,DataDemo.getAll(dataEmpty));
        }
    public void testGetAll2() {
        Group group3 = new Group(3);
        Data dataEmpty = new Data("Test Empty", group3);
        List<Integer> expected2 = Arrays.asList();
        assertEquals(expected2,DataDemo.getAll(dataEmpty));
    }
    public void testGetAll3() {
        Group group1 = new Group(1,1,2,3);
        Group group2 = new Group(2,4,5,6);
        Group group3 = new Group(3, -7,-8,-9);
        Data data = new Data("Test Data", group1, group2, group3);
        List<Integer> expected = Arrays.asList(1,2,3,4,5,6,-7,-8,-9);
        assertEquals(expected,DataDemo.getAll(data));
    }
}