package org.example.model.sets;


public class DiscreteUniverseOfDiscourse implements UniverseOfDiscourse {

    public DiscreteUniverseOfDiscourse(Integer min, Integer max) {
        this.min = min;
        this.max = max;
    }

    private final Integer min;
    private final Integer max;


    @Override
    public double getMinimum() {
        return min;
    }

    @Override
    public double getMaximum() {
        return max;
    }

    @Override
    public boolean valueInUniverseOfDiscourse(Double value) {
        int val = value.intValue();
        return val <= max && val >= min;
    }

    @Override
    public Number calculateMeasure() {
        return max - min + 1;
    }
}
