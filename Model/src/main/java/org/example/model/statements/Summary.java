package org.example.model.statements;

import org.example.model.db.Entry;
import org.example.model.quantifiers.Quantifier;
import org.example.model.sets.CompoundableLabeledFuzzySet;
import lombok.Getter;
import org.example.model.sets.LabeledFuzzySet;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
public abstract class Summary {

    // subject of the summary
    protected static final String SUBJECT = "daily weather measurement";

    protected final Quantifier quantifier;
    protected final CompoundableLabeledFuzzySet summarizer;

    protected Double qualityMeasure;

    protected Double degreeOfTruth;
    protected Double degreeOfImprecision;
    protected Double degreeOfCovering;
    protected Double degreeOfAppropriateness;
    protected Double lengthOfSummary;
    protected Double degreeOfQuantifierImprecision;
    protected Double degreeOfQuantifierCardinality;
    protected Double degreeOfSummarizerCardinality;
    protected Double degreeOfQualifierImprecision;
    protected Double degreeOfQualifierCardinality;
    protected Double lengthOfQualifier;

    protected Summary(Quantifier quantifier, CompoundableLabeledFuzzySet summarizer) {
        this.quantifier = quantifier;
        this.summarizer = summarizer;
    }

    public String getTextualSummary() {
        return getTextualRepresentation() + " [" + this.qualityMeasure + "]";
    }

    public abstract String getTextualRepresentation();

    protected abstract double getDegreeOfCovering(List<Entry> entries);

    public Double calculateQualityMeasure(List<Entry> entries) {
        return Objects.requireNonNullElse(this.qualityMeasure, this.calculateWeightedMeasure(entries));
    }

    // T1
    public abstract double calculateDegreeOfTruth(List<Entry> entries);

    // T2
    public double calculateDegreeOfImprecision() {
        Set<LabeledFuzzySet> subset = this.summarizer.getSubset();
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
        Set<LabeledFuzzySet> subset = this.summarizer.getSubset();
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
    public double calculateLengthOfSummary() {
        this.lengthOfSummary = 2 * Math.pow(0.5, this.summarizer.getSize());
        return this.lengthOfSummary;
    }

    // T6
    public double calculateDegreeOfQuantifierImprecision() {
        this.degreeOfQuantifierImprecision = 1 - this.quantifier.getDegreeOfFuzziness();
        return this.degreeOfQuantifierImprecision;
    }

    // T7

    public double calculateDegreeOfQuantifierCardinality() {
        Quantifier set = this.quantifier;
        this.degreeOfQuantifierCardinality = 1 - (set.getCardinality() /
                set.getUniverseOfDiscourse().calculateMeasure().doubleValue());
        return this.degreeOfQuantifierCardinality;
    }

    // T8
    public double calculateDegreeOfSummarizerCardinality() {
        Set<LabeledFuzzySet> subset = this.summarizer.getSubset();
        int n = subset.size();
        double multiply = 1.0;
        for (var set : subset) {
            multiply *=
                    set.getCardinality() /
                            set.getUniverseOfDiscourse().calculateMeasure().doubleValue();
        }
        this.degreeOfSummarizerCardinality = 1 - Math.pow(multiply, 1.0 / n);
        return this.degreeOfSummarizerCardinality;
    }

    // T9
    public abstract double calculateDegreeOfQualifierImprecision(List<Entry> entries);

    // T10
    public abstract double calculateDegreeOfQualifierCardinality(List<Entry> entries);

    // T11
    public double calculateLengthOfQualifier() {
        this.lengthOfQualifier = 2 * Math.pow(0.5d, this.summarizer.getSize());
        return this.lengthOfQualifier;
    }

    public double calculateWeightedMeasure(List<Entry> entries, List<Double> weights) {
        return weights.get(0) * this.calculateDegreeOfTruth(entries) +
                weights.get(1) * this.calculateDegreeOfImprecision() +
                weights.get(2) * this.calculateDegreeOfCovering(entries) +
                weights.get(3) * this.calculateDegreeOfAppropriateness(entries) +
                weights.get(4) * this.calculateLengthOfSummary() +
                weights.get(5) * this.calculateDegreeOfSummarizerCardinality() +
                weights.get(6) * this.calculateDegreeOfQuantifierImprecision() +
                weights.get(7) * this.calculateDegreeOfQuantifierCardinality() +
                weights.get(8) * this.calculateDegreeOfQualifierCardinality(entries) +
                weights.get(9) * this.calculateDegreeOfQualifierImprecision(entries) +
                weights.get(10) * this.calculateLengthOfQualifier();
    }

    public double calculateWeightedMeasure(List<Entry> entries) {
        return this.calculateWeightedMeasure(entries,
                List.of(0.30, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07));
    }

}
