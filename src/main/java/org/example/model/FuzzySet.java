package org.example.model;

public class FuzzySet {

    private final MembershipFunction membershipFunction;

    public FuzzySet(MembershipFunction membershipFunction) {
        this.membershipFunction = membershipFunction;
    }

    public Double calculateMembershipFunctionValue(double d) {
     return 0.0;
    }
}
