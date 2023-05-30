package org.example.model.functions;

import lombok.Getter;
import org.example.model.sets.*;

@Getter
public class GaussianShape implements MembershipShape {

    private final double mean;
    private final double stDev;

    public GaussianShape(double mean, double standardDeviation) {
        this.mean = mean;
        this.stDev = standardDeviation;
    }

    @Override
    public Double evaluate(Double x) {
        return gauss(x, this.mean, this.stDev);
    }


    @Override
    public Double getIntegral(double a, double b) {
        double valB = (this.mean - b) / (Math.sqrt(2) * this.stDev);
        double valA = (this.mean - a) / (Math.sqrt(2) * this.stDev);
        double num = Math.sqrt(Math.PI / 2.0) * (-1 * this.stDev);
        double err2 = errorFunction(valB);
        double err1 = errorFunction(valA);
        return num * (err2 - err1);
    }



    @Override
    public Double getMaxValue() {
        return 1d;
    }


    @Override
    public NonFuzzySet getSupport(UniverseOfDiscourse universe) {
        return universe.getNonFuzzySet().getSubset(
                universe.getNonFuzzySet().getMin().doubleValue(),
                universe.getNonFuzzySet().getMax().doubleValue());
    }

    @Override
    public NonFuzzySet getAlfaCut(UniverseOfDiscourse universe, double y) {
        double tmp = Math.sqrt(-2 * Math.pow(this.stDev, 2) * Math.log(y));
        double leftPoint = this.mean - tmp;
        double rightPoint = this.mean + tmp;
        leftPoint = Math.max(leftPoint, universe.getNonFuzzySet().getMin().doubleValue());
        rightPoint = Math.min(rightPoint, universe.getNonFuzzySet().getMax().doubleValue());
        return universe.getNonFuzzySet().getSubset(leftPoint, rightPoint);
    }


    private static double gauss(double x, double mean, double standardDeviation) {
        double exponent = -0.5 * Math.pow((x - mean) / standardDeviation, 2);
        return Math.exp(exponent);
    }

    private static double errorFunction(double x) {
        if (x < 0) {
            return -errorFunction(-x);
        } else {
            return 1 - (1 / Math.pow(1 +
                    0.278393 * x +
                    0.230389 * Math.pow(x, 2) +
                    0.000972 * Math.pow(x, 3) +
                    0.078108 * Math.pow(x, 4), 4));
        }
    }

}
