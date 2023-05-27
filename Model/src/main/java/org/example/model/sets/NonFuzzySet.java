package org.example.model.sets;


import lombok.Getter;

@Getter
public class NonFuzzySet {

    protected final Double minimum;
    protected final Double maximum;
    protected final Double measure;

    public NonFuzzySet(Double minimum, Double maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
        this.measure = maximum - minimum;
        if (measure < 0) throw new IllegalStateException("Invalid universe of discourse! Size smaller than 0");
    }

    public boolean isEmpty() {
        return measure == 0;
    }
}
