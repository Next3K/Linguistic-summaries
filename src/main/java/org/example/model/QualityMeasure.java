package org.example.model;

public interface QualityMeasure {
    double getValue(Statement statement);
    String getName();
}
