package org.example.model.statements;

import org.example.model.db.Entry;
import org.example.model.quantifiers.Quantifier;
import org.example.model.sets.CompoundableLabeledFuzzySet;
import org.example.model.sets.LabeledFuzzySet;

import java.util.List;

public class SecondTypeSummary extends Summary {

    public SecondTypeSummary(Quantifier quantifier,
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

    @Override
    public double calculateDegreeOfQualifierImprecision(List<Entry> entries) {
        List<LabeledFuzzySet> subset = this.qualifier.getSubset();
        int n = subset.size();
        double multiply = 1.0;
        for (var set : subset) {
            multiply *= set.getDegreeOfFuzziness();
        }
        this.degreeOfQualifierImprecision = 1 - Math.pow(multiply, 1.0 / n);
        return this.degreeOfQualifierImprecision;
    }

    @Override
    public double calculateDegreeOfQualifierCardinality(List<Entry> entries) {
        List<LabeledFuzzySet> subset = this.qualifier.getSubset();
        int n = subset.size();
        double multiply = 1.0;
        for (var set : subset) {
            multiply *=
                    set.getCardinalityLikeMeasure() /
                    set.getUniverseOfDiscourse().calculateMeasure().doubleValue();
        }
        this.degreeOfQualifierCardinality = 1 - Math.pow(multiply, 1.0 / n);
        return this.degreeOfQualifierCardinality;
    }
}
