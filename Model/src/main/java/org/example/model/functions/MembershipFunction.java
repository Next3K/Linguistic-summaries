package org.example.model.functions;

public interface MembershipFunction {
    public static final double MAXIMUM_VALUE = 1.0;

    Double evaluate(Double x);

    Double getIntegral(double a, double b);

    Double getMaxValue();
}
