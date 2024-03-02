package org.example.phoneBookl;

import junit.framework.TestCase;
import org.example.human_student.Human;
import org.example.phoneBook.PhoneBook;

import java.util.*;

public class PhoneBookTest extends TestCase {
    Human h1 = new Human("Петров", "Владимир", "Юрьевич", 19);
    Human h2 = new Human("Иванов", "Иван", "Иванович", 30);
    Human h3 = new Human("Сергеев", "Сергей", "Сергеевич", 23);
    String ph1 = "89522280000";
    String ph11 = "88001234567";
    String ph2 = "12345678910";
    String ph3 = "24682468777";


    public void testAddPhone() {

        List<Human> humans = new ArrayList<Human>();
        humans.add(h1);
        humans.add(h2);
        humans.add(h3);
        List<String> phones1 = new ArrayList<String>();
        phones1.add(ph1);
        phones1.add(ph11);
        List<String> phones2 = new ArrayList<String>();
        phones2.add(ph3);
        List<String> phones3 = new ArrayList<String>();
        phones3.add(ph3);
        PhoneBook book = new PhoneBook();
        book.addPhone(h1, ph1);
        book.addPhone(h1, ph11);
        book.addPhone(h2, ph2);
        book.addPhone(h3, ph3);
        assertEquals(phones1, book.getPhoneList(h1));
    }
    public void testAddPhone2() {
        List<Human> humans = new ArrayList<Human>();
        humans.add(h1);
        humans.add(h2);
        humans.add(h3);
        List<String> phones1 = new ArrayList<String>();
        phones1.add(ph1);
        phones1.add(ph11);
        List<String> phones2 = new ArrayList<String>();
        phones2.add(ph3);
        List<String> phones3 = new ArrayList<String>();
        phones3.add(ph3);
        PhoneBook book = new PhoneBook();
        book.addPhone(h1, ph1);
        book.addPhone(h1, ph11);
        book.addPhone(h2, ph2);
        book.addPhone(h3, ph3);
        List<String> phoneList = book.getPhoneList(h3);
        assertEquals(phones3, book.getPhoneList(h3));
    }
    public void testAddPhone3() {
        List<Human> humans = new ArrayList<Human>();
        humans.add(h1);
        humans.add(h2);
        humans.add(h3);
        PhoneBook book = new PhoneBook();
        List<String> phoneList = book.getPhoneList(h3);
        System.out.println("===TEST FOR EMPTY EXECUTED===");
        assertEquals(null, book.getPhoneList(h3));
    }

    public void testRemovePhone() {
        List<Human> humans = new ArrayList<Human>();
        humans.add(h1);
        humans.add(h2);
        humans.add(h3);
        List<String> phones1 = new ArrayList<String>();
        phones1.add(ph1);
        PhoneBook book = new PhoneBook();
        book.addPhone(h1, ph1);
        book.addPhone(h1, ph11);
        book.addPhone(h2, ph2);
        book.addPhone(h3, ph3);
        book.removePhone(h1,ph11);
        assertEquals(phones1,book.getPhoneList(h1));


    }
    public void testRemovePhone2(){
        List<Human> humans = new ArrayList<Human>();
        humans.add(h1);
        humans.add(h2);
        humans.add(h3);
        List<String> phones1 = new ArrayList<String>();
        PhoneBook book = new PhoneBook();
        book.addPhone(h1, ph1);
        book.removePhone(h1,ph1);
        book.addPhone(h2, ph2);
        book.addPhone(h3, ph3);
        book.removePhone(h1,ph11);
        assertEquals(phones1,book.getPhoneList(h1));
    }
    public void testRemovePhoneForEmpty() {
        List<String> phones1 = new ArrayList<String>();
        PhoneBook book = new PhoneBook();
        book.addPhone(h1, ph1);
        book.removePhone(h1, ph1);
        book.addPhone(h2, ph2);
        book.addPhone(h3, ph3);
        book.removePhone(h1, ph11);
        assertEquals(phones1, book.getPhoneList(h1));
    }


        public void testGetPhoneList1() {
            List<Human> humans = new ArrayList<Human>();
            humans.add(h1);
            humans.add(h2);
            humans.add(h3);
            List<String> phones1 = new ArrayList<String>();
            phones1.add(ph1);
            phones1.add(ph11);
            PhoneBook book = new PhoneBook();
            book.addPhone(h1, ph1);
            book.addPhone(h1,ph11);
            book.addPhone(h2, ph2);
            book.addPhone(h3, ph3);
            assertEquals(phones1,book.getPhoneList(h1));
    }
    public void testGetPhoneList2() {
        List<Human> humans = new ArrayList<Human>();
        humans.add(h1);
        humans.add(h2);
        humans.add(h3);
        List<String> phones2 = new ArrayList<String>();
        phones2.add(ph1);
        phones2.add(ph11);
        PhoneBook book = new PhoneBook();
        book.addPhone(h2, ph1);
        book.addPhone(h2,ph11);
        book.addPhone(h1, ph2);
        book.addPhone(h3, ph3);
        assertEquals(phones2,book.getPhoneList(h2));
    }
    public void testGetPhoneListEmpty() {
        List<Human> humans = new ArrayList<Human>();
        humans.add(h1);
        humans.add(h2);
        humans.add(h3);
        PhoneBook book = new PhoneBook();
        book.addPhone(h2, ph1);
        book.addPhone(h2,ph11);
        book.addPhone(h3, ph3);
        assertEquals(null,book.getPhoneList(h1));
    }

    public void testFindPersonByPhone1() {
        PhoneBook book = new PhoneBook();
        book.addPhone(h1, ph1);
        book.addPhone(h1,ph11);
        book.addPhone(h3, ph3);
        assertEquals(h1,book.findPersonByPhone(ph1));
    }
    public void testFindPersonByPhone2() {
        PhoneBook book = new PhoneBook();
        book.addPhone(h1, ph1);
        book.addPhone(h1,ph11);
        book.addPhone(h3, ph3);
        assertEquals(book.findPersonByPhone(ph1),book.findPersonByPhone(ph11));
    }
    public void testFindPersonByPhoneForEmpty() {
        PhoneBook book = new PhoneBook();
        book.addPhone(h1, ph1);
        book.addPhone(h1,ph11);
        book.addPhone(h3, ph3);
        assertEquals(null,book.findPersonByPhone(ph2));
    }

    public void testFindPeopleByName1() {
        PhoneBook book = new PhoneBook();
        book.addPhone(h1, ph1);
        book.addPhone(h1,ph11);
        book.addPhone(h3, ph3);
        List<String> phones1 = new ArrayList<String>();
        phones1.add(ph1);
        phones1.add(ph11);
        Map<Human, List<String>> result = book.findPeopleByName(h1.getSurname());
        assertEquals(phones1,result.get(h1));
    }
    public void testFindPeopleByNameEmpty() {
        PhoneBook book = new PhoneBook();
        book.addPhone(h1, ph1);
        book.addPhone(h1,ph11);
        book.addPhone(h3, ph3);
        List<String> phones1 = new ArrayList<String>();
        phones1.add(ph1);
        phones1.add(ph11);
        Map<Human, List<String>> result = book.findPeopleByName(h1.getName());
        assertEquals(null,result.get(h2));
    }
}