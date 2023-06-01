package org.example.model.summary;

import lombok.Getter;
import org.example.model.db.Entry;
import org.example.model.quantifiers.Quantifier;
import org.example.model.sets.CompoundFuzzySet;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Getter
public class MultiSubjectSummaryTypeThree extends MultiSubjectSummary {

    private final Quantifier quantifier;
    private final CompoundFuzzySet qualifier;

    public MultiSubjectSummaryTypeThree(Entry.SubjectType subjectOne,
                                        Entry.SubjectType subjectTwo,
                                        Quantifier quantifier,
                                        CompoundFuzzySet summarizer,
                                        CompoundFuzzySet qualifier) {
        super(subjectOne, subjectTwo, summarizer);
        this.quantifier = quantifier;
        this.qualifier = qualifier;
        if (!this.quantifier.isRelative()) throw new IllegalArgumentException("Qualifier has to be relative!");
    }

    @Override
    public String getTextualRepresentation() {
        return quantifier.getTextualRepresentation() + " " +
                subjectOne.description() + " which are/have: " +
                subjectTwo.description() + " in comparison to " +
                qualifier.getTextualRepresentation() + "," + " are/have: " +
                summarizer.getTextualRepresentation();
    }

    @Override
    public Double calculateQualityMeasure(Map<Entry.SubjectType, List<Entry>> entriesPartitioned) {
        var recordsTypeOne = entriesPartitioned.get(this.subjectOne);
        var recordsTypeTwo = entriesPartitioned.get(this.subjectTwo);
        double a = recordsTypeTwo.stream().mapToDouble(e ->
                Math.min(summarizer.evaluateFor(e), qualifier.evaluateFor(e))).sum() / recordsTypeTwo.size();
        double b = recordsTypeOne.stream().mapToDouble(summarizer::evaluateFor).sum() / recordsTypeOne.size();
        this.qualityMeasure = quantifier.evaluateFor(a / (a + b));
        return qualityMeasure;
    }

}
