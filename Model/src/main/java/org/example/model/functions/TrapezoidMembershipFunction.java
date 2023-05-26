package org.example.model.functions;

import org.example.model.sets.*;

public class TrapezoidMembershipFunction implements MembershipFunction {

    public TrapezoidMembershipFunction(double a, double A, double B, double b) {
        this.a = a;
        this.A = A;
        this.B = B;
        this.b = b;
        this.leftLineCoefficientA = (1d - 0d) / (this.A - this.a);
        this.leftLineCoefficientB = -1 * leftLineCoefficientA * this.a;
        this.rightLineCoefficientA = (0d - 1d) / (this.b - this.B);
        this.rightLineCoefficientB = -1 * rightLineCoefficientA * this.b;
    }

    private final double a;
    private final double A;
    private final double B;
    private final double b;

    private final double leftLineCoefficientA;
    private final double leftLineCoefficientB;
    private final double rightLineCoefficientA;
    private final double rightLineCoefficientB;

    @Override
    public Double evaluate(Double x) {
        if (x <= a || x >= b) return 0.0;
        else if (x >= A && x <= B) return 1.0;
        else {
            if (x <= A) {
                return leftLineCoefficientA * x + leftLineCoefficientB;
            }
            return rightLineCoefficientA * x + rightLineCoefficientB;
        }
    }

    @Override
    public Double getIntegral(double min, double max) {
        double fullTrapeze = 0.5 * 1 * (Math.abs(A - B) + Math.abs(a - b));

        min = Math.max(min, this.a);
        max = Math.min(max, this.b);

        if (min <= a && max >= b) {
            return fullTrapeze;
        }

        double leftCutOff = cutOffTrapezeFromLeft(min - a);
        double rightCutOff = fullTrapeze - cutOffTrapezeFromLeft(max - a);

        return fullTrapeze - leftCutOff - rightCutOff;
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


    private double cutOffTrapezeFromLeft(double offset) {
        double firstTriangleWidth = this.A - this.a;
        double rectangleWidth = this.B - this.A;

        if (offset <= firstTriangleWidth) {
            return TriangularMembershipFunction
                    .areaUnderLine(leftLineCoefficientA, leftLineCoefficientB, this.a, this.a + offset);
        } else if (offset <= firstTriangleWidth + rectangleWidth) {
            return 0.5 * firstTriangleWidth * 1 + (offset - firstTriangleWidth) * 1;
        } else {
            return  0.5 * firstTriangleWidth * 1 + rectangleWidth * 1 + TriangularMembershipFunction
                    .areaUnderLine(rightLineCoefficientA, rightLineCoefficientB, this.B, this.a + offset);
        }
    }

}
