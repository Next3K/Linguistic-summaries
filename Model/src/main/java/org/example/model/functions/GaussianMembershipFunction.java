package org.example.model.functions;

public class GaussianMembershipFunction implements MembershipFunction {

    private final double mean;
    private final double standardDeviation;
    private final double normalizationConstant;

    public GaussianMembershipFunction(double mean, double standardDeviation) {
        this.mean = mean;
        this.standardDeviation = standardDeviation;
        this.normalizationConstant = 1.0d / gaussianFunction(mean);
    }

    @Override
    public Double evaluate(Double x) {
        return gaussianFunction(x) * normalizationConstant;
    }

    @Override
    public Double getIntegral(double a, double b) {
        return null;
    }

    @Override
    public Double getMaxValue() {
        return 0d;
    }

    private double gaussianFunction(Double x) {
        double exponent = -Math.pow(x - mean, 2) / (2 * Math.pow(standardDeviation, 2));
        double coefficient = 1 / (standardDeviation * Math.sqrt(2 * Math.PI));
        return coefficient * Math.exp(exponent);
    }
}
