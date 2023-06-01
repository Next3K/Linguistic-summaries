package org.example.model.quantifiers;

import org.example.model.functions.MembershipShape;
import org.example.model.sets.FuzzySet;
import org.example.model.sets.UniverseOfDiscourse;

public abstract class Quantifier extends FuzzySet {

    public Quantifier(String label, MembershipShape membershipFunction, UniverseOfDiscourse universeOfDiscourse) {
        super(label, null, membershipFunction, universeOfDiscourse, DescriptionType.SIMPLE);

        if (!(isConvex() && isNormal())) {
            throw new IllegalArgumentException("Fuzzy set for quantifier has to be convex and normal, but wasn't");
        }
    }

    public abstract boolean isRelative();
}
