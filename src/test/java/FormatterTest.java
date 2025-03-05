import org.example.task11.Formatter;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FormatterTest {
    public static void main(String[] args) {
        Formatter formatter = new Formatter();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            executor.execute(new DateFormatterTask(formatter));
        }

        executor.shutdown();
    }
}

class DateFormatterTask implements Runnable {
    private final Formatter formatter;

    public DateFormatterTask(Formatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void run() {
        Date now = new Date();
        String formattedDate = formatter.format(now);
        System.out.println(Thread.currentThread().getName() + " -> " + formattedDate);
    }
}