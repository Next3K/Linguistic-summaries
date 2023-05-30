package org.example.model.functions;

import lombok.Getter;
import org.example.model.sets.NonFuzzySet;

@Getter
public abstract class MembershipFunction {

    protected NonFuzzySet universeOfDiscourse;

    public MembershipFunction(NonFuzzySet universeOfDiscourse) {
        if (universeOfDiscourse.isEmpty()) {
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
