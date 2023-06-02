package org.example.model.summary;

import org.example.model.db.Entry;
import org.example.model.sets.CompoundFuzzySet;

import java.util.List;
import java.util.Map;


public abstract class MultiSubjectSummary {

    protected final CompoundFuzzySet summarizer;
    protected final Entry.SubjectType subjectOne;
    protected final Entry.SubjectType subjectTwo;
    protected Double qualityMeasure;


    protected MultiSubjectSummary(Entry.SubjectType subjectOne,
                                  Entry.SubjectType subjectTwo,
                                  CompoundFuzzySet summarizer) {
        this.subjectOne = subjectOne;
        this.subjectTwo = subjectTwo;
        this.summarizer = summarizer;
    }

    public abstract String getTwoSubjectSummaryAsText();

    public abstract Double calculateQualityMeasure(Map<Entry.SubjectType, List<Entry>> entries);

    public double getQualityMeasure() {
        return this.qualityMeasure;
    }

}
