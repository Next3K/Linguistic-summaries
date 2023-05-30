package org.example.model.quantifiers;

import org.example.model.functions.MembershipShape;
import org.example.model.sets.UniverseOfDiscourse;


public class AbsoluteQuantifier extends Quantifier {

    private final Double value;

    // custom absolute quantifiers
    public AbsoluteQuantifier(Double value,
                              String label,
                              MembershipShape membershipFunction,
                              UniverseOfDiscourse universeOfDiscourse) {
        super(label, membershipFunction, universeOfDiscourse);

        if (!universeOfDiscourse.getNonFuzzySet().isValueInTheSet(value)) {
            throw new IllegalArgumentException(
                    "Provided value: " + value +
                    " is not in universe of discourse! Value should be between:" +
                    universeOfDiscourse.getNonFuzzySet().getMinimum() + " and " +
                            universeOfDiscourse.getNonFuzzySet().getMaximum());
        }
        this.value = value;
    }



    @Override
    public String getTextualRepresentation() {
        return this.getLabel() + " " + value.intValue();
    }

    @Override
    public Double getQuantified(Double d) {
        return calculateMembershipFunctionValue(d);
    }

    @Override
    public boolean isRelative() {
        return false;
    }
}
