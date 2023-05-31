package org.example.model.sets;

import lombok.Getter;

@Getter
public class UniverseOfDiscourse {

    private final NonFuzzySet nonFuzzySet;
    private static final UniverseOfDiscourse relative = new UniverseOfDiscourse(0d, 1d);
    private static final UniverseOfDiscourse absolute = new UniverseOfDiscourse(0d, 14_854d);

    public UniverseOfDiscourse(Double min, Double max) {
        this.nonFuzzySet = new ContinuousNonFuzzySet(min, max);
        if (nonFuzzySet.isEmpty()) {
            throw new IllegalArgumentException("Universe of discourse cannot be empty!");
        }
    }

    public UniverseOfDiscourse(Integer min, Integer max) {
        this.nonFuzzySet = new DiscreteNonFuzzySet(min, max);
        if (nonFuzzySet.isEmpty()) {
            throw new IllegalArgumentException("Universe of discourse cannot be empty!");
        }
    }

    public static UniverseOfDiscourse relativeQuantifierUniverseInstance() {
        return relative;
    }

    public static UniverseOfDiscourse absoluteQuantifierUniverseInstance() {
        return relative;
    }

}
