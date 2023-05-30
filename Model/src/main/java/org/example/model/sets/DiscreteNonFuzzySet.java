package org.example.model.sets;



public class DiscreteNonFuzzySet extends NonFuzzySet {

    public DiscreteNonFuzzySet(Integer min, Integer max) {
        super(min, max);
    }

    @Override
    public boolean isEmpty() {
        return max.intValue() <= min.intValue();
    }

    @Override
    public double calculateSize() {
        return max.intValue() - min.intValue() + 1;
    }

    @Override
    public boolean isValueInTheSet(Number number) {
        return number.intValue() <= this.max.intValue() && this.min.intValue() <= number.intValue();
    }
}
