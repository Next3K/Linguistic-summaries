package org.example.model.sets;


public class NonFuzzySet {

    protected final Double minimum;
    protected final Double maximum;
    protected final Double size;

    public NonFuzzySet(Double minimum, Double maximum, Double size) {
        if (size <= 0) throw new IllegalStateException("Invalid universe of discourse! Size smaller than 0");
        this.size = size;
        this.minimum = minimum;
        this.maximum = maximum;
    }
}
