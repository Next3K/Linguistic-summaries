package org.example.model.sets;


public class DiscreteNonFuzzySet extends NonFuzzySet {

    private final int min;
    private final int max;

    public DiscreteNonFuzzySet(int a, int b) {
        super((double) a, (double) b);
        this.min = a;
        this.max = b;
    }
}
