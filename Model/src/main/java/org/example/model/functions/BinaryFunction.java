package org.example.model.functions;

public class BinaryFunction implements MembershipFunction{

    private final double a;
    private final double b;


    public BinaryFunction(double a, double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Double evaluate(Double x) {
        return (x <= b && x >= a) ? 1d : 0d;
    }

    @Override
    public Double getIntegral(double a, double b) {
        return (b - a) * 1;
    }

    @Override
    public Double getMaxValue() {
        return 1.0d;
    }
}
