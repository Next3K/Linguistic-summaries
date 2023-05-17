package org.example.model;

import org.example.model.measures.AggregatingMeasure;
import org.example.model.measures.Measures;

import java.util.Objects;

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
