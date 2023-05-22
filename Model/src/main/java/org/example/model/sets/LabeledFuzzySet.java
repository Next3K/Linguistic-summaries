package org.example.model.sets;


import org.example.model.functions.MembershipFunction;
import org.example.model.db.Entry;

import java.util.function.Function;

public class LabeledFuzzySet extends FuzzySet {

    // label of the fuzzy set
    private final String label;
    private final Entry.DatabaseColumn column;

    // generates textual description from summarizer
    private final Function<String, String> descriptionProducer;

    public LabeledFuzzySet(String label,
                           MembershipFunction membershipFunction,
                           UniverseOfDiscourse universeOfDiscourse,
                           Entry.DatabaseColumn column,
                           Function<String, String> descriptionProducer) {
        super(membershipFunction, universeOfDiscourse);

        this.column = column;
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
