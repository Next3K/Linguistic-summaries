package org.example.model.sets;

public class EmptySet extends NonFuzzySet {

    private EmptySet(Number min, Number max) {
        super(min, max);
    }

    public static NonFuzzySet getInstance() {
        return new EmptySet(0d, 0d);
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public double evaluateSize() {
        return 0;
    }

    @Override
    public boolean isValueInTheSet(Number number) {
        return false;
    }

    @Override
    public NonFuzzySet getSubset(Number a, Number b) {
        return this.emptySetInstance();
    }

}
