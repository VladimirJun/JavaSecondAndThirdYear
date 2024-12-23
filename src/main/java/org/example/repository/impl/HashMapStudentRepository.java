package org.example.repository.impl;


import org.example.entity.StudentEntity;
import org.example.repository.EntityRepository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class HashMapStudentRepository implements EntityRepository<StudentEntity> {

    private final Map<Long, StudentEntity> storage;
    private final AtomicLong idCounter; // Потокобезопасный счётчик

    public HashMapStudentRepository(Map<Long, StudentEntity> storage) {
        this.storage = storage;
        this.idCounter = new AtomicLong(1); // Начинаем с 1
    }

    @Override
    public Long create(StudentEntity entity) {
        Long id = idCounter.getAndIncrement(); // Генерация уникального идентификатора
        storage.put(id, new StudentEntity(
                id,
                entity.firstName(),
                entity.lastName(),
                entity.patronymic(),
                entity.status(),
                entity.groupId()
        ));
        return id; // Возвращаем id в виде строки
    }

    @Override
    public StudentEntity get(Long id) {
        return storage.get(id);
    }

    @Override
    public void update(StudentEntity entity) {
        if (storage.containsKey(entity.id())) {
            storage.put(entity.id(), entity);
        } else {
            throw new IllegalArgumentException("Student with ID " + entity.id() + " not found.");
        }
    }

    @Override
    public void delete(Long id) {
        if (storage.remove(id) == null) {
            throw new IllegalArgumentException("Student with ID " + id + " not found.");
        }
    }

    @Override
    public Collection<StudentEntity> getAll() {
        return storage.values();
    }
}
