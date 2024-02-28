package org.example.task_13;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;

public class DataDemoTest extends TestCase {

        public void testGetAll() {
           Group group1 = new Group(1,0);
           Group group2 = new Group(2,2);
           Data data = new Data("Test Data", group1, group2);
           List<Integer> expected = Arrays.asList(0, 2);
           assertEquals(expected, DataDemo.getAll(data));
        }
}