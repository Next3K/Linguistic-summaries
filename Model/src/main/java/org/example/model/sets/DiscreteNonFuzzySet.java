package org.example.model.sets;


public class DiscreteNonFuzzySet extends NonFuzzySet {

    private final int min;
    private final int max;

    public DiscreteNonFuzzySet(int a, int b) {
        super(new DiscreteUniverseOfDiscourse(a, b));
        this.min = a;
        this.max = b;
    }
}
