package com.vp.amazonreviewsapp.model.dto.mapper;

public interface ItemMapper<E, Q, S> {
    E toEntity(Q dto);

    S toDto(E entity);
}
