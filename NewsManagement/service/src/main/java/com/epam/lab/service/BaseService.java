package com.epam.lab.service;

import com.epam.lab.dto.AbstractDto;
import com.epam.lab.exception.ServiceException;

import java.util.List;

public interface BaseService<T extends AbstractDto> {
    List<T> showAllDto();

    T showDtoById(Long id);

    boolean saveDto(T t);

    T editDto(T t);

    boolean removeDto(Long id);
}
