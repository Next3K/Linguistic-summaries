package org.example.model.sets;

public class ContinuousUniverseOfDiscourse implements UniverseOfDiscourse {

    private final double min;
    private final double max;

    public ContinuousUniverseOfDiscourse(double min, double max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public double getMinimum() {
        return this.min;
    }

    @Override
    public double getMaximum() {
        return this.max;
    }

    @Override
    public boolean valueInUniverseOfDiscourse(Double value) {
        return value <= this.max && value >= this.min;
    }

    @Override
    public Number getCardinality() {
        return max - min;
    }


}
