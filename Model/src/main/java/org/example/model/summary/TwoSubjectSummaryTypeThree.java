package org.example.model.summary;

import lombok.Getter;
import org.example.model.quantifiers.Quantifier;
import org.example.model.sets.CompoundFuzzySet;

import java.util.Objects;

@Getter
public class TwoSubjectSummaryTypeThree extends TwoSubjectSummary {
    protected TwoSubjectSummaryTypeThree(String subjectOne,
                                         String subjectTwo,
                                         Quantifier quantifier,
                                         CompoundFuzzySet summarizer,
                                         CompoundFuzzySet qualifier) {
        super(subjectOne, subjectTwo);
        this.quantifier = quantifier;
        this.summarizer = summarizer;
        this.qualifier = qualifier;
        if (!this.quantifier.isRelative()) throw new IllegalArgumentException("Qualifier has to be relative!");
    }

    private final Quantifier quantifier;
    private final CompoundFuzzySet summarizer;
    private final CompoundFuzzySet qualifier;

    @Override
    public String getTextualRepresentation() {
        return quantifier.getTextualRepresentation() + " " +
                subjectOne + " which are/have: " +
                subjectTwo + " in comparison to " +
                qualifier.getTextualRepresentation() + "," + " are/have: " +
                summarizer.getTextualRepresentation();
    }

    @Override
    public Double calculateQualityMeasure() {
        return Objects.requireNonNullElse(this.qualityMeasure, 1.0);
    }
}
