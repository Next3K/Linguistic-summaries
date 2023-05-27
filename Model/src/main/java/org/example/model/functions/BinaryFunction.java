package org.example.model.functions;

import org.example.model.sets.*;

public class BinaryFunction implements MembershipFunction {

    private final double a;
    private final double b;


    public BinaryFunction(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Double evaluate(Double x) {
        return (x <= b && x >= a) ? 1d : 0d;
    }

    @Override
    public Double getIntegral(double a, double b) {
        return (b - a) * 1;
    }

    @Override
    public Double getMaxValue() {
        return 1.0d;
    }

    @Override
    public NonFuzzySet getSupport(UniverseOfDiscourse universe) {
        double q = Math.max(this.a, universe.getMinimum());
        double r = Math.min(this.b, universe.getMaximum());
        if (universe instanceof DiscreteUniverseOfDiscourse) {
            return new DiscreteNonFuzzySet((int) q, (int) r);
        }
        return new ContinuousNonFuzzySet(q, r);
    }

    @Override
    public NonFuzzySet getAlfaCut(UniverseOfDiscourse universe, double y) {
        if (y != getMaxValue()) {
            return new NonFuzzySet(0d, 0d);
        }
        if (universe instanceof DiscreteUniverseOfDiscourse) {
            return new DiscreteNonFuzzySet((int) universe.getMinimum(), (int) universe.getMaximum());
        }
        return new ContinuousNonFuzzySet(universe.getMinimum(), universe.getMaximum());
    }

}
