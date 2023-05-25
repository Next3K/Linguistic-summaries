package org.example.model.statements;

import lombok.Getter;
import org.example.model.quantifiers.Quantifier;
import org.example.model.sets.CompoundableLabeledFuzzySet;

import java.util.Objects;

@Getter
public class DoubleSummaryTypeTwo extends TwoSubjectSummary {

    protected DoubleSummaryTypeTwo(String subjectOne,
                                   String subjectTwo,
                                   Quantifier quantifier,
                                   CompoundableLabeledFuzzySet summarizer,
                                   CompoundableLabeledFuzzySet qualifier) {
        super(subjectOne, subjectTwo);
        this.quantifier = quantifier;
        this.summarizer = summarizer;
        this.qualifier = qualifier;
        if (!this.quantifier.isRelative()) throw new IllegalArgumentException("Qualifier has to be relative!");
    }

    private final Quantifier quantifier;
    private final CompoundableLabeledFuzzySet summarizer;
    private final CompoundableLabeledFuzzySet qualifier;

    @Override
    public String getTextualRepresentation() {
        return quantifier.getTextualRepresentation() + " " +
                subjectOne + " in comparison to " +
                subjectTwo + " which are/have: " +
                qualifier.getTextualRepresentation() + "," + " are/have: " +
                summarizer.getTextualRepresentation();
    }

    @Override
    public Double calculateQualityMeasure() {
        return Objects.requireNonNullElse(this.qualityMeasure, 1.0);
    }
}