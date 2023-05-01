package org.example.model;

public class SecondTypeStatement extends Statement{


    protected SecondTypeStatement(Quantifier quantifier, Summarizer summarizer) {
        // check whether quantifier is relative
        super(quantifier, summarizer);
    }

    @Override
    public Double calculateDegreeOfTruth() {
        if (degreeOfTruth != null) {
            return degreeOfTruth;
        }

        // calculate degree of truth

        return degreeOfTruth;
    }
}
