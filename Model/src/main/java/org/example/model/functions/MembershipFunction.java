package org.example.model.functions;

import lombok.Getter;
import org.example.model.sets.NonFuzzySet;
import org.example.model.sets.UniverseOfDiscourse;

@Getter
public abstract class MembershipFunction {

    protected UniverseOfDiscourse universeOfDiscourse;

    public MembershipFunction(UniverseOfDiscourse universeOfDiscourse) {
        if (universeOfDiscourse.calculateMeasure().doubleValue() == 0) {
            throw new IllegalArgumentException("Universe cannot be empty!");
        }
        this.universeOfDiscourse = universeOfDiscourse;
    }

    public abstract Double evaluate(Double x);

    public abstract Double getIntegral();

    public abstract Double getMaxValue();

    public abstract NonFuzzySet getSupport();

    public abstract NonFuzzySet getAlfaCut(double y);
}
