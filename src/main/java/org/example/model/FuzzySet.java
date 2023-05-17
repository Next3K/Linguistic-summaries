package org.example.model;

import lombok.Getter;
import org.example.model.functions.MembershipFunction;

@Getter
public class FuzzySet extends ClassicalSet {

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


    public boolean isNormal() {
        var value = membershipFunction.getMaxValue();
        return value <= 1 && value >= 0;
    }

    public boolean isConvex() {
        return true;
    }

    public double getCardinalityLikeMeasure() {
        return membershipFunction.getIntegral(universeOfDiscourse.getMinimum(), universeOfDiscourse.getMaximum());
    }
}
