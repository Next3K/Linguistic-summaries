package org.example.model.statements;

import org.example.model.db.Entry;
import org.example.model.quantifiers.Quantifier;
import org.example.model.sets.CompoundableLabeledFuzzySet;

import java.util.List;

public class SecondTypeSummary extends Summary {

    protected SecondTypeSummary(Quantifier quantifier,
                                CompoundableLabeledFuzzySet summarizer,
                                CompoundableLabeledFuzzySet qualifier) {
        super(quantifier, summarizer);

        // check whether quantifier is OK
        if (!quantifier.isRelative()) {
            throw new IllegalStateException("passed illegal quantifier");
        }

        this.qualifier = qualifier;
    }

    protected final CompoundableLabeledFuzzySet qualifier;


    @Override
    public String getTextualRepresentation() {
        return quantifier.getTextualRepresentation() + " " +
                SUBJECT + " being/having " +  qualifier.getTextualRepresentation() +
                " are also / have also "  + summarizer.getTextualRepresentation();
    }

    @Override
    protected double getDegreeOfCovering(List<Entry> entries) {
        double up = (double) entries
                .stream()
                .filter(e ->
                        this.summarizer.getMembershipFunctionValueFor(e) > 0
                        &&
                        this.qualifier.getMembershipFunctionValueFor(e) > 0)
                .count();
        double down = (double) entries
                .stream()
                .filter(e -> this.qualifier.getMembershipFunctionValueFor(e) > 0)
                .count();
        return up / down;
    }

    @Override
    public double calculateDegreeOfTruth(List<Entry> entries) {
        double numerator = 0d;
        double denominator = 0d;
        for (var entry : entries) {
            Double summarizerValue = this.summarizer.getMembershipFunctionValueFor(entry);
            Double qualifierValue = this.qualifier.getMembershipFunctionValueFor(entry);
            numerator += Math.min(summarizerValue, qualifierValue);
            denominator += qualifierValue;
        }
        if (denominator == 0) return 0;
        this.degreeOfTruth = numerator / denominator;
        return degreeOfTruth;
    }
}
