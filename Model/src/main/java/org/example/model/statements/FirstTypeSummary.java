package org.example.model.statements;

import org.example.model.db.Entry;
import org.example.model.quantifiers.Quantifier;
import org.example.model.sets.CompoundableLabeledFuzzySet;

import java.util.List;

public class FirstTypeSummary extends Summary {

    public FirstTypeSummary(Quantifier quantifier, CompoundableLabeledFuzzySet summarizer) {
        super(quantifier, summarizer);
    }

    @Override
    protected double getDegreeOfCovering(List<Entry> entries) {
        int m = entries.size();
        return (double) entries
                .stream()
                .filter(e -> this.summarizer.getMembershipFunctionValueFor(e) > 0)
                .count() / m;
    }

    @Override
    public double calculateDegreeOfTruth(List<Entry> entries) {
        double valueCalculatedFromEntries = 0d;
        for (var entry : entries) {
            valueCalculatedFromEntries += this.summarizer.getMembershipFunctionValueFor(entry);
        }
        double m = (this.quantifier.isRelative()) ?
                this.quantifier.getUniverseOfDiscourse().calculateMeasure().doubleValue() : 1d;
        this.degreeOfTruth = this.quantifier.getQuantified(valueCalculatedFromEntries) / m;
        return degreeOfTruth;
    }

    @Override
    public double calculateDegreeOfQualifierImprecision(List<Entry> entries) {
        return 1;
    }

    @Override
    public double calculateDegreeOfQualifierCardinality(List<Entry> entries) {
        return 1;
    }


    @Override
    public String getTextualRepresentation() {
        return quantifier.getTextualRepresentation() +
                " " +
                SUBJECT +
                " are/have " +
                summarizer.getTextualRepresentation();
    }

}
