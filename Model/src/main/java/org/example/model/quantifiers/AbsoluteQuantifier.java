package org.example.model.quantifiers;

import org.example.model.functions.MembershipShape;
import org.example.model.sets.UniverseOfDiscourse;


public class AbsoluteQuantifier extends Quantifier {

    public AbsoluteQuantifier(String label,
                              MembershipShape membershipFunction,
                              UniverseOfDiscourse universeOfDiscourse) {
        super(label, membershipFunction, universeOfDiscourse);
    }


    @Override
    public boolean isRelative() {
        return false;
    }
}
