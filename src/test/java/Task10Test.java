import org.example.task10.Task10;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task10Test {

    @Test
    void testPutAndGet() {
        Task10<String, Integer> map = new Task10<>();
        map.put("one", 1);
        map.put("two", 2);

        assertEquals(Integer.valueOf(1), map.get("one"));
        assertEquals(Integer.valueOf(2), map.get("two"));
    }

    @Test
    void testRemove() {
        Task10<String, Integer> map = new Task10<>();
        map.put("key", 42);
        assertEquals(Integer.valueOf(42), map.remove("key"));
        assertNull(map.get("key"));
    }

    @Test
    void testContainsKey() {
        Task10<String, Integer> map = new Task10<>();
        map.put("exists", 100);
        assertTrue(map.containsKey("exists"));
        assertFalse(map.containsKey("missing"));
    }

    @Test
    void testSize() {
        Task10<String, Integer> map = new Task10<>();
        assertEquals(0, map.size());

        map.put("A", 10);
        map.put("B", 20);
        assertEquals(2, map.size());

        map.remove("A");
        assertEquals(1, map.size());
    }

    @Test
    void testConcurrentAccess() throws InterruptedException {
        Task10<Integer, Integer> map = new Task10<>();
        int threadCount = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            int finalI = i;
            executor.execute(() -> {
                map.put(finalI, finalI * 10);
                latch.countDown();
            });
        }

        latch.await();
        executor.shutdown();

        assertEquals(threadCount, map.size());

        for (int i = 0; i < threadCount; i++) {
            assertEquals(Integer.valueOf(i * 10), map.get(i));
        }
    }

    @Test
    void testConcurrentReadAndWrite() throws InterruptedException {
        Task10<Integer, Integer> map = new Task10<>();
        int threadCount = 5;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount * 2);
        CountDownLatch latch = new CountDownLatch(threadCount * 2);

        // Запускаем потоки записи
        for (int i = 0; i < threadCount; i++) {
            int finalI = i;
            executor.execute(() -> {
                map.put(finalI, finalI);
                latch.countDown();
            });
        }

        // Запускаем потоки чтения
        for (int i = 0; i < threadCount; i++) {
            int finalI = i;
            executor.execute(() -> {
                map.get(finalI);
                latch.countDown();
            });
        }

        latch.await();
        executor.shutdown();

        assertEquals(threadCount, map.size());
    }
}
