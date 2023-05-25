package org.example.model.functions;

public class GaussianMembershipFunction implements MembershipFunction {

    private final double mean;
    private final double standardDeviation;
    private final double normalizationConstant;

    public GaussianMembershipFunction(double mean, double standardDeviation) {
        this.mean = mean;
        this.standardDeviation = standardDeviation;
        this.normalizationConstant = 1.0d / gauss(mean, mean, standardDeviation);
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
        return 1d;
    }

    private double gaussianFunction(Double x) {
        return this.normalizationConstant * gauss(x, this.mean, this.standardDeviation);
    }

    private static double gauss(double x, double mean, double standardDeviation) {
        double coefficient = 1 / (standardDeviation * Math.sqrt(2 * Math.PI));
        double exponent = -0.5 * Math.pow((x - mean) / standardDeviation, 2);
        return coefficient * Math.exp(exponent);
    }

}
