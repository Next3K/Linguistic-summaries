package org.example.model;

import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.Set;

@AllArgsConstructor
public class LinguisticVariable {
    private final String name;
    private final Set<String> linguisticValues;
    private final UniverseOfDiscourse universeOfDiscourse;
    private final String syntacticRule;
    private final Map<String,FuzzySet> semanticRule;
}
