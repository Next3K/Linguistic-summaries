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
    public double evaluateSize() {
        return (isEmpty()) ? 0 : max.intValue() - min.intValue() + 1;
    }

    @Override
    public boolean isValueInTheSet(Number number) {
        return !isEmpty() &&
                number.intValue() <= this.max.intValue() &&
                this.min.intValue() <= number.intValue();
    }

    @Override
    public NonFuzzySet getSubset(Number a, Number b) {
        return (isEmpty()) ? emptySetInstance() :  new DiscreteNonFuzzySet(
                Math.max(a.intValue(), min.intValue()),
                Math.min(b.intValue(), max.intValue()));
    }

}
