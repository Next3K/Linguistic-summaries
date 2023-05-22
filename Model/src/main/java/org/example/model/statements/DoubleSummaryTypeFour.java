package org.example.model.statements;

import lombok.Getter;
import org.example.model.sets.CompoundableLabeledFuzzySet;

import java.util.Objects;

@Getter
public class DoubleSummaryTypeFour extends TwoSubjectSummary {

    private CompoundableLabeledFuzzySet summarizer;

    protected DoubleSummaryTypeFour(String subjectOne, String subjectTwo) {
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
