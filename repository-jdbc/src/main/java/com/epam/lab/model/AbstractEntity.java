package com.epam.lab.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

public abstract class AbstractEntity implements Serializable, Cloneable {
    private Long id;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
