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

    public boolean convexityCheck() {
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
        return this.getSupportMeasure() / this.universeOfDiscourse.calculateMeasure().doubleValue();
    }

    public double getSupportMeasure() {
        return membershipFunction.getSupport(this.universeOfDiscourse).measure;
    }

    public double getAlfaCutMeasure(double y) {
        return membershipFunction.getSupport(this.universeOfDiscourse).measure;
    }

    public double getCardinality() {
        if (universeOfDiscourse instanceof DiscreteUniverseOfDiscourse) {
            double sum = 0;
            for (int i = (int) universeOfDiscourse.getMinimum(); i < (int) universeOfDiscourse.getMaximum(); i++) {
                sum += membershipFunction.evaluate((double) i);
            }
            return sum;

        }
        return membershipFunction.getIntegral(universeOfDiscourse.getMinimum(), universeOfDiscourse.getMaximum());
    }
}
