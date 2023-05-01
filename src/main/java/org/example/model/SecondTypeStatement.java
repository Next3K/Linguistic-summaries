package org.example.model;

public class SecondTypeStatement extends Statement {


    protected SecondTypeStatement(Quantifier quantifier, Summarizer summarizer, Summarizer qualifier) {
        super(quantifier, summarizer);

        // check whether quantifier is OK
        if (!quantifier.isRelative()) {
            throw new IllegalStateException("passed illegal quantifier");
        }

        this.qualifier = qualifier;
    }

    protected final Summarizer qualifier;

    @Override
    public Double calculateDegreeOfTruth() {
        if (degreeOfTruth != null) {
            return degreeOfTruth;
        }

        // calculate degree of truth

        return degreeOfTruth;
    }

    @Override
    public String getTextualRepresentation() {
        return quantifier.getTextualRepresentation() + " " +
                SUBJECT + " being/having " +  qualifier.getTextualRepresentation() +
                " are also / have also "  + summarizer.getTextualRepresentation();
    }


}
