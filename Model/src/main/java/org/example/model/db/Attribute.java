package org.example.model.db;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Attribute {
    private final String name;
    private final Double value;

    public Attribute(String name, Double value) {
        this.name = name;
        this.value = value;
    }
}
