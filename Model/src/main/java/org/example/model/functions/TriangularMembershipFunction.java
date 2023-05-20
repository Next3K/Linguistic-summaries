package org.example.model.functions;

public class TriangularMembershipFunction implements MembershipFunction {

    public TriangularMembershipFunction(double a, double mid, double b) {
        this.a = a;
        this.b = b;
        this.mid = mid;
    }

    private final double a;
    private final double b;
    private final double mid;

    @Override
    public Double evaluate(Double x) {
        if (x < a || x > b) return 0.0;
        if (x < mid) {
            return x * 1 / (mid - a) - (a / (mid - a));
        }
        return x * 1 / (b - mid) - (mid / (b - mid));
    }

    @Override
    public Double getIntegral(double a, double b) {
        if (b < this.a) return 0.0d;
        if (a > this.b) return 0.0d;
        
        //left part
        double leftPart = 0;
        // right part
        double rightPart = 0;

        return leftPart + rightPart;
    }

    @Override
    public Double getMaxValue() {
        return 1.0d;
    }
}
