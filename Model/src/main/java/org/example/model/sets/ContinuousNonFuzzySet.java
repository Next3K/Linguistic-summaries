package org.example.model.sets;

public class ContinuousNonFuzzySet extends NonFuzzySet {

    public ContinuousNonFuzzySet(Double minimum, Double maximum) {
        super(new ContinuousUniverseOfDiscourse(minimum, maximum));
    }
}
