package org.example.validator.primitive;
import java.util.List;

public class NotNullValidator{

    public static boolean notNull(Object input, String fieldName, List<String> collector) {
        if (input == null) {
            collector.add(String.format("Field \"%s\" is null", fieldName));
            return false;
        }
        return true;
    }

}