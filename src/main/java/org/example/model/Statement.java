package org.example.model;

public abstract class Statement {

    protected static final String SUBJECT = "students";
    protected static final String EXPRESSION = "are / have";
    protected final Quantifier quantifier;
    protected Double degreeOfTruth;
    protected final Summarizer summarizer;

    protected Statement(Quantifier quantifier, Summarizer summarizer) {
        this.quantifier = quantifier;
        this.summarizer = summarizer;
    }

    abstract public Double calculateDegreeOfTruth();

    String getTextualRepresentation() {
        return quantifier.getTextualRepresentation() + " " +
                SUBJECT + " " + EXPRESSION + " " + summarizer.getTextualRepresentation();
    }
}
