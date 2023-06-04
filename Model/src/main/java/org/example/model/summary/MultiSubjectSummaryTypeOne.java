package org.example.model.summary;

import lombok.Getter;
import org.example.model.db.Entry;
import org.example.model.quantifiers.Quantifier;
import org.example.model.sets.CompoundFuzzySet;

import java.util.List;
import java.util.Map;

@Getter
public class MultiSubjectSummaryTypeOne extends MultiSubjectSummary {

    public MultiSubjectSummaryTypeOne(Entry.SubjectType subjectOne,
                                      Entry.SubjectType subjectTwo,
                                         Quantifier quantifier,
                                         CompoundFuzzySet summarizer) {
        super(subjectOne, subjectTwo, summarizer);
        this.quantifier = quantifier;
        if (!this.quantifier.isRelative()) throw new IllegalArgumentException("Qualifier has to be relative!");
    }

    private final Quantifier quantifier;

    @Override
    public String getTwoSubjectSummaryAsText() {
        return quantifier.getTextualRepresentation() + " " +
                subjectOne.description() + " in comparison to " +
                subjectTwo.description() + " are/have: " +
                summarizer.getTextualRepresentation() +
                "[" + this.qualityMeasure + "]";
    }

    @Override
    public String getTwoSubjectSummaryForTable() {
        return quantifier.getTextualRepresentation() + " " +
                subjectOne.description() + " in comparison to " +
                subjectTwo.description() + " are/have: " +
                summarizer.getTextualRepresentation();
    }


    @Override
    public Double calculateQualityMeasure(Map<Entry.SubjectType, List<Entry>>  entriesPartitioned) {
        var recordsTypeOne = entriesPartitioned.get(this.subjectOne);
        var recordsTypeTwo = entriesPartitioned.get(this.subjectTwo);
        double a = recordsTypeOne.stream().mapToDouble(summarizer::evaluateFor).sum() / recordsTypeOne.size();
        double b = recordsTypeTwo.stream().mapToDouble(summarizer::evaluateFor).sum() / recordsTypeTwo.size();
        this.qualityMeasure = quantifier.evaluateFor(a / (a + b));
        return qualityMeasure;
    }
}
