package org.example;

import org.example.human_student.Human;
import org.example.human_student.ListDemo;
import org.example.task_13.Data;
import org.example.task_13.DataIterator;
import org.example.task_13.Group;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Group g1 = new Group(1,201,202,203);
        Group g2 = new Group(2,301,302,303);
        Group g3 = new Group(3,101,102,103);
        Group[] groups = {g1,g2,g3};
        Data data = new Data("Name",groups);
        DataIterator iterator = new DataIterator(data);
        for (DataIterator it = iterator; it.hasNext(); ) {
            Integer value = it.next();
            System.out.println(value);
        }
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
        System.out.println(ListDemo.buildSortedlist(humans));
    }
}
