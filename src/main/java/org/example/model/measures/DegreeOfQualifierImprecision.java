package org.example.model.measures;

import org.example.model.QualityMeasure;
import org.example.model.Statement;

public class DegreeOfQualifierImprecision implements QualityMeasure {
    @Override
    public double getValue(Statement statement) {
        return 0;
    }

    @Override
    public String getName() {
        return "degree of qualifier imprecision";
    }
}
