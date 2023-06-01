package org.example.model.summary;

import lombok.Getter;
import org.example.model.db.Entry;
import org.example.model.sets.CompoundFuzzySet;

import java.util.List;
import java.util.Map;

@Getter
public class MultiSubjectSummaryTypeFour extends MultiSubjectSummary {


    public MultiSubjectSummaryTypeFour(Entry.SubjectType subjectOne,
                                       Entry.SubjectType subjectTwo,
                                       CompoundFuzzySet summarizer) {
        super(subjectOne, subjectTwo, summarizer);
    }

    @Override
    public String getTwoSubjectSummaryAsText() {
        return "More" + " " +
                subjectOne.description() + " than " +
                subjectTwo.description() + "is/have: " +
                summarizer.getTextualRepresentation() +
                "[" + this.qualityMeasure + "]";
    }

    @Override
    public Double calculateQualityMeasure(Map<Entry.SubjectType, List<Entry>> entriesPartitioned) {
        var recordsSubjectOne = entriesPartitioned.get(this.subjectOne);
        var recordsSubjectTwo = entriesPartitioned.get(this.subjectTwo);

        var big = Math.max(recordsSubjectTwo.size(), recordsSubjectOne.size());

        double sum = 0;
        for (int i = 0; i < big; i++) {
            double a = (i < recordsSubjectOne.size()) ? summarizer.evaluateFor(recordsSubjectOne.get(i)) : 0;
            double b = (i < recordsSubjectTwo.size()) ? summarizer.evaluateFor(recordsSubjectTwo.get(i)) : 0;
            sum += Math.min(1, 1 - a + b);
        }

        this.qualityMeasure = 1 - (sum / big);
        return qualityMeasure;
    }

}
