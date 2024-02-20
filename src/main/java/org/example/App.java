package org.example;

import org.example.human_student.Human;
import org.example.task_13.Data;
import org.example.task_13.DataIterator;
import org.example.task_13.Group;
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

    }
}
