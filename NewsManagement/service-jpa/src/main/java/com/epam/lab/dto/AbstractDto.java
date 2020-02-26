package com.epam.lab.dto;

import java.io.Serializable;

public abstract class AbstractDto implements Serializable, Cloneable {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
