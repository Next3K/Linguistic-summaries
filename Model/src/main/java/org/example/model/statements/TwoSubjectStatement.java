package org.example.model.statements;

import lombok.Getter;
import org.example.model.quantifiers.Quantifier;

import java.util.Objects;

@Getter
public abstract class TwoSubjectStatement {
    // subjects of the summary
    protected final String subjectOne;
    protected final String subjectTwo;
    protected Double qualityMeasure;

    protected TwoSubjectStatement(String subjectOne, String subjectTwo) {
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
