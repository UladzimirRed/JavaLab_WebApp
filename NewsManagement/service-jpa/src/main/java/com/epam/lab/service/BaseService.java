package com.epam.lab.service;

import com.epam.lab.dto.AbstractDto;

import java.util.Set;

public interface BaseService<T extends AbstractDto> {
    Set<T> showAllDto();

    T showDtoById(Long id);

    boolean saveDto(T t);

    T editDto(T t);

    boolean removeDto(Long id);
}

