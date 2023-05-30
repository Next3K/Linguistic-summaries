package org.example.model.sets;


import lombok.Getter;

@Getter
public abstract class NonFuzzySet {

    protected final Number min;
    protected final Number max;

    public NonFuzzySet(Number min, Number max) {
        this.min = min;
        this.max = max;
    }

    public abstract boolean isEmpty();

    public abstract double calculateSize();

    public abstract boolean isValueInTheSet(Number number);

    public abstract NonFuzzySet getSubset(Number a, Number b);

    public Number getMinimum() {
        return min;
    }

    public Number getMaximum() {
        return max;
    }

}
