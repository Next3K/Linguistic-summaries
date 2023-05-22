package org.example.model.statements;

import org.example.model.db.Entry;
import org.example.model.quantifiers.Quantifier;
import org.example.model.sets.CompoundableLabeledFuzzySet;
import lombok.Getter;

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
    private Double degreeOfAppropriateness;
    private Double lengthOfSummary;
    private Double optimalSummary;
    private Double degreeOfQuantifierImprecision;
    private Double degreeOfQuantifierCardinality;
    private Double degreeOfQualifierCardinality;
    private Double degreeOfQualifierImprecision;
    private Double lengthOfQualifier;
    private Double degreeOfCovering;

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

    public double calculateDegreeOfTruth(List<Entry> entries) {
        double valueCalculatedFromEntries = 3.14;
        this.degreeOfTruth = this.quantifier.getQuantified(valueCalculatedFromEntries);
        return degreeOfTruth;
    }

    public double calculateDegreeOfCovering(List<Entry> entries) {
        if (this.degreeOfCovering == null) {
            this.degreeOfCovering = this.getDegreeOfCovering(entries);
        }
        return this.degreeOfCovering;
    }

    public double calculateDegreeOfImprecision(List<Entry> entries) {
        this.degreeOfImprecision = 0d;
        return this.degreeOfImprecision;
    }

    public double calculateDegreeOfAppropriateness(List<Entry> entries) {
        this.degreeOfAppropriateness = 0d;
        double t3 = this.getDegreeOfCovering(entries);
        return degreeOfAppropriateness;
    }

    public double calculateLengthOfSummary(List<Entry> entries) {
        this.lengthOfSummary = 0d;
        return this.lengthOfSummary;
    }

    public double calculateOptimalSummary(List<Entry> entries) {
        this.optimalSummary = 0d;
        return optimalSummary;
    }

    public double calculateDegreeOfQuantifierImprecision(List<Entry> entries) {
        this.degreeOfQuantifierImprecision = 0d;
        return this.degreeOfQuantifierImprecision;
    }

    public double calculateDegreeOfQuantifierCardinality(List<Entry> entries) {
        this.degreeOfQuantifierCardinality = 0d;
        return this.degreeOfQuantifierCardinality;
    }

    public double calculateDegreeOfQualifierCardinality(List<Entry> entries) {
        this.degreeOfQualifierCardinality = 1d;
        return this.degreeOfQualifierCardinality;
    }

    public double calculateDegreeOfQualifierImprecision(List<Entry> entries) {
        this.degreeOfQualifierImprecision = 1d;
        return this.degreeOfQualifierImprecision;
    }

    public double calculateLengthOfQualifier(List<Entry> entries) {
        this.lengthOfQualifier = 0d;
        return this.lengthOfQualifier;
    }

    public double calculateWeightedMeasure(List<Entry> entries, List<Double> weights) {
        return weights.get(0) * this.calculateDegreeOfTruth(entries) +
                weights.get(1) * this.calculateDegreeOfImprecision(entries) +
                weights.get(2) * this.calculateDegreeOfCovering(entries) +
                weights.get(3) * this.calculateDegreeOfAppropriateness(entries) +
                weights.get(4) * this.calculateLengthOfSummary(entries) +
                weights.get(5) * this.calculateOptimalSummary(entries) +
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
                0.07 * this.calculateOptimalSummary(entries) +
                0.07 * this.calculateDegreeOfQuantifierImprecision(entries) +
                0.07 * this.calculateDegreeOfQuantifierCardinality(entries) +
                0.07 * this.calculateDegreeOfQualifierCardinality(entries) +
                0.07 * this.calculateDegreeOfQualifierImprecision(entries) +
                0.07 * this.calculateLengthOfQualifier(entries);
    }

}
