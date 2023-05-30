package org.example.model.sets;

import lombok.Getter;

@Getter
public class UniverseOfDiscourseTwo {

    private final NonFuzzySet nonFuzzySet;

    public UniverseOfDiscourseTwo(Double min, Double max) {
        this.nonFuzzySet = new ContinuousNonFuzzySet(min, max);
    }

    public UniverseOfDiscourseTwo(Integer min,  Integer max) {
        this.nonFuzzySet = new DiscreteNonFuzzySet(min, max);
        if (nonFuzzySet.isEmpty()) {
            throw new IllegalArgumentException("Universe of discourse cannot be empty!");
        }
    }
}
