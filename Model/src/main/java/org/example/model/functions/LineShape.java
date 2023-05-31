package org.example.model.functions;

import org.example.model.sets.NonFuzzySet;
import org.example.model.sets.UniverseOfDiscourse;

public class LineShape implements MembershipShape {
    @Override
    public Double evaluate(Double x) {
        return 1d;
    }

    @Override
    public Double getIntegral(double a, double b) {
        return a * b;
    }

    @Override
    public Double getMaxValue() {
        return 1d;
    }

    @Override
    public NonFuzzySet getSupport(UniverseOfDiscourse universe) {
        return universe.getNonFuzzySet();
    }

    @Override
    public NonFuzzySet getAlfaCut(UniverseOfDiscourse universe, double y) {
        return (y == 1d) ? getSupport(universe) : universe.getNonFuzzySet().emptySetInstance();
    }
}
