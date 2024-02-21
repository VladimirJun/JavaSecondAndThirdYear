package org.example.human_student;

import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLOutput;
import java.util.*;

import static org.example.human_student.ListDemo.buildAgeLetterMap;
import static org.example.human_student.ListDemo.buildAgeMap;

public class ListDemoTest extends TestCase {
    public void testFindSameSurname1() {
        Human h0 = new Human("Петросян", "Тигран", "Вартанович", 54);
        Human h1 = new Human("Петров", "Владимир", "Юрьевич", 19);
        Human h2 = new Human("Иванов", "Иван", "Иванович", 30);
        Human h3 = new Human("Сергеев", "Сергей", "Сергеевич", 23);
        Human h4 = new Human("Антонов", "Антон", "Павлович", 45);
        Human h5 = new Human("Петров", "Юрий", "Валентинович", 76);
        Human h6 = new Human("Петров", "Сергей", "Иванович", 11);
        List<Human> persons = new ArrayList<>();
        persons.add(h2);
        persons.add(h3);
        persons.add(h4);
        persons.add(h5);
        persons.add(h6);
        List<Human> personsSamePetrov = new ArrayList<>();
        personsSamePetrov.add(h5);
        personsSamePetrov.add(h6);
        assertEquals(personsSamePetrov, ListDemo.findSameSurname(persons, h1));
    }

    public void testFindSameSurname2() {
        Human h0 = new Human("Петросян", "Тигран", "Вартанович", 54);
        Human h1 = new Human("Петров", "Владимир", "Юрьевич", 19);
        Human h2 = new Human("Иванов", "Иван", "Иванович", 30);
        Human h3 = new Human("Сергеев", "Сергей", "Сергеевич", 23);
        Human h4 = new Human("Антонов", "Антон", "Павлович", 45);
        Human h5 = new Human("Петров", "Юрий", "Валентинович", 76);
        Human h6 = new Human("Петров", "Сергей", "Иванович", 11);
        List<Human> persons = new ArrayList<>();
        persons.add(h2);
        persons.add(h3);
        persons.add(h4);
        persons.add(h5);
        persons.add(h6);
        List<Human> personsSameIvanov = new ArrayList<>();
        personsSameIvanov.add(h2);
        assertEquals(personsSameIvanov, ListDemo.findSameSurname(persons, h2));

    }

    public void testFindSameSurname3() {
        Human h0 = new Human("Петросян", "Тигран", "Вартанович", 54);
        List<Human> persons = new ArrayList<>();
        List<Human> personsSameEmpty = new ArrayList<>();
        assertEquals(personsSameEmpty, ListDemo.findSameSurname(persons, h0));
    }

    public void testRemovePerson1() {
        Human h1 = new Human("Петров", "Владимир", "Юрьевич", 19);
        Human h2 = new Human("Иванов", "Иван", "Иванович", 30);
        Human h3 = new Human("Сергеев", "Сергей", "Сергеевич", 23);
        Human h4 = new Human("Антонов", "Антон", "Павлович", 45);
        Human h5 = new Human("Петров", "Юрий", "Валентинович", 76);
        Human h6 = new Human("Петров", "Сергей", "Иванович", 11);
        List<Human> persons = new ArrayList<>();
        persons.add(h1);
        persons.add(h2);
        persons.add(h3);
        persons.add(h4);
        persons.add(h5);
        persons.add(h6);
        List<Human> personsWithoutSergeev = new ArrayList<>();
        personsWithoutSergeev.add(h1);
        personsWithoutSergeev.add(h2);
        personsWithoutSergeev.add(h4);
        personsWithoutSergeev.add(h5);
        personsWithoutSergeev.add(h6);
        assertEquals(personsWithoutSergeev, ListDemo.removePerson(persons, h3));
    }

    public void testRemovePerson2() {
        Human h0 = new Human("Петросян", "Тигран", "Вартанович", 54);
        Human h1 = new Human("Петров", "Владимир", "Юрьевич", 19);
        Human h2 = new Human("Иванов", "Иван", "Иванович", 30);
        Human h3 = new Human("Сергеев", "Сергей", "Сергеевич", 23);
        Human h4 = new Human("Антонов", "Антон", "Павлович", 45);
        Human h5 = new Human("Петров", "Юрий", "Валентинович", 76);
        Human h6 = new Human("Петров", "Сергей", "Иванович", 11);
        List<Human> persons = new ArrayList<>();
        persons.add(h1);
        persons.add(h2);
        persons.add(h3);
        persons.add(h4);
        persons.add(h5);
        persons.add(h6);
        assertEquals(persons, ListDemo.removePerson(persons, h0));
    }

    public void testRemovePerson3() {
        Human h0 = new Human("Петросян", "Тигран", "Вартанович", 54);
        Human h1 = new Human("Петров", "Владимир", "Юрьевич", 19);
        Human h2 = new Human("Иванов", "Иван", "Иванович", 30);
        Human h3 = new Human("Сергеев", "Сергей", "Сергеевич", 23);
        Human h4 = new Human("Антонов", "Антон", "Павлович", 45);
        Human h5 = new Human("Петров", "Юрий", "Валентинович", 76);
        Human h6 = new Human("Петров", "Сергей", "Иванович", 11);
        List<Human> persons = new ArrayList<>();
        persons.add(h1);
        persons.add(h2);
        persons.add(h3);
        persons.add(h4);
        persons.add(h5);
        persons.add(h6);
        List<Human> personsWithoutMe = new ArrayList<>();
        personsWithoutMe.add(h2);
        personsWithoutMe.add(h3);
        personsWithoutMe.add(h4);
        personsWithoutMe.add(h5);
        personsWithoutMe.add(h6);
        assertEquals(personsWithoutMe, ListDemo.removePerson(persons, h1));
    }

    public void testRemovePerson4() {
        Human h0 = new Human("Петросян", "Тигран", "Вартанович", 54);
        List<Human> persons = new ArrayList<>();
        assertEquals(persons, ListDemo.removePerson(persons, h0));
    }

    public void testEmptyList1() {
        List<Set<Integer>> sets = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        assertTrue(ListDemo.findNonIntersectingSets(sets, set).isEmpty());
    }

    public void testEmptyList2() {
        List<Set<Integer>> sets = new ArrayList<>();
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        Set<Integer> set3 = new HashSet<>();
        Set<Integer> setCheckout = new HashSet<>();
        setCheckout.add(5);
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set2.add(1);
        set2.add(2);
        set2.add(4);
        set3.add(1);
        set3.add(2);
        set3.add(5);
        sets.add(set1);
        sets.add(set2);
        sets.add(set3);
        List<Set<Integer>> setsCheck = new ArrayList<>();
        setsCheck.add(set1);
        setsCheck.add(set2);
        assertEquals(setsCheck, ListDemo.findNonIntersectingSets(sets, setCheckout));
    }

    //5
    public void testFindMaxAge() {
        Human h1 = new Human("Петров", "Владимир", "Юрьевич", 30);
        Human h2 = new Human("Иванов", "Иван", "Иванович", 30);
        Human h3 = new Human("Сергеев", "Сергей", "Сергеевич", 30);
        Student s1 = new Student("Антонов", "Антон", "Антонович", 30, "ФЦТК");
        List<Human> humans = new ArrayList<Human>();

        humans.add(s1);
        humans.add(h1);
        humans.add(h2);
        humans.add(h3);
        Set <Human> expected = new HashSet<Human>();
        expected.add(s1);
        expected.add(h2);
        expected.add(h3);
        expected.add(h1);
        assertEquals(expected, ListDemo.findMaxAge(humans));
    }

    public void testFindMaxAge2() {
        Human h1 = new Human("Петров", "Владимир", "Юрьевич", 30);
        Human h2 = new Human("Иванов", "Иван", "Иванович", 30);
        Human h3 = new Human("Сергеев", "Сергей", "Сергеевич", 30);
        Student s1 = new Student("Антонов", "Антон", "Антонович", 30, "ФЦТК");
        List<Human> humans = new ArrayList<Human>();
        humans.add(s1);
        humans.add(h1);
        humans.add(h2);
        humans.add(h3);
        assertTrue(ListDemo.findMaxAge(humans).contains(h2));
        assertTrue(ListDemo.findMaxAge(humans).contains(h1));
        assertTrue(ListDemo.findMaxAge(humans).contains(h2));
        assertTrue(ListDemo.findMaxAge(humans).contains(h3));
    }

    public void testFindMaxAge3() {
        Human h1 = new Human("Петров", "Владимир", "Юрьевич", 19);
        Human h2 = new Human("Иванов", "Иван", "Иванович", 30);
        Human h3 = new Human("Сергеев", "Сергей", "Сергеевич", 23);
        Student s1 = new Student("Антонов", "Антон", "Антонович", 18, "ФЦТК");
        List<Human> humans = new ArrayList<Human>();
        humans.add(s1);
        humans.add(h1);
        humans.add(h2);
        humans.add(h3);
        assertTrue(ListDemo.findMaxAge(humans).contains(h2));

    }

    //6
    public void testBuildSortedlist1() {
        Set<Human> humans = new HashSet<>();
        Human human1 = new Human("Ильин", "Никита", "Евгеньевич", 25);
        Human human2 = new Human("Смирнов", "Алексей", "Алексеевич", 25);
        Human human11 = new Human("Ильев", "Виктор", "Петрович", 25);
        Human human3 = new Human("Петров", "Юрий", "Петрович", 30);
        Human human4 = new Human("Сидоров", "Сергей", "Сергеевич", 30);
        humans.add(human1);
        humans.add(human2);
        humans.add(human3);
        humans.add(human4);
        humans.add(human11);
        List<Human> sortedHumans = new ArrayList<>();
        sortedHumans.add(human11);
        sortedHumans.add(human1);
        sortedHumans.add(human3);
        sortedHumans.add(human4);
        sortedHumans.add(human2);
        assertEquals(sortedHumans,ListDemo.buildSortedlist(humans));
    }

    public void testBuildSortedlist2() {
        Set<Human> humans = new HashSet<>();
        Human human3 = new Human("Петров", "Юрий", "Петрович", 30);
        Human human33 = new Human("Петров", "Алексей", "Владимирович", 28);
        Human human333 = new Human("Петров", "Алексей", "Владимирович", 200);
        humans.add(human3);
        humans.add(human333);
        humans.add(human33);
        System.out.println(humans);
        System.out.println(ListDemo.buildSortedlist(humans));

        List<Human> sortedHumans = new ArrayList<>();
        sortedHumans.add(human33);
        sortedHumans.add(human333);
        sortedHumans.add(human3);
        System.out.println(sortedHumans);
        assertEquals(sortedHumans, ListDemo.buildSortedlist(humans));
    }

    public void testBuildSortedlist3() {
        Set<Human> humans = new HashSet<>();
        List<Human> empty = new ArrayList<>();
        assertEquals(empty, ListDemo.buildSortedlist(humans));

    }

    //7
    public void testGetHumansByIdsEmpty() {
        Map<Integer, Human> idHuman = new HashMap<>();
        Human h1 = new Human("Иванов", "Иван", "Иванович", 25);
        Set<Integer> ids = new HashSet<>();
        Set<Human> expected = new HashSet<>();
        Set<Human> actual = ListDemo.getHumansByIds(idHuman, ids);
        assertEquals(expected, actual);
    }

    public void testGetHumansByIdsEqual() {
        Map<Integer, Human> idHuman = new HashMap<>();
        Human h1 = new Human("Иванов", "Иван", "Иванович", 25);
        Human h2 = new Human("Смирнов", "Алексей", "Алексеевич", 25);
        idHuman.put(1, h1);
        idHuman.put(2, h2);
        Set<Integer> ids = new HashSet<>();
        ids.add(1);
        ids.add(6);
        Set<Human> expected = new HashSet<>();
        expected.add(h1);
        Set<Human> actual = ListDemo.getHumansByIds(idHuman, ids);
        assertEquals(expected, actual);
    }

    public void testGetHumansByIdsOneMissing() {
        Map<Integer, Human> idHuman = new HashMap<>();
        Human h1 = new Human("Иванов", "Иван", "Иванович", 25);
        idHuman.put(1, h1);
        Set<Integer> ids = new HashSet<>();
        ids.add(1);
        ids.add(2);
        Set<Human> expected = new HashSet<>();
        expected.add(h1);
        Set<Human> actual = ListDemo.getHumansByIds(idHuman, ids);
        assertEquals(expected, actual);
    }

    //8
    public void testGetIdsOfMatureManyAdults() {
        Map<Integer, Human> idHuman = new HashMap<>();
        Human h1 = new Human("Ильин", "Никита", "Евгеньевич", 25);
        Human h2 = new Human("Смирнов", "Алексей", "Алексеевич", 42);
        Human h3 = new Human("Ильев", "Виктор", "Петрович", 18);
        idHuman.put(1, h1);
        idHuman.put(2, h2);
        idHuman.put(3, h3);
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        List<Integer> actual = ListDemo.getIdsOfMature(idHuman);
        assertEquals(expected, actual);
    }

    public void testGetIdsOfMatureOneKid() {
        Map<Integer, Human> idHuman = new HashMap<>();
        Human h1 = new Human("Ильин", "Никита", "Евгеньевич", 25);
        Human h2 = new Human("Смирнов", "Алексей", "Алексеевич", 16);
        Human h3 = new Human("Ильев", "Виктор", "Петрович", 18);
        idHuman.put(1, h1);
        idHuman.put(2, h2);
        idHuman.put(3, h3);
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(3);
        List<Integer> actual = ListDemo.getIdsOfMature(idHuman);
        assertEquals(expected, actual);
    }


    public void testGetIdsOfMatureEmpty() {
        Map<Integer, Human> idHuman = new HashMap<>();
        List<Integer> expected = new ArrayList<>();
        List<Integer> actual = ListDemo.getIdsOfMature(idHuman);
        assertEquals(expected, actual);
    }

    //9
    public void testGetAgeToHumansMapMultipleElements() {
        Map<Integer,Human> humanMap = new HashMap<>();
        Human h1 = new Human("Ильин", "Никита", "Евгеньевич", 18);
        Human h2 = new Human("Смирнов", "Алексей", "Алексеевич", 20);
        Human h3 = new Human("Ильев", "Виктор", "Петрович", 19);
        Human h4 = new Human("Карлсен", "Никита", "Евгеньевич", 18);
        humanMap.put(1,h1);
        humanMap.put(2,h2);
        humanMap.put(3,h3);
        humanMap.put(4,h4);
        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(1, 18);
        expected.put(2, 20);
        expected.put(3, 19);
        expected.put(4,18);
        Map<Integer, Integer> actual = ListDemo.getAgeToHumansMap(humanMap);
        assertEquals(expected,actual);
    }

    public void testGetAgeToHumansMapEmpty() {
        Map<Integer,Human> humanMap = new HashMap<>();
        Map<Integer, Integer> expected = new HashMap<>();
        Map<Integer, Integer> actual = ListDemo.getAgeToHumansMap(humanMap);
        assertEquals(expected, actual);
    }

    //10
    public void testBuildAgeMap1() {
        Set<Human> humans = new HashSet<>();
        Human h1 = new Human("Иванов", "Иван", "Иванович", 25);
        Human h2 = new Human("Смирнов", "Алексей", "Алексеевич", 25);
        Human h3 = new Human("Петров", "Петр", "Петрович", 30);
        Human h4 = new Human("Сидоров", "Антон", "Антонович", 30);
        Human h5 = new Human("Петросян", "Тигран", "Вартанович", 31);
        humans.add(h1);
        humans.add(h2);
        humans.add(h3);
        humans.add(h4);
        humans.add(h5);
        List<Human> youngHumans = new ArrayList<>();
        youngHumans.add(h1);
        youngHumans.add(h2);
        List<Human> oldHumans = new ArrayList<>();
        oldHumans.add(h3);
        oldHumans.add(h4);
        List<Human> veryOldHumans = new ArrayList<>();
        veryOldHumans.add(h5);
        Map<Integer, List<Human>> ageMap = buildAgeMap(humans);
        Map<Integer, List<Human>> expected = new HashMap<>();
        expected.put(25,youngHumans);
        expected.put(30,oldHumans);
        expected.put(31,veryOldHumans);
      assertEquals(expected, ageMap);
    }
    public void testBuildAgeMap2() {
        Set<Human> humans = new HashSet<>();
        Human h1 = new Human("Иванов", "Иван", "Иванович", 25);
        Human h2 = new Human("Смирнов", "Алексей", "Алексеевич", 25);
        Human h3 = new Human("Петров", "Петр", "Петрович", 30);
        Human h4 = new Human("Сидоров", "Антон", "Антонович", 30);
        Human h5 = new Human("Петросян", "Тигран", "Вартанович", 25);
        humans.add(h1);
        humans.add(h2);
        humans.add(h3);
        humans.add(h4);
        humans.add(h5);
        List<Human> youngHumans = new ArrayList<>();
        youngHumans.add(h1);
        youngHumans.add(h2);
        youngHumans.add(h5);
        List<Human> oldHumans = new ArrayList<>();
        oldHumans.add(h3);
        oldHumans.add(h4);
        Map<Integer, List<Human>> ageMap = buildAgeMap(humans);
        Map<Integer, List<Human>> result = new HashMap<>();
        result.put(25,youngHumans);
        result.put(30,oldHumans);

        assertEquals(result, ageMap);
    }
    public void testBuildAgeMapEmpty() {
        Human h1 = new Human("Иванов", "Иван", "Иванович", 25);
        Set<Human> humans = new HashSet<>();
        humans.add(h1);

        Map<Integer, List<Human>> ageMap = buildAgeMap(humans);
        assertNull(ageMap.get(35));
    }
    //11
    public void testAgeToLetterMap() {
        List<Human> humans = new ArrayList<>();
        Human human1 = new Human("Ильин", "Никита", "Евгеньевич", 25);
        Human human2 = new Human("Смирнов", "Алексей", "Алексеевич", 25);
        Human human11 = new Human("Ильев", "Виктор", "Петрович", 25);
        Human human3 = new Human("Петров", "Юрий", "Петрович", 30);
        Human human4 = new Human("Сидоров", "Сергей", "Сергеевич", 30);
        humans.add(human1);
        humans.add(human2);
        humans.add(human3);
        humans.add(human4);
        humans.add(human11);
        Map<Integer, Map<Character, List<Human>>> ageLetterMap = buildAgeLetterMap(humans);
        List<Human> expected = new LinkedList<Human>();
        expected.add(human11);
        expected.add(human1);
        assertEquals(expected,ageLetterMap.get(25).get('И'));
    }
    public void testAgetoLetterMap2(){
        List<Human> humans = new ArrayList<>();
        Human human1 = new Human("Ильин", "Никита", "Евгеньевич", 25);
        Human human2 = new Human("Смирнов", "Алексей", "Алексеевич", 25);
        Human human11 = new Human("Ильев", "Виктор", "Петрович", 25);
        Human human3 = new Human("Петров", "Юрий", "Петрович", 30);
        Human human4 = new Human("Сидоров", "Сергей", "Сергеевич", 30);
        humans.add(human1);
        humans.add(human2);
        humans.add(human3);
        humans.add(human4);
        humans.add(human11);
        Map<Integer, Map<Character, List<Human>>> ageLetterMap = buildAgeLetterMap(humans);
        List<Human> expected = new LinkedList<Human>();
        expected.add(human3);
        assertEquals(expected,ageLetterMap.get(30).get('П'));
    }


}
