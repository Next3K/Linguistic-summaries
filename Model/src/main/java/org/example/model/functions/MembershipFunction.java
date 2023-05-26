package org.example.model.functions;

import org.example.model.sets.NonFuzzySet;
import org.example.model.sets.UniverseOfDiscourse;

public interface MembershipFunction {

    Double evaluate(Double x);

    Double getIntegral(double a, double b);

    Double getMaxValue();

    NonFuzzySet getSupport(UniverseOfDiscourse universe);
}
