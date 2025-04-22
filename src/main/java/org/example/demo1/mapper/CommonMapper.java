package org.example.demo1.mapper;

import java.util.List;

public interface CommonMapper<E, D> {
    E mapToEntity(D d);

    D mapToDto(E e);

    List<E> mapToEntities(List<D> dtos);

    List<D> mapToDtos(List<E> entities);
}