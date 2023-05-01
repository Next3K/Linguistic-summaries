package org.example.model;

public class TrapezoidMembershipFunction implements MembershipFunction {

    public TrapezoidMembershipFunction(double a, double A, double B, double b) {
        this.a = a;
        this.b = b;
        this.A = A;
        this.B = B;
    }

    private final double a;
    private final double b;
    private final double A;
    private final double B;

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
}
