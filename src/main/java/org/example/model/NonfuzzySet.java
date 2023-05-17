package org.example.model;

import org.example.model.functions.MembershipFunction;

public class NonfuzzySet {
    private final MembershipFunction membershipFunction;
    private final UniverseOfDiscourse universeOfDiscourse;

    public NonfuzzySet(MembershipFunction membershipFunction, UniverseOfDiscourse universeOfDiscourse) {
        this.membershipFunction = membershipFunction;
        this.universeOfDiscourse = universeOfDiscourse;
    }
}
