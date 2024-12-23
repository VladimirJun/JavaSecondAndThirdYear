package org.example.repository.impl;


import org.example.entity.AttendanceEntity;
import org.example.repository.AttendanceRepository;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class HashMapAttendanceRepository implements AttendanceRepository {

    private final Map<Long, AttendanceEntity> storage;
    private final AtomicLong idCounter; // Счётчик для генерации уникальных ID

    public HashMapAttendanceRepository(Map<Long, AttendanceEntity> storage) {
        this.storage = storage;
        this.idCounter = new AtomicLong(1); // Начинаем отсчёт ID с 1
    }

    @Override
    public Long create(AttendanceEntity entity) {
        Long id = idCounter.getAndIncrement(); // Генерация уникального идентификатора
        storage.put(id, new AttendanceEntity(
                id,
                entity.lessonId(),
                entity.studentsId()
        ));
        return id; // Возвращаем ID в виде строки
    }

    @Override
    public AttendanceEntity get(Long lessonId) {
        // Поиск по lessonId
        return storage.values()
                .stream()
                .filter(report -> report.lessonId()==(lessonId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("LessonReport with lessonId " + lessonId + " not found."));
    }

    @Override
    public void update(AttendanceEntity entity) {
        // Проверяем, существует ли объект с указанным ID
        if (!storage.containsKey(entity.id())) {
            throw new IllegalArgumentException("LessonReport with ID " + entity.id() + " not found.");
        }
        storage.put(entity.id(), entity);
    }

    @Override
    public void delete(Long lessonId) {
        // Удаление по lessonId
        boolean removed = storage.entrySet().removeIf(entry -> entry.getValue().lessonId()==(lessonId));
        if (!removed) {
            throw new IllegalArgumentException("LessonReport with lessonId " + lessonId + " not found.");
        }
    }
}
