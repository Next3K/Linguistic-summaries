package org.example.model;

public class NonfuzzySet {
    private final MembershipFunction membershipFunction;
    private final UniverseOfDiscourse universeOfDiscourse;

    public NonfuzzySet(MembershipFunction membershipFunction, UniverseOfDiscourse universeOfDiscourse) {
        this.membershipFunction = membershipFunction;
        this.universeOfDiscourse = universeOfDiscourse;
    }
}
