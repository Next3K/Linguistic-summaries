package org.example.model.functions;

import org.example.model.sets.*;

public class GaussianMembershipFunction implements MembershipFunction {

    private final double mean;
    private final double stDev;
    private final double normalizationConstant;

    public GaussianMembershipFunction(double mean, double standardDeviation) {
        this.mean = mean;
        this.stDev = standardDeviation;
        this.normalizationConstant = this.stDev * Math.sqrt(2 * Math.PI);
    }

    @Override
    public Double evaluate(Double x) {
        return gauss(x, this.mean, this.stDev) * this.normalizationConstant;
    }

    @Override
    public Double getIntegral(double a, double b) {
        double area = 0.5 * (
                errorFunction((b - this.mean) / (Math.sqrt(2) * this.stDev)) -
                        errorFunction((a - this.mean) / (Math.sqrt(2) * this.stDev)));
        return area * normalizationConstant;
    }

    @Override
    public Double getMaxValue() {
        return 1d;
    }

    @Override
    public NonFuzzySet getSupport(UniverseOfDiscourse universe) {
        if (universe instanceof DiscreteUniverseOfDiscourse) {
            return new DiscreteNonFuzzySet((int) universe.getMinimum(), (int) universe.getMaximum());
        }
        return new ContinuousNonFuzzySet(universe.getMinimum(), universe.getMaximum());
    }


    private static double gauss(double x, double mean, double standardDeviation) {
        double value = 1 / (Math.sqrt(2 * Math.PI) * standardDeviation);
        double exponent = -0.5 * Math.pow((x - mean) / standardDeviation, 2);
        return value * Math.exp(exponent);
    }

    private static double errorFunction(double x) {
        return 1 - (1 / Math.pow(1 +
                0.278393 * x +
                0.230389 * Math.pow(x, 2) +
                0.000972 * Math.pow(x, 3) +
                0.078108 * Math.pow(x, 4), 4));
    }

}
