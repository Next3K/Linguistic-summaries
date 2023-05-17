package org.example.model.statements;

import org.example.model.quantifiers.Quantifier;
import org.example.model.Summarizer;

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
    public Double calculateQualityMeasure() {
        return 0d;
    }

    @Override
    public String getTextualRepresentation() {
        return quantifier.getTextualRepresentation() + " " +
                SUBJECT + " being/having " +  qualifier.getTextualRepresentation() +
                " are also / have also "  + summarizer.getTextualRepresentation();
    }


}
