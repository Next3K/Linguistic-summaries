package org.example.model;


import org.example.model.functions.MembershipFunction;
import org.example.model.sets.FuzzySet;

import java.util.function.Function;

public class Summarizer extends FuzzySet {

    // label of the fuzzy set
    private final String label;

    // generates textual description from summarizer
    private final Function<String, String> descriptionProducer;

    public Summarizer(String label,
                      MembershipFunction membershipFunction,
                      UniverseOfDiscourse universeOfDiscourse,
                      Function<String, String> descriptionProducer) {
        super(membershipFunction, universeOfDiscourse);

        this.descriptionProducer = descriptionProducer;
        this.label = label;
    }

    public String getTextualRepresentation() {
        return descriptionProducer.apply(label);
    }

    public Double getSummarizerValueFor(double x) {
        return this.calculateMembershipFunctionValue(x);
    }
}
