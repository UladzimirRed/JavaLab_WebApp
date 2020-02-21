package com.epam.lab.jpaservice;

import com.epam.lab.dto.AbstractDto;
import com.epam.lab.model.AbstractEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public interface BaseService <T extends AbstractDto> {
    List<T> showAllDto();

    T showDtoById(Long id);

    boolean saveDto(T t);

    T editDto(T t);

    boolean removeDto(Long id);
}

