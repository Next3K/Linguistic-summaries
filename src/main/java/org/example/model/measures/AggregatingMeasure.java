package org.example.model.measures;

import org.example.model.QualityMeasure;
import org.example.model.Statement;

public class AggregatingMeasure implements QualityMeasure {

    @Override
    public double getValue(Statement statement) {
        return 0.30 * new DegreeOfTruth().getValue(statement) +
                0.07 * new DegreeOfAppropriateness().getValue(statement) +
                0.07 * new DegreeOfCoverage().getValue(statement) +
                0.07 * new DegreeOfImprecision().getValue(statement) +
                0.07 * new DegreeOfQualifierCardinality().getValue(statement) +
                0.07 * new DegreeOfQualifierImprecision().getValue(statement) +
                0.07 * new DegreeOfQuantifierCardinality().getValue(statement) +
                0.07 * new DegreeOfQuantifierImprecision().getValue(statement) +
                0.07 * new DegreeOfQulifierCardinalityTwo().getValue(statement) +
                0.07 * new DegreeOfSummarizerCardinality().getValue(statement) +
                0.07 * new LengthOfSummary().getValue(statement);
    }

    @Override
    public String getName() {
        return null;
    }
}
