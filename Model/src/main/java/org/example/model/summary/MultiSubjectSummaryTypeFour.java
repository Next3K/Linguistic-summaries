package org.example.model.summary;

import lombok.Getter;
import org.example.model.db.Entry;
import org.example.model.sets.CompoundFuzzySet;
import org.example.model.sets.FuzzySet;

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
    public String getTextualRepresentation() {
        return "More" + " " +
                subjectOne.description() + " than " +
                subjectTwo.description() + "is/have: " +
                summarizer.getTextualRepresentation();
    }

    @Override
    public Double calculateQualityMeasure(Map<Entry.SubjectType, List<Entry>> entriesPartitioned) {
        var recordsTypeOne = entriesPartitioned.get(this.subjectOne);
        var recordsTypeTwo = entriesPartitioned.get(this.subjectTwo);
        double numerator = recordsTypeTwo.stream().mapToDouble(summarizer::evaluateFor).sum() / recordsTypeTwo.size();
        double denominator = recordsTypeOne.stream().mapToDouble(summarizer::evaluateFor).sum() / recordsTypeOne.size();
        double degreeOfInclusion = numerator / denominator;
        this.qualityMeasure = 1 - degreeOfInclusion;
        return qualityMeasure;
    }

}
