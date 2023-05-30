package org.example.model.sets;

import lombok.Getter;

@Getter
public class UniverseOfDiscourse {

    private final NonFuzzySet nonFuzzySet;

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
}
