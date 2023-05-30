package org.example.model.summary;

import lombok.Getter;
import org.example.model.sets.Compound;

import java.util.Objects;

@Getter
public class TwoSubjectSummaryTypeFour extends TwoSubjectSummary {

    private Compound summarizer;

    protected TwoSubjectSummaryTypeFour(String subjectOne, String subjectTwo) {
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
