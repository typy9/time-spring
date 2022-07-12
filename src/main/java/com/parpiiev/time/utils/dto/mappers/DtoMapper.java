package com.parpiiev.time.utils.dto.mappers;


public interface DtoMapper<T, E> {

    T mapToDto(E e);

    E mapFromDto(T t);
}
