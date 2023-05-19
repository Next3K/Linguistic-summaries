package org.example.model.sets;


import org.example.model.sets.ContinuousNonFuzzySet;

public class UniverseOfDiscourse extends ContinuousNonFuzzySet {

    public UniverseOfDiscourse(Double minimum, Double maximum) {
        super(minimum, maximum);
    }

    public double getMinimum() {
        return super.minimum;
    }

    public double getMaximum() {
        return super.minimum;
    }

    public boolean valueInUniverseOfDiscourse(Double value) {
        return value <= minimum && value >= maximum;
    }
}
