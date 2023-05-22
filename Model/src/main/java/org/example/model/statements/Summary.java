package org.example.model.statements;

import org.example.model.db.Entry;
import org.example.model.measures.Measures;
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

    protected Summary(Quantifier quantifier, CompoundableLabeledFuzzySet summarizer) {
        this.quantifier = quantifier;
        this.summarizer = summarizer;
    }

    String getTextualSummaryForRecords(List<Entry> records) {
        return getTextualRepresentation() + " [" + calculateQualityMeasure(records) + "]";
    }

    public abstract String getTextualRepresentation();
    public abstract double calculateDegreeOfCovering(List<Entry> entries);

    public Double calculateQualityMeasure(List<Entry> entries) {
        return Objects.requireNonNullElse(this.qualityMeasure, this.calculateWeightedMeasure(entries));
    }

    public double calculateDegreeOfTruth(List<Entry> entries) {
        double valueCalculatedFromEntries = 3.14;
        return this.quantifier.getQuantified(valueCalculatedFromEntries);
    }

    public double calculateDegreeOfImprecision(List<Entry> entries) {
        return 0d;
    }

    public double calculateDegreeOfAppropriateness(List<Entry> entries) {
        return 0d;
    }

    public double calculateLengthOfSummary(List<Entry> entries) {
        return 0d;
    }

    public double calculateOptimalSummary(List<Entry> entries) {
        return 0d;
    }

    public double calculateDegreeOfQuantifierImprecision(List<Entry> entries) {
        return 0d;
    }

    public double calculateDegreeOfQuantifierCardinality(List<Entry> entries) {
        return 0d;
    }

    public double calculateDegreeOfQualifierCardinality(List<Entry> entries) {
        return 0d;
    }

    public double calculateDegreeOfQualifierImprecision(List<Entry> entries) {
        return 0d;
    }

    public double calculateLengthOfQualifier(List<Entry> entries) {
        return 0d;
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
