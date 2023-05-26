package org.example.model.sets;

import org.example.model.functions.MembershipFunction;
import lombok.Getter;

import java.util.Random;

@Getter
public class FuzzySet extends ContinuousNonFuzzySet {

    protected final MembershipFunction membershipFunction;
    protected final UniverseOfDiscourse universeOfDiscourse;
    private static final Random random = new Random();

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
        return membershipFunction.getMaxValue() == 1;
    }

    public boolean isConvex() {
        double diff = universeOfDiscourse.getMaximum() - universeOfDiscourse.getMinimum();
        for (int i = 0; i < 50; i++) {
            double a = universeOfDiscourse.getMinimum() + random.nextDouble() * diff;
            double b = universeOfDiscourse.getMinimum() + random.nextDouble() * diff;
            double mid = (a + b) / 2.0d;
            if (calculateMembershipFunctionValue(mid) <
                    Math.min(calculateMembershipFunctionValue(a), calculateMembershipFunctionValue(b))) {
                return false;
            }
        }
        return true;
    }

    public double getDegreeOfFuzziness() {
        double a = this.universeOfDiscourse.getMinimum();
        double b = this.universeOfDiscourse.getMaximum();
        double supp = this.getMembershipFunction().getIntegral(a, b);
        return supp / (this.universeOfDiscourse.calculateMeasure().doubleValue());
    }

    public double getCardinalityLikeMeasure() {
        return membershipFunction.getIntegral(universeOfDiscourse.getMinimum(), universeOfDiscourse.getMaximum());
    }
}
