package org.example.model.summary;

import org.example.model.db.Entry;
import org.example.model.quantifiers.Quantifier;
import org.example.model.sets.CompoundFuzzySet;

import java.util.List;

public class FirstFormSummary extends Summary {

    public FirstFormSummary(Quantifier quantifier, CompoundFuzzySet summarizer) {
        super(quantifier, summarizer);
    }

    @Override
    protected double getDegreeOfCovering(List<Entry> entries) {
        double m = entries.size();
        return (entries
                .stream()
                .filter(e -> this.summarizer.evaluateFor(e) > 0)
                .count()) / m;
    }

    @Override
    public double calculateDegreeOfTruth(List<Entry> entries) {
        double valueCalculatedFromEntries = 0d;
        for (var entry : entries) {
            valueCalculatedFromEntries += this.summarizer.evaluateFor(entry);
        }
        double m = (this.quantifier.isRelative()) ?
                (double) entries.size() : 1d;
        this.degreeOfTruth = this.quantifier.getMembershipShape().evaluate(valueCalculatedFromEntries / m);
        return degreeOfTruth;
    }

    @Override
    public double calculateDegreeOfQualifierImprecision(List<Entry> entries) {
        this.degreeOfQualifierImprecision = 0d;
        return this.degreeOfQualifierImprecision;
    }

    @Override
    public double calculateDegreeOfQualifierCardinality(List<Entry> entries) {
        this.degreeOfQualifierCardinality = 0d;
        return this.degreeOfQualifierCardinality;
    }

    @Override
    public double calculateLengthOfQualifier() {
        this.lengthOfQualifier = 1d;
        return this.lengthOfQualifier;
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
