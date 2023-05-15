package org.example.model.functions;

import org.example.model.MembershipFunction;

public class TriangularMembershipFunction implements MembershipFunction {

    public TriangularMembershipFunction(double a, double b, double mid) {
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
}
