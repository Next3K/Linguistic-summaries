package org.example.model;

public class FuzzySet {

    private final MembershipFunction membershipFunction;
    private final UniverseOfDiscourse universeOfDiscourse;

    public FuzzySet(MembershipFunction membershipFunction, UniverseOfDiscourse universeOfDiscourse) {
        this.membershipFunction = membershipFunction;
        this.universeOfDiscourse = universeOfDiscourse;
    }

    public Double calculateMembershipFunctionValue(double x) {
        if (x < universeOfDiscourse.getMinimum() || x > universeOfDiscourse.getMaximum()) {
            throw new IllegalStateException("Argument out of bounds for given universe of discourse!");
        }
        return membershipFunction.evaluate(x);
    }
}
