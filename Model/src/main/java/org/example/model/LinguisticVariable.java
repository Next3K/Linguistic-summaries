package org.example.model;

import org.example.model.db.Entry;
import org.example.model.sets.FuzzySet;
import org.example.model.sets.LabeledFuzzySet;
import org.example.model.sets.UniverseOfDiscourse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
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
    private final Entry.DatabaseColumn column;

    public LinguisticVariable(String name,
                              Set<String> linguisticValues,
                              UniverseOfDiscourse universeOfDiscourse,
                              Map<String, FuzzySet> semanticRule,
                              Entry.DatabaseColumn column) {
        this.name = name;
        this.linguisticValues = linguisticValues;
        this.universeOfDiscourse = universeOfDiscourse;
        this.semanticRule = semanticRule;
        this.syntacticRule = this::simpleDescription;
        this.column = column;
    }

    public LinguisticVariable(String name,
                              Set<String> linguisticValues,
                              UniverseOfDiscourse universeOfDiscourse,
                              Map<String, FuzzySet> semanticRule,
                              SyntacticRuleType syntacticRuleType,
                              Entry.DatabaseColumn column) {
        this.name = name;
        this.linguisticValues = linguisticValues;
        this.universeOfDiscourse = universeOfDiscourse;
        this.semanticRule = semanticRule;
        this.syntacticRule = switch (syntacticRuleType) {
            case SIMPLE -> this::simpleDescription;
            case COMPLEX -> this::complexDescription;
        };
        this.column = column;
    }

    public enum SyntacticRuleType {
        SIMPLE, COMPLEX
    }

    public List<LabeledFuzzySet> getLabeledFuzzySetsForChosenFuzzySets(List<String> chosenSetsLabels) {
        List<LabeledFuzzySet> tmp = new ArrayList<>(chosenSetsLabels.size());
        for (var fuzzySetLabel : chosenSetsLabels) {
            FuzzySet fuzzySet = this.semanticRule.get(fuzzySetLabel);
            tmp.add(new LabeledFuzzySet(
                    this.name,
                    fuzzySet.getMembershipFunction(),
                    this.universeOfDiscourse,
                    this.column,
                    this.syntacticRule));
        }
        return tmp;
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
