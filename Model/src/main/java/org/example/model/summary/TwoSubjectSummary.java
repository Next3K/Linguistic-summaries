package org.example.model.summary;

import lombok.Getter;

import java.util.Objects;

@Getter
public abstract class TwoSubjectSummary {
    // subjects of the summary
    protected final String subjectOne;
    protected final String subjectTwo;
    protected Double qualityMeasure;

    protected TwoSubjectSummary(String subjectOne, String subjectTwo) {
        this.subjectOne = subjectOne;
        this.subjectTwo = subjectTwo;
    }

    String getReport() {
        return getTextualRepresentation() + " [" + calculateQualityMeasure() + "]";
    }

    public abstract String getTextualRepresentation();

    public Double calculateQualityMeasure() {
        return Objects.requireNonNullElse(this.qualityMeasure, 1.0);
    }
}
