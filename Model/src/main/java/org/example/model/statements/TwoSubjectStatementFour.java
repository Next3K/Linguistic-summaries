package org.example.model.statements;

import lombok.Getter;
import org.example.model.sets.CompoundLabeledFuzzySet;
import org.example.model.sets.LabeledFuzzySet;

import java.util.Objects;

@Getter
public class TwoSubjectStatementFour extends TwoSubjectStatement{

    private CompoundLabeledFuzzySet summarizer;

    protected TwoSubjectStatementFour(String subjectOne, String subjectTwo) {
        super(subjectOne, subjectTwo);
    }

    @Override
    public String getTextualRepresentation() {
        return "More" + " " + subjectOne + " than " + subjectTwo + "is/have: " + summarizer.getTextualRepresentation();
    }

    @Override
    public Double calculateQualityMeasure() {
        return Objects.requireNonNullElse(this.qualityMeasure, 1.0);
    }
}
