package org.example.model.quantifiers;

import org.example.model.FuzzySet;
import org.example.model.UniverseOfDiscourse;
import org.example.model.functions.MembershipFunction;

public abstract class Quantifier extends FuzzySet {

    public Quantifier(MembershipFunction membershipFunction, UniverseOfDiscourse universeOfDiscourse) {
        super(membershipFunction, universeOfDiscourse);

        if (!(isConvex() && isNormal())) {
            throw new IllegalArgumentException("Fuzzy set for quantifier has to be convex and normal, but wasn't");
        }
    }

    public abstract String getTextualRepresentation();

    public abstract Double getQuantified(Double d);

    public abstract Boolean isRelative();
}