package org.example.testUtils;

import org.example.human_student.Human;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class ReflectionDemo{
    public ReflectionDemo() {
    }

    //task1
    public static int countHumans(List<Object> list) {
        int count = 0;
        for (Object obj : list) {
            if (obj instanceof Human) {
                count++;
            }
        }
        return count;
    }

    //task2
    public static List<String> getMethodNames(Object obj) {
        List<String> methodNames = new ArrayList<>();

        Method[] methods = obj.getClass().getMethods();
        for (Method method : methods) {
            methodNames.add(method.getName());

        }

        return methodNames;
    }

    //task3
    public static List<String> getSuperClassNames(Object obj) {
        List<String> superClassNames = new ArrayList<>();
        Class<?> cl = obj.getClass();
        do {
            if (cl != obj.getClass())
                superClassNames.add(cl.getName());
            cl = cl.getSuperclass();
        } while (cl != Object.class);

        superClassNames.add(Object.class.getName());

        return superClassNames;
    }

    //task4
    public static int executeExecutableObjects(List<Object> objects) {
        int count = 0;
        for (Object obj : objects) {
            if (obj instanceof Executable) {
                ((Executable) obj).execute();
                count++;
            }
        }
        return count;
    }


    //task5
    public static List<String> getGettersAndSetters(Object obj) {
        List<String> result = new ArrayList<>();

        Method[] methods = obj.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().startsWith("get") && method.getParameterTypes().length == 0 && !method.getReturnType().equals(void.class)) {
                result.add(method.getName());
            } else if (method.getName().startsWith("set") && method.getParameterTypes().length == 1 && method.getReturnType().equals(void.class)) {
                result.add(method.getName());
            }
        }
        result.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        return result;
    }
}

