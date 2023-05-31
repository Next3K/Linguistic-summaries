package org.example.model.summary;

import org.example.model.db.Entry;
import org.example.model.quantifiers.Quantifier;
import org.example.model.sets.CompoundFuzzySet;
import org.example.model.sets.FuzzySet;

import java.util.List;
import java.util.Set;


public class SecondTypeSummary extends Summary {

    public SecondTypeSummary(Quantifier quantifier,
                             CompoundFuzzySet summarizer,
                             CompoundFuzzySet qualifier) {
        super(quantifier, summarizer);

        // check whether quantifier is OK
        if (!quantifier.isRelative()) {
            throw new IllegalStateException("passed illegal quantifier");
        }

        this.qualifier = qualifier;
    }

    protected final CompoundFuzzySet qualifier;


    @Override
    public String getTextualRepresentation() {
        return quantifier.getTextualRepresentation() + " " +
                SUBJECT +
                " being/having " +
                qualifier.getTextualRepresentation() +
                " are also / have also "
                + summarizer.getTextualRepresentation();
    }

    @Override
    protected double getDegreeOfCovering(List<Entry> entries) {
        double up = entries
                .stream()
                .filter(e ->
                        this.summarizer.evaluateFor(e) > 0
                                &&
                                this.qualifier.evaluateFor(e) > 0)
                .count();
        if (up == 0) return 0;
        double down = entries
                .stream()
                .filter(e -> this.qualifier.evaluateFor(e) > 0)
                .count();
        return up / down;
    }

    @Override
    public double calculateDegreeOfTruth(List<Entry> entries) {
        double numerator = 0d;
        double denominator = 0d;
        for (var entry : entries) {
            Double summarizerValue = this.summarizer.evaluateFor(entry);
            Double qualifierValue = this.qualifier.evaluateFor(entry);
            numerator += Math.min(summarizerValue, qualifierValue);
            denominator += qualifierValue;
        }
        if (denominator == 0) {
            this.degreeOfTruth = 0d;
            return 0;
        }
        this.degreeOfTruth = quantifier.evaluateFor(numerator / denominator);
        return degreeOfTruth;
    }

    @Override
    public double calculateDegreeOfQualifierImprecision(List<Entry> entries) {
        Set<FuzzySet> subset = this.qualifier.getFuzzySets();
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
        Set<FuzzySet> subset = this.qualifier.getFuzzySets();
        int n = subset.size();
        double multiply = 1.0;
        for (var set : subset) {
            multiply *= set.getCardinality(entries) / (double) entries.size();
        }
        this.degreeOfQualifierCardinality = 1 - Math.pow(multiply, 1.0 / n);
        return this.degreeOfQualifierCardinality;
    }

    @Override
    public double calculateLengthOfQualifier() {
        this.lengthOfQualifier = 2 * Math.pow(0.5d, this.qualifier.getSize());
        return this.lengthOfQualifier;
    }
}
