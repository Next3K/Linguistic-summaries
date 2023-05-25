package org.example.model.functions;

public class TrapezoidMembershipFunction implements MembershipFunction {

    public TrapezoidMembershipFunction(double a, double A, double B, double b) {
        this.a = a;
        this.b = b;
        this.A = A;
        this.B = B;
        this.leftLineCoefficientA = (1d - 0d) / (this.A - this.a);
        this.leftLineCoefficientB = -1 * leftLineCoefficientA * this.a;
        this.rightLineCoefficientA = (0d - 1d) / (this.b - this.B);
        this.rightLineCoefficientB = -1 * rightLineCoefficientA * this.b;
    }

    private final double a;
    private final double b;
    private final double A;
    private final double B;

    private final double leftLineCoefficientA;
    private final double leftLineCoefficientB;
    private final double rightLineCoefficientA;
    private final double rightLineCoefficientB;

    @Override
    public Double evaluate(Double x) {
        if (x <= a || x >= b) return 0.0;
        else if (x >= A && x <= B) return 1.0;
        else {
            // x < A
            if (x < A) {
                return x * 1 / (A - a) - (a / (A - a));
            }
            // x > B
            return x * 1 / (b - B) - (B / (b - B));
        }
    }

    @Override
    public Double getIntegral(double min, double max) {
        double fullTrapeze = 0.5 * 1 * (Math.abs(A - B) + Math.abs(a - b));

        if (min <= a && max >= b) {
            return fullTrapeze;
        }

        if (min >= a && min <= b) {
            return cutOffTrapeze(a - min);
        }

        if (max >= a && max <= b) {
            return cutOffTrapeze(b - min);
        }

        return fullTrapeze;
    }

    @Override
    public Double getMaxValue() {
        return 1.0d;
    }

    private double cutOffTrapeze(double offset) {
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
