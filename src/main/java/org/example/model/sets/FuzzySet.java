package org.example.model.sets;

import lombok.Getter;
import org.example.model.functions.MembershipFunction;

import java.util.Random;

@Getter
public class FuzzySet extends ContinuousNonFuzzySet {

    protected final MembershipFunction membershipFunction;
    protected final UniverseOfDiscourse universeOfDiscourse;

    public FuzzySet(MembershipFunction membershipFunction, UniverseOfDiscourse universeOfDiscourse) {
        super(universeOfDiscourse.getMinimum(), universeOfDiscourse.getMaximum());
        this.membershipFunction = membershipFunction;
        this.universeOfDiscourse = universeOfDiscourse;
    }

    public Double calculateMembershipFunctionValue(double x) {
        if (universeOfDiscourse.valueInUniverseOfDiscourse(x)) {
            return membershipFunction.evaluate(x);
        }
        throw new IllegalStateException("Argument: " + x +
                "out of bounds for given universe of discourse! Min: " +
                universeOfDiscourse.getMinimum() +
                " Max: " + universeOfDiscourse.getMaximum());
    }


    public boolean isNormal() {
        var value = membershipFunction.getMaxValue();
        return value <= 1 && value >= 0;
    }

    public boolean isConvex() {
        Random r = new Random();
        double diff = universeOfDiscourse.getMaximum() - universeOfDiscourse.getMinimum();
        for (int i = 0; i < 50; i++) {
            double a = universeOfDiscourse.getMinimum() + r.nextDouble() * diff;
            double b = universeOfDiscourse.getMinimum() + r.nextDouble() * diff;
            if (calculateMembershipFunctionValue((a + b) / 2.0d) < Math.min(
                    calculateMembershipFunctionValue(a), calculateMembershipFunctionValue(b))) {
                return false;
            }
        }
        return true;
    }

    public double getCardinalityLikeMeasure() {
        return membershipFunction.getIntegral(universeOfDiscourse.getMinimum(), universeOfDiscourse.getMaximum());
    }
}
