package org.example.model;

public class FuzzySet {

    private final MembershipFunction membershipFunction;
    private final UniverseOfDiscourse universeOfDiscourse;

    public FuzzySet(MembershipFunction membershipFunction, UniverseOfDiscourse universeOfDiscourse) {
        this.membershipFunction = membershipFunction;
        this.universeOfDiscourse = universeOfDiscourse;
    }

    public Double calculateMembershipFunctionValue(double x) {
        if (universeOfDiscourse.valueInUniverseOfDiscourse(x)) {
            return membershipFunction.evaluate(x);
        }
        throw new IllegalStateException("Argument: "+ x +
                "out of bounds for given universe of discourse! Min: " +
                universeOfDiscourse.getMinimum() +
                " Max: " + universeOfDiscourse.getMaximum());
    }
}
