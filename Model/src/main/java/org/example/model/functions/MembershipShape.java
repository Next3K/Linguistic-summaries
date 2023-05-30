package org.example.model.functions;


import org.example.model.sets.NonFuzzySet;
import org.example.model.sets.UniverseOfDiscourse;


public interface MembershipShape {

    Double evaluate(Double x);

    Double getIntegral(double a, double b);

    Double getMaxValue();

    NonFuzzySet getSupport(UniverseOfDiscourse universe);

    NonFuzzySet getAlfaCut(UniverseOfDiscourse universe, double y);
}
