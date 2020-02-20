package com.epam.lab.model;

import java.io.Serializable;

public abstract class AbstractEntity implements Serializable, Cloneable {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
