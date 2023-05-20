package org.example.model.sets;

import java.util.List;

public class DiscreteNonFuzzySet extends NonFuzzySet {

    private final List<Double> listOfValues;

    public DiscreteNonFuzzySet(List<Double> listOfValues) {
        super(listOfValues.get(0), listOfValues.get(listOfValues.size() - 1), (double) listOfValues.size());
        this.listOfValues = listOfValues;
    }
}
