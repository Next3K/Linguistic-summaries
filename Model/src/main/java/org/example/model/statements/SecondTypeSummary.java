package org.example.model.statements;

import org.example.model.db.Entry;
import org.example.model.quantifiers.Quantifier;
import org.example.model.sets.CompoundableLabeledFuzzySet;

import java.util.List;

public class SecondTypeSummary extends Summary {

    protected SecondTypeSummary(Quantifier quantifier,
                                CompoundableLabeledFuzzySet summarizer,
                                CompoundableLabeledFuzzySet qualifier) {
        super(quantifier, summarizer);

        // check whether quantifier is OK
        if (!quantifier.isRelative()) {
            throw new IllegalStateException("passed illegal quantifier");
        }

        this.qualifier = qualifier;
    }

    protected final CompoundableLabeledFuzzySet qualifier;


    @Override
    public String getTextualRepresentation() {
        return quantifier.getTextualRepresentation() + " " +
                SUBJECT + " being/having " +  qualifier.getTextualRepresentation() +
                " are also / have also "  + summarizer.getTextualRepresentation();
    }

    @Override
    public double calculateDegreeOfCovering(List<Entry> entries) {
        double up = (double) entries
                .stream()
                .filter(e ->
                        this.summarizer.getSummarizerValueFor(e) > 0
                        &&
                        this.qualifier.getSummarizerValueFor(e) > 0)
                .count();
        double down = (double) entries
                .stream()
                .filter(e -> this.qualifier.getSummarizerValueFor(e) > 0)
                .count();
        return up / down;
    }
}
