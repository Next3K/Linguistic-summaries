package org.example.model.sets;


public class ContinuousNonFuzzySet extends NonFuzzySet {

    public ContinuousNonFuzzySet(Double min, Double max) {
        super(min, max);
    }

    @Override
    public boolean isEmpty() {
        return this.max.equals(this.min);
    }

    @Override
    public double calculateSize() {
        return this.max.doubleValue() - this.min.doubleValue();
    }

    @Override
    public boolean isValueInTheSet(Number number) {
        return number.doubleValue() <= max.doubleValue() && min.doubleValue() <= number.doubleValue();
    }

    @Override
    public NonFuzzySet getSubset(Number a, Number b) {
        return new ContinuousNonFuzzySet(
                Math.min(a.doubleValue(), min.doubleValue()),
                Math.max(b.doubleValue(), min.doubleValue()));
    }


}
