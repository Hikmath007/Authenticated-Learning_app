package com.hikmath.LearningPortal.mapper;

import org.mapstruct.Mapper;

import java.util.List;

public interface EntityMapper<D, E> {

    D toDto (E entity);
    E toEntity (D dto);

    List<D> toDtoList(List<E> entityList);
    List<E> toEntityList (List<D> dtoList);

}
