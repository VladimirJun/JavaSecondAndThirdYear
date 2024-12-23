package org.example.repository.impl;


import org.example.entity.TeacherEntity;
import org.example.repository.EntityRepository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class HashMapTeacherRepository implements EntityRepository<TeacherEntity> {

    private final Map<Long, TeacherEntity> storage;
    private final AtomicLong idCounter; // Потокобезопасный счётчик ID

    public HashMapTeacherRepository(Map<Long, TeacherEntity> storage) {
        this.storage = storage;
        this.idCounter = new AtomicLong(1); // Начальное значение ID
    }

    @Override
    public Long create(TeacherEntity entity) {
        long id = idCounter.getAndIncrement(); // Генерация уникального ID
        storage.put(id, new TeacherEntity(id, entity.firstName(), entity.lastName(), entity.patronymic()));
        return id; // Возвращаем ID как строку
    }

    @Override
    public TeacherEntity get(Long id) {
        return storage.get(id); // Получение объекта по ID
    }

    @Override
    public void update(TeacherEntity entity) {
        if (!storage.containsKey(entity.id())) {
            throw new IllegalArgumentException("Teacher with ID " + entity.id() + " not found.");
        }
        storage.put(entity.id(), entity); // Обновление объекта
    }

    @Override
    public void delete(Long id) {
        if (storage.remove(id) == null) {
            throw new IllegalArgumentException("Teacher with ID " + id + " not found.");
        }
    }

    @Override
    public Collection<TeacherEntity> getAll() {
        return storage.values(); // Возвращаем все элементы
    }
}
