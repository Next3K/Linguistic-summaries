package org.example.model;

import org.example.model.sets.FuzzySet;
import lombok.Getter;
import org.example.model.sets.UniverseOfDiscourse;

import java.util.Map;
import java.util.Set;

@Getter
public class LinguisticVariable {

    private final Map<String, FuzzySet> linguisticValues;
    private final UniverseOfDiscourse universeOfDiscourse;

    public LinguisticVariable(Map<String, FuzzySet> linguisticValues,
                              UniverseOfDiscourse universeOfDiscourse) {
        this.linguisticValues = linguisticValues;
        this.universeOfDiscourse = universeOfDiscourse;
    }

    public FuzzySet getSet(String name) {
        return this.linguisticValues.get(name);
    }
}
