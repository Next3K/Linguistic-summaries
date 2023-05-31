package org.example.model.summary;

import lombok.Getter;
import org.example.model.quantifiers.Quantifier;
import org.example.model.sets.CompoundFuzzySet;

import java.util.Objects;

@Getter
public class TwoSubjectSummaryTypeOne extends TwoSubjectSummary {

    protected TwoSubjectSummaryTypeOne(String subjectOne,
                                       String subjectTwo,
                                       Quantifier quantifier,
                                       CompoundFuzzySet summarizer) {
        super(subjectOne, subjectTwo);
        this.quantifier = quantifier;
        this.summarizer = summarizer;
        if (!this.quantifier.isRelative()) throw new IllegalArgumentException("Qualifier has to be relative!");
    }

    private final Quantifier quantifier;
    private final CompoundFuzzySet summarizer;

    @Override
    public String getTextualRepresentation() {
        return quantifier.getTextualRepresentation() + " " +
                subjectOne + " in comparison to " +
                subjectTwo + " is/have: " +
                summarizer.getTextualRepresentation();
    }

    @Override
    public Double calculateQualityMeasure() {
        return Objects.requireNonNullElse(this.qualityMeasure, 1.0);
    }
}
