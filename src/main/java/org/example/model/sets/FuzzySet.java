package org.example.model.sets;

import lombok.Getter;
import org.example.model.UniverseOfDiscourse;
import org.example.model.functions.MembershipFunction;

@Getter
public class FuzzySet extends NonFuzzySet {

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
        double globalMaximum = 0;
        boolean foundPick = false;
        double delta = (universeOfDiscourse.getMaximum() - universeOfDiscourse.getMinimum()) / 100;
        for (double i = universeOfDiscourse.getMinimum(); i < universeOfDiscourse.getMaximum(); i += delta) {
            double currentValue = calculateMembershipFunctionValue(i);
            if (currentValue > globalMaximum) globalMaximum = currentValue;
            else {
                if (foundPick) {
                    return false;
                } else {
                    foundPick = true;
                }
            }
        }
        return true;
    }

    public double getCardinalityLikeMeasure() {
        return membershipFunction.getIntegral(universeOfDiscourse.getMinimum(), universeOfDiscourse.getMaximum());
    }
}
