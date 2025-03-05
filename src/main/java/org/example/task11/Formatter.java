package org.example.task11;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Formatter {
    private final ThreadLocal<SimpleDateFormat> dateFormat =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public String format(Date date) {
        return dateFormat.get().format(date);
    }
}
