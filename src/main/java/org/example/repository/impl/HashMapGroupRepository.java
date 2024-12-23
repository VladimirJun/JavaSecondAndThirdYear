package org.example.repository.impl;

import org.example.entity.GroupEntity;
import org.example.repository.EntityRepository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class HashMapGroupRepository implements EntityRepository<GroupEntity> {

    private final Map<Long, GroupEntity> storage;
    private final AtomicLong idCounter; // Потокобезопасный счётчик

    public HashMapGroupRepository(Map<Long, GroupEntity> storage) {
        this.storage = storage;
        this.idCounter = new AtomicLong(1); // Начинаем с 1, можно и с 0
    }

    @Override
    public Long create(GroupEntity entity) {
       Long  id = idCounter.getAndIncrement(); // Получаем уникальный идентификатор
        storage.put(id, new GroupEntity(id, entity.name()));
        return id; // Возвращаем id в виде строки
    }

    @Override
    public GroupEntity get(Long id) {
        return storage.get(id);
    }

    @Override
    public void update(GroupEntity entity) {
        if (storage.containsKey(entity.id())) {
            storage.put(entity.id(), entity);
        } else {
            throw new IllegalArgumentException("Entity with ID " + entity.id() + " not found.");
        }
    }

    @Override
    public void delete(Long id) {
        if (storage.remove(id) == null) {
            throw new IllegalArgumentException("Entity with ID " + id + " not found.");
        }
    }

    @Override
    public Collection<GroupEntity> getAll() {
        return storage.values();
    }
}
