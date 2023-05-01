package org.example.model;

public abstract class Statement {

    // subject of the summary
    protected static final String SUBJECT = "students";

    protected final Quantifier quantifier;
    protected final Summarizer summarizer;

    protected Double degreeOfTruth;

    protected Statement(Quantifier quantifier, Summarizer summarizer) {
        this.quantifier = quantifier;
        this.summarizer = summarizer;
    }

    String getReport() {
        return getTextualRepresentation() + " [" + calculateDegreeOfTruth() + "]";
    }

    public abstract String getTextualRepresentation();

    public abstract Double calculateDegreeOfTruth();
}
