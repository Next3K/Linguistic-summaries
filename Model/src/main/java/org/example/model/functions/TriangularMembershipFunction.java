package org.example.model.functions;

public class TriangularMembershipFunction implements MembershipFunction {

    public TriangularMembershipFunction(double a, double mid, double b) {
        this.a = a;
        this.b = b;
        this.mid = mid;
        this.leftLineCoefA = (1d - 0d) / (this.mid - this.a);
        this.leftLineCoefB = -1 * leftLineCoefA * this.a;
        this.rightLineCoefA = (0d - 1d) / (this.b - this.mid);
        this.rightLineCoefB = -1 * rightLineCoefA * this.b;
    }

    private final double a;
    private final double b;
    private final double mid;

    private final double leftLineCoefA;
    private final double leftLineCoefB;
    private final double rightLineCoefA;
    private final double rightLineCoefB;

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
            return areaUnderLine(leftLineCoefA, leftLineCoefB, min, max);

        }

        // right center
        if (min >= this.mid && max <= this.b) {
            return areaUnderLine(rightLineCoefA, rightLineCoefB, min, max);

        }

        double leftPart = areaUnderLine(leftLineCoefA, leftLineCoefB, min, this.mid);
        double rightPart = areaUnderLine(rightLineCoefA, rightLineCoefB, this.mid, max);
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
