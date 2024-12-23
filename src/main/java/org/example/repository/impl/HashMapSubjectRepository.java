package org.example.repository.impl;


import org.example.entity.SubjectEntity;
import org.example.repository.EntityRepository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class HashMapSubjectRepository implements EntityRepository<SubjectEntity> {

    private final Map<Long, SubjectEntity> storage;
    private final AtomicLong idCounter; // Потокобезопасный счётчик для генерации ID

    public HashMapSubjectRepository(Map<Long, SubjectEntity> storage) {
        this.storage = storage;
        this.idCounter = new AtomicLong(1); // Начинаем с ID 1
    }

    @Override
    public Long create(SubjectEntity entity) {
        Long id = idCounter.getAndIncrement(); // Генерация уникального ID
        storage.put(id, new SubjectEntity(id, entity.name())); // Сохранение объекта
        return id; // Возвращаем ID как строку
    }

    @Override
    public SubjectEntity get(Long id) {
        return storage.get(id); // Получение объекта по ID
    }

    @Override
    public void update(SubjectEntity entity) {
        // Проверяем, существует ли объект перед обновлением
        if (!storage.containsKey(entity.id())) {
            throw new IllegalArgumentException("Subject with ID " + entity.id() + " not found.");
        }
        storage.put(entity.id(), entity);
    }

    @Override
    public void delete(Long id) {
        // Удаление объекта по ID
        if (storage.remove(id) == null) {
            throw new IllegalArgumentException("Subject with ID " + id + " not found.");
        }
    }

    @Override
    public Collection<SubjectEntity> getAll() {
        return storage.values(); // Получение всех объектов
    }
}
