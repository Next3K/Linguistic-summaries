package org.example.model;

import java.util.Objects;

public class FirstTypeStatement extends Statement {

    public FirstTypeStatement(Quantifier quantifier, Summarizer summarizer) {
        super(quantifier, summarizer);
    }

//    @Override
//    public Double calculateQualityMeasure() {
//        return Objects.requireNonNullElse(this.qualityMeasure, 0.45d);
//    }


    @Override
    public String getTextualRepresentation() {
        return quantifier.getTextualRepresentation() + " " +
                SUBJECT + " are/have " + summarizer;
    }

}
