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


}
