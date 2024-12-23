package org.example.repository.impl;


import org.example.entity.LessonEntity;
import org.example.repository.LessonRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class HashMapLessonRepository implements LessonRepository {

    private final Map<Long, LessonEntity> storage; // Смена ключа на Long
    private final AtomicLong idCounter; // Потокобезопасный счётчик ID

    public HashMapLessonRepository(Map<Long, LessonEntity> storage) {
        this.storage = storage;
        this.idCounter = new AtomicLong(1); // Начинаем с ID = 1
    }

    @Override
    public Long create(LessonEntity entity) {
        Long id = idCounter.getAndIncrement(); // Генерация уникального ID
        storage.put(id, new LessonEntity(
                id,
                entity.subjectId(),
                entity.date(),
                entity.number(),
                entity.teacherId(),
                entity.groupId()
        ));
        return id;
    }

    @Override
    public LessonEntity get(Long id) {
        return storage.get(id); // Получение объекта по ID
    }

    @Override
    public void update(LessonEntity entity) {
        if (!storage.containsKey(entity.id())) {
            throw new IllegalArgumentException("Lesson with ID " + entity.id() + " not found.");
        }
        storage.put(entity.id(), entity); // Обновление объекта
    }

    @Override
    public void delete(Long id) {
        if (storage.remove(id) == null) {
            throw new IllegalArgumentException("Lesson with ID " + id + " not found.");
        }
    }

    @Override
    public Collection<LessonEntity> getLessonsByTutorId(Long id, LocalDate start, LocalDate end) {
        return storage.values().stream()
                .filter(e -> Objects.equals(e.teacherId(), id))
                .filter(e -> !e.date().isBefore(start) && !e.date().isAfter(end)) // Диапазон дат включительно
                .toList();
    }

    @Override
    public Collection<LessonEntity> getLessonsByGroupId(Long id, LocalDate start, LocalDate end) {
        return storage.values().stream()
                .filter(e -> Objects.equals(e.groupId(), id))
                .filter(e -> !e.date().isBefore(start) && !e.date().isAfter(end)) // Диапазон дат включительно
                .toList();
    }

    @Override
    public void deleteLessonsByTutorId(Long id) {
        boolean removed = storage.entrySet().removeIf(e -> Objects.equals(e.getValue().teacherId(), id));
        if (!removed) {
            throw new IllegalArgumentException("No lessons found for teacher with ID " + id);
        }
    }

    @Override
    public void deleteLessonsByGroupId(Long id) {
        boolean removed = storage.entrySet().removeIf(e -> Objects.equals(e.getValue().groupId(), id));
        if (!removed) {
            throw new IllegalArgumentException("No lessons found for group with ID " + id);
        }
    }
}
