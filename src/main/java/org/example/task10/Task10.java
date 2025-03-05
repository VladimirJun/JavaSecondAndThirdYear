package org.example.task10;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.HashMap;
import java.util.Map;

public class Task10<K, V> {
    private final Map<K, V> map = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(K key, V value) {
        lock.writeLock().lock(); // Блокируем все операции
        try {
            map.put(key, value);
        } finally {
            lock.writeLock().unlock(); // Разблокируем
        }
    }

    public V get(K key) {
        lock.readLock().lock(); // Разрешаем множественное чтение
        try {
            return map.get(key);
        } finally {
            lock.readLock().unlock();
        }
    }

    public V remove(K key) {
        lock.writeLock().lock();
        try {
            return map.remove(key);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public boolean containsKey(K key) {
        lock.readLock().lock();
        try {
            return map.containsKey(key);
        } finally {
            lock.readLock().unlock();
        }
    }

    public int size() {
        lock.readLock().lock();
        try {
            return map.size();
        } finally {
            lock.readLock().unlock();
        }
    }
    public void main(){

    }
}
