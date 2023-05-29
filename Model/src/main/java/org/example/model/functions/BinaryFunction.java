package org.example.model.functions;

import org.example.model.sets.*;

public class BinaryFunction extends MembershipFunction {

    private final double a;
    private final double b;


    public BinaryFunction(double a, double b) {
        super(new ContinuousUniverseOfDiscourse(a, b));
        this.a = a;
        this.b = b;
    }

    @Override
    public Double evaluate(Double x) {
        return (x <= b && x >= a) ? 1d : 0d;
    }

    @Override
    public Double getIntegral() {
        return (b - a) * 1;
    }

    @Override
    public Double getMaxValue() {
        return 1.0d;
    }

    @Override
    public NonFuzzySet getSupport() {
        double q = Math.max(this.a, this.universeOfDiscourse.getMinimum());
        double r = Math.min(this.b, this.universeOfDiscourse.getMaximum());
//        if (universe instanceof DiscreteUniverseOfDiscourse) {
//            return new DiscreteNonFuzzySet((int) q, (int) r);
//        }
        return new ContinuousNonFuzzySet(q, r);
    }

    @Override
    public NonFuzzySet getAlfaCut(double y) {
        if (y != getMaxValue()) {
            return new NonFuzzySet(new BinaryFunction(0, 1), new ContinuousUniverseOfDiscourse(0, 0));
        }
//        if (universe instanceof DiscreteUniverseOfDiscourse) {
//            return new DiscreteNonFuzzySet((int) universe.getMinimum(), (int) universe.getMaximum());
//        }
        return new ContinuousNonFuzzySet(
                this.universeOfDiscourse.getMinimum(),
                this.universeOfDiscourse.getMaximum());
    }

}
