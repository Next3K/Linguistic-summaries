package org.example.model.functions;

public class TriangularMembershipFunction implements MembershipFunction {

    public TriangularMembershipFunction(double a, double mid, double b) {
        this.a = a;
        this.b = b;
        this.mid = mid;
        this.leftLineCoefficientA = (1d - 0d) / (this.mid - this.a);
        this.leftLineCoefficientB = -1 * leftLineCoefficientA * this.a;
        this.rightLineCoefficientA = (0d - 1d) / (this.b - this.mid);
        this.rightLineCoefficientB = -1 * rightLineCoefficientA * this.b;
    }

    private final double a;
    private final double b;
    private final double mid;

    private final double leftLineCoefficientA;
    private final double leftLineCoefficientB;
    private final double rightLineCoefficientA;
    private final double rightLineCoefficientB;

    @Override
    public Double evaluate(Double x) {
        if (x < a || x > b) return 0.0;
        if (x < mid) {
            return x * 1 / (mid - a) - (a / (mid - a));
        }
        return x * 1 / (b - mid) - (mid / (b - mid));
    }

    @Override
    public Double getIntegral(double min, double max) {
        if (max <= this.a) return 0.0d; // to the left
        if (min >= this.b) return 0.0d; // to the right
        if (min >= this.a && max < this.b) return 0d; // in the center

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

    public static double areaUnderLine(double a, double b, double X1, double X2) {
        return 0.5d * a * X2 * X2 + X2 * b - 0.5d * a * X1 * X1 - X1 * b;
    }
}
