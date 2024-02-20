package org.example.collections_demo;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CollectionsDemoTest extends TestCase {

    Logger logger = Logger.getLogger(CollectionsDemoTest.class.getName());

    public void testCountMatchingFirstChar() {
        List<String> list = new ArrayList<>();
        List<String> listEmpty = new ArrayList<>();
        String s1 = "Cord";
        String s2 = "Cold";
        String s3 = "world";
        String s4 = "person";
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        logger.info(list.toString());
        System.out.println("===TEST ONE EXECUTED===");
        assertEquals(2,CollectionsDemo.countMatchingFirstChar(list,'C'));
        System.out.println("===TEST TWO EXECUTED===");
        assertEquals(1,CollectionsDemo.countMatchingFirstChar(list,'w'));
        System.out.println("===TEST THREE EXECUTED===");
        assertEquals(0,CollectionsDemo.countMatchingFirstChar(list,'a'));
        System.out.println("===TEST FOUR FOR EMPTY EXECUTED===");
        assertEquals(0,CollectionsDemo.countMatchingFirstChar(listEmpty,'a'));

    }
}