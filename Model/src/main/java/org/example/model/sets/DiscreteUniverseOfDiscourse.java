package org.example.model.sets;

import java.util.List;

public class DiscreteUniverseOfDiscourse implements UniverseOfDiscourse {

    Integer min;
    Integer max;

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
        return value <= max && value >= min;
    }

    @Override
    public Number getCardinality() {
        return max - min + 1;
    }
}
