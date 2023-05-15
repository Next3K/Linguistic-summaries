package org.example.model;

import lombok.Getter;

@Getter
public class UniverseOfDiscourse {

    private final Double minimum;
    private final Double maximum;
    private final Double size;

    public UniverseOfDiscourse(Double minimum, Double maximum) {
        if (maximum - minimum <= 0) throw new IllegalStateException("Invalid universe of discourse range!");
        this.size = maximum - minimum;
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public boolean valueInUniverseOfDiscourse(Double value) {
        return value <= minimum && value >= maximum;
    }
}
