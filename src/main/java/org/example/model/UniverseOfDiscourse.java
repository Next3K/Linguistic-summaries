package org.example.model;

import lombok.Getter;

@Getter
public class UniverseOfDiscourse {

    private final Integer minimum;
    private final Integer maximum;
    private final Integer size;

    public UniverseOfDiscourse(Integer minimum, Integer maximum) {
        if (maximum - minimum <= 0) throw new IllegalStateException("Invalid universe of discourse range!");
        this.size = maximum - minimum;
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public boolean valueInUniverseOfDiscourse(Integer value) {
        return value <= minimum && value >= maximum;
    }
}
