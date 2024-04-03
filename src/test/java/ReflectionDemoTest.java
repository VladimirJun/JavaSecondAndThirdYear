import junit.framework.TestCase;
import org.example.testUtils.ReflectionDemo;
import org.example.testUtils.TestClass;
import org.example.testUtils.TestExe1;
import org.example.TestExe2;
import org.example.human_student.Human;
import org.example.human_student.Student;
import java.util.ArrayList;
import java.util.List;

public class ReflectionDemoTest extends TestCase {
//task1
    public void testCountHumans() {
        List<Object> list = new ArrayList<>();
        list.add(new Human("Petrov", "Vladimir", "Papa", 19));
        list.add(new Human("Петров", "Владимир", "Юрьевич", 27));
        list.add("abcde");
        list.add( new ReflectionDemo());
        list.add(new Student("Бруклин","Вахо","Вахович",17,"ФЦТК"));
        int result = ReflectionDemo.countHumans(list);
        assertEquals(3, result);
    }

    public void testCountHumansEmpty() {
        List<Object> list = new ArrayList<>();
        int result = ReflectionDemo.countHumans(list);
        assertEquals(0, result);
    }
    public void testCountHumans2() {
        List<Object> list = new ArrayList<>();
        list.add("abcde");
        int result = ReflectionDemo.countHumans(list);
        assertEquals(0, result);
    }


//task2
    public void testGetMethodNames() {
        TestClass testObg = new TestClass();
        List<String> result = ReflectionDemo.getMethodNames(testObg);
        List<String> expected = new ArrayList<>();
        expected.add("testMethod2");
        expected.add("testMethod3");
//        assertEquals(expected,result);
        assertTrue(result.contains("testMethod2")&&result.contains("testMethod3"));
        assertFalse(result.contains("testMethod1"));
    }

    public void testGetSuperClassNames() {
        Student s1 = new Student("Бруклин","Вахо","Вахович",17,"ФЦТК");
        List<String> expected = new ArrayList<String>();
        expected.add("org.example.human_student.Human");
        expected.add(("java.lang.Object"));
        List<String> result = ReflectionDemo.getSuperClassNames(s1);
        assertEquals(expected,result);
    }

    public void testExecuteExecutableObjects() {
        List<Object> objects= new ArrayList<>();
        objects.add(new ReflectionDemo());
        objects.add(new TestClass());
        objects.add(new TestExe1());
        objects.add(new TestExe2());
        assertEquals(2,ReflectionDemo.executeExecutableObjects(objects));
    }
    //task5
    public void testGetGettersAndSetters() {
        Human h1 = new Human("Петров", "Владимир", "Юрьевич",19);
        List<String> expected = new ArrayList<>();
        expected.add("getAge");
        expected.add("getName");
        expected.add("getPatronymic");
        expected.add("getSurname");
        expected.add("setAge");
        expected.add("setName");
        expected.add("setPatronymic");
        expected.add("setSurname");
        assertEquals(expected, ReflectionDemo.getGettersAndSetters(h1));
    }
    public void testGetGettersAndSettersEmpty() {
        ReflectionDemo reflectionDemo = new ReflectionDemo();
        List<String> expected = new ArrayList<>();
        assertEquals(expected, ReflectionDemo.getGettersAndSetters(reflectionDemo));
    }
}