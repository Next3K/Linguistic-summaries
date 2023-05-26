package org.example.model.functions;

import org.example.model.sets.*;

public class TriangularMembershipFunction implements MembershipFunction {

    public TriangularMembershipFunction(double a, double mid, double b) {
        this.a = a;
        this.mid = mid;
        this.b = b;
        this.leftLineCoefficientA = (1d - 0d) / (this.mid - this.a);
        this.leftLineCoefficientB = (-leftLineCoefficientA) * this.a;
        this.rightLineCoefficientA = (0d - 1d) / (this.b - this.mid);
        this.rightLineCoefficientB = (-rightLineCoefficientA) * this.b;
    }

    private final double a;
    private final double mid;
    private final double b;

    private final double leftLineCoefficientA;
    private final double leftLineCoefficientB;
    private final double rightLineCoefficientA;
    private final double rightLineCoefficientB;

    @Override
    public Double evaluate(Double x) {
        if (x < a || x > b) return 0.0;
        if (x < mid) {
            return leftLineCoefficientA * x + leftLineCoefficientB;
        }
        return rightLineCoefficientA * x + rightLineCoefficientB;
    }

    @Override
    public Double getIntegral(double min, double max) {
        if (max <= this.a) return 0.0d; // to the left
        if (min >= this.b) return 0.0d; // to the right

        min = Math.max(min, this.a);
        max = Math.min(max, this.b);

        // left center
        if (min >= this.a && max <= this.mid) {
            return areaUnderLine(leftLineCoefficientA, leftLineCoefficientB, min, max);
        }

        // right center
        if (min >= this.mid && max <= this.b) {
            return areaUnderLine(rightLineCoefficientA, rightLineCoefficientB, min, max);

        }

        double leftPart = areaUnderLine(leftLineCoefficientA, leftLineCoefficientB, min, this.mid);
        double rightPart = areaUnderLine(rightLineCoefficientA, rightLineCoefficientB, this.mid, max);
        return leftPart + rightPart;
    }

    @Override
    public Double getMaxValue() {
        return 1.0d;
    }

    @Override
    public NonFuzzySet getSupport(UniverseOfDiscourse universe) {
        double q = Math.max(this.a, universe.getMinimum());
        double r = Math.min(this.b, universe.getMaximum());
        if (universe instanceof DiscreteUniverseOfDiscourse) {
            return new DiscreteNonFuzzySet((int) q, (int) r);
        }
        return new ContinuousNonFuzzySet(q, r);
    }

    public static double areaUnderLine(double a, double b, double X1, double X2) {
        return 0.5d * a * X2 * X2 + X2 * b - 0.5d * a * X1 * X1 - X1 * b;
    }
}
