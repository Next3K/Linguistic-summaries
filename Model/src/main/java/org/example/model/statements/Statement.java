package org.example.model.statements;

import org.example.model.measures.Measures;
import org.example.model.quantifiers.Quantifier;
import org.example.model.sets.Summarizer;
import lombok.Getter;

import java.util.Objects;

@Getter
public abstract class Statement {

    // subject of the summary
    protected static final String SUBJECT = "daily weather measurement";

    protected final Quantifier quantifier;
    protected final Summarizer summarizer;

    protected Double qualityMeasure;

    protected Statement(Quantifier quantifier, Summarizer summarizer) {
        this.quantifier = quantifier;
        this.summarizer = summarizer;
    }

    String getReport() {
        return getTextualRepresentation() + " [" + calculateQualityMeasure() + "]";
    }

    public abstract String getTextualRepresentation();

    public Double calculateQualityMeasure() {
        return Objects.requireNonNullElse(this.qualityMeasure, Measures.calculateWeightedMeasure(this));
    }

}
