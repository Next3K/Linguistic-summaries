package org.example.model;

public class FirstTypeStatement extends Statement {

    public FirstTypeStatement(Quantifier quantifier, Summarizer summarizer) {
        super(quantifier, summarizer);
    }

    @Override
    public Double calculateDegreeOfTruth() {
        if (this.degreeOfTruth != null) {
            return degreeOfTruth;
        }

        // calculate degree of truth

        return degreeOfTruth;
    }

    @Override
    public String getTextualRepresentation() {
        return quantifier.getTextualRepresentation() + " " +
                SUBJECT + " are/have " + summarizer;
    }

}
