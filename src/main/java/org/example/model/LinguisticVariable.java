package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.model.sets.FuzzySet;
import org.example.model.sets.UniverseOfDiscourse;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

@AllArgsConstructor
@Getter
public class LinguisticVariable {

    private final String name;
    private final Set<String> linguisticValues;
    private final UniverseOfDiscourse universeOfDiscourse;
    private final Map<String, FuzzySet> semanticRule;
    private final Function<String, String> syntacticRule;

    public LinguisticVariable(String name,
                              Set<String> linguisticValues,
                              UniverseOfDiscourse universeOfDiscourse,
                              Map<String, FuzzySet> semanticRule) {
        this.name = name;
        this.linguisticValues = linguisticValues;
        this.universeOfDiscourse = universeOfDiscourse;
        this.semanticRule = semanticRule;
        this.syntacticRule = this::simpleDescription;
    }

    public LinguisticVariable(String name,
                              Set<String> linguisticValues,
                              UniverseOfDiscourse universeOfDiscourse,
                              Map<String, FuzzySet> semanticRule,
                              SyntacticRuleType syntacticRuleType) {
        this.name = name;
        this.linguisticValues = linguisticValues;
        this.universeOfDiscourse = universeOfDiscourse;
        this.semanticRule = semanticRule;
        this.syntacticRule = switch (syntacticRuleType) {
            case SIMPLE -> this::simpleDescription;
            case COMPLEX -> this::complexDescription;
        };
    }

    public enum SyntacticRuleType {
        SIMPLE, COMPLEX
    }

    // example: "Student is/have: buffed"
    private String simpleDescription(String fuzzySetLabel) {
        return fuzzySetLabel;
    }

    // example: "Student is/have: strongman musculature"
    private String complexDescription(String fuzzySetLabel) {
        return fuzzySetLabel + " " + this.name;
    }
}
