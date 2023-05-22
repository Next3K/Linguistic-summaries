package org.example.model.statements;

import lombok.Getter;
import org.example.model.quantifiers.Quantifier;
import org.example.model.sets.CompoundLabeledFuzzySet;
import org.example.model.sets.LabeledFuzzySet;

import java.util.Objects;

@Getter
public class TwoSubjectStatementOne extends TwoSubjectStatement{

    protected TwoSubjectStatementOne(String subjectOne,
                                     String subjectTwo,
                                     Quantifier quantifier,
                                     CompoundLabeledFuzzySet summarizer) {
        super(subjectOne, subjectTwo);
        this.quantifier = quantifier;
        this.summarizer = summarizer;
        if (!this.quantifier.isRelative()) throw new IllegalArgumentException("Qualifier has to be relative!");
    }

    private final Quantifier quantifier;
    private final CompoundLabeledFuzzySet summarizer;

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
