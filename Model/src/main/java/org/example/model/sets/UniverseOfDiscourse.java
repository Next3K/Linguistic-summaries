package org.example.model.sets;


public interface UniverseOfDiscourse {

    double getMinimum();

    double getMaximum();

    boolean valueInUniverseOfDiscourse(Double value);

    Number calculateMeasure();

    default boolean isEmpty() {
        return calculateMeasure().doubleValue() == 0;
    }
}
