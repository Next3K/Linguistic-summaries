package org.example.model.statements;

import org.example.model.db.Entry;
import org.example.model.quantifiers.Quantifier;
import org.example.model.sets.CompoundableLabeledFuzzySet;

import java.util.List;

public class FirstTypeSummary extends Summary {

    public FirstTypeSummary(Quantifier quantifier, CompoundableLabeledFuzzySet summarizer) {
        super(quantifier, summarizer);
    }

    @Override
    protected double getDegreeOfCovering(List<Entry> entries) {
        int m = entries.size();
        return (double) entries
                .stream()
                .filter(e -> this.summarizer.getSummarizerValueFor(e) > 0)
                .count() / m;
    }


    @Override
    public String getTextualRepresentation() {
        return quantifier.getTextualRepresentation() + " " +
                SUBJECT + " are/have " + summarizer;
    }

}
