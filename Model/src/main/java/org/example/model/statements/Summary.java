package org.example.model.statements;

import org.example.model.db.Entry;
import org.example.model.quantifiers.Quantifier;
import org.example.model.sets.CompoundableLabeledFuzzySet;
import lombok.Getter;
import org.example.model.sets.LabeledFuzzySet;

import java.util.List;
import java.util.Objects;

@Getter
public abstract class Summary {

    // subject of the summary
    protected static final String SUBJECT = "daily weather measurement";

    protected final Quantifier quantifier;
    protected final CompoundableLabeledFuzzySet summarizer;

    protected Double qualityMeasure;

    private Double degreeOfTruth;
    private Double degreeOfImprecision;
    private Double degreeOfCovering;
    private Double degreeOfAppropriateness;
    private Double lengthOfSummary;
    private Double degreeOfQuantifierImprecision;
    private Double degreeOfQuantifierCardinality;
    private Double degreeOfSummarizerCardinality;
    private Double degreeOfQualifierImprecision;
    private Double degreeOfQualifierCardinality;
    private Double lengthOfQualifier;

    protected Summary(Quantifier quantifier, CompoundableLabeledFuzzySet summarizer) {
        this.quantifier = quantifier;
        this.summarizer = summarizer;
    }

    String getTextualSummaryForRecords(List<Entry> records) {
        return getTextualRepresentation() + " [" + calculateQualityMeasure(records) + "]";
    }

    public abstract String getTextualRepresentation();

    protected abstract double getDegreeOfCovering(List<Entry> entries);

    public Double calculateQualityMeasure(List<Entry> entries) {
        return Objects.requireNonNullElse(this.qualityMeasure, this.calculateWeightedMeasure(entries));
    }

    // T1
    public double calculateDegreeOfTruth(List<Entry> entries) {
        double valueCalculatedFromEntries = 3.14;
        this.degreeOfTruth = this.quantifier.getQuantified(valueCalculatedFromEntries);
        return degreeOfTruth;
    }

    // T2
    public double calculateDegreeOfImprecision(List<Entry> entries) {
        List<LabeledFuzzySet> subset = this.summarizer.getSubset();
        int n = subset.size();
        double multiply = 1.0;
        for (var set : subset) {
            multiply *= set.getDegreeOfFuzziness();
        }
        this.degreeOfImprecision = 1 - Math.pow(multiply, 1d / n);
        return this.degreeOfImprecision;
    }

    // T3
    public double calculateDegreeOfCovering(List<Entry> entries) {
        if (this.degreeOfCovering == null) {
            this.degreeOfCovering = this.getDegreeOfCovering(entries);
        }
        return this.degreeOfCovering;
    }

    // T4
    public double calculateDegreeOfAppropriateness(List<Entry> entries) {
        List<LabeledFuzzySet> subset = this.summarizer.getSubset();
        int m = entries.size();
        double multiply = 1.0d;
        for (var set : subset) {
            double r = (double) entries
                    .stream()
                    .filter(e -> set.getSummarizerValueFor(e) > 0)
                    .count() / m;
            multiply *= r;
        }
        double t3 = this.getDegreeOfCovering(entries);
        this.degreeOfAppropriateness = Math.abs(multiply - t3);
        return degreeOfAppropriateness;
    }

    // T5
    public double calculateLengthOfSummary(List<Entry> entries) {
        this.lengthOfSummary = 2 * Math.pow(0.5, this.summarizer.getSize());
        return this.lengthOfSummary;
    }

    // T6
    public double calculateDegreeOfQuantifierImprecision(List<Entry> entries) {
        this.degreeOfQuantifierImprecision = 0d;
        return this.degreeOfQuantifierImprecision;
    }

    // T7
    public double calculateDegreeOfQuantifierCardinality(List<Entry> entries) {
        this.degreeOfQuantifierCardinality = 0d;
        return this.degreeOfQuantifierCardinality;
    }

    // T8
    public double calculateDegreeOfSummarizerCardinality(List<Entry> entries) {
        this.degreeOfSummarizerCardinality = 0d;
        return this.degreeOfSummarizerCardinality;
    }

    // T9
    public double calculateDegreeOfQualifierImprecision(List<Entry> entries) {
        this.degreeOfQualifierImprecision = 1d;
        return this.degreeOfQualifierImprecision;
    }

    // T10
    public double calculateDegreeOfQualifierCardinality(List<Entry> entries) {
        this.degreeOfQualifierCardinality = 1d;
        return this.degreeOfQualifierCardinality;
    }

    // T11
    public double calculateLengthOfQualifier(List<Entry> entries) {
        this.lengthOfQualifier = 2 * Math.pow(0.5d, this.summarizer.getSize());
        return this.lengthOfQualifier;
    }

    public double calculateWeightedMeasure(List<Entry> entries, List<Double> weights) {
        return weights.get(0) * this.calculateDegreeOfTruth(entries) +
                weights.get(1) * this.calculateDegreeOfImprecision(entries) +
                weights.get(2) * this.calculateDegreeOfCovering(entries) +
                weights.get(3) * this.calculateDegreeOfAppropriateness(entries) +
                weights.get(4) * this.calculateLengthOfSummary(entries) +
                weights.get(5) * this.calculateDegreeOfSummarizerCardinality(entries) +
                weights.get(6) * this.calculateDegreeOfQuantifierImprecision(entries) +
                weights.get(7) * this.calculateDegreeOfQuantifierCardinality(entries) +
                weights.get(8) * this.calculateDegreeOfQualifierCardinality(entries) +
                weights.get(9) * this.calculateDegreeOfQualifierImprecision(entries) +
                weights.get(10) * this.calculateLengthOfQualifier(entries);
    }

    public double calculateWeightedMeasure(List<Entry> entries) {
        return 0.30 * this.calculateDegreeOfTruth(entries) +
                0.07 * this.calculateDegreeOfImprecision(entries) +
                0.07 * this.calculateDegreeOfCovering(entries) +
                0.07 * this.calculateDegreeOfAppropriateness(entries) +
                0.07 * this.calculateLengthOfSummary(entries) +
                0.07 * this.calculateDegreeOfSummarizerCardinality(entries) +
                0.07 * this.calculateDegreeOfQuantifierImprecision(entries) +
                0.07 * this.calculateDegreeOfQuantifierCardinality(entries) +
                0.07 * this.calculateDegreeOfQualifierCardinality(entries) +
                0.07 * this.calculateDegreeOfQualifierImprecision(entries) +
                0.07 * this.calculateLengthOfQualifier(entries);
    }

}
