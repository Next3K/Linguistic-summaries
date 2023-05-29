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
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class LinguisticVariable {

    // tutaj trzeba wywalic wiekszosc
    private final String name;
    // tutaj set tych fuzzysetow
    private final Set<FuzzySet> linguisticValues;
    private final UniverseOfDiscourse universeOfDiscourse;

    public LinguisticVariable(String name,
                              Set<FuzzySet> linguisticValues,
                              UniverseOfDiscourse universeOfDiscourse) {
        this.name = name;
        this.linguisticValues = linguisticValues;
        this.universeOfDiscourse = universeOfDiscourse;
    }

    public LinguisticVariable(String name,
                              Set<FuzzySet> linguisticValues,
                              UniverseOfDiscourse universeOfDiscourse) {
        this.name = name;
        this.linguisticValues = linguisticValues;
        this.universeOfDiscourse = universeOfDiscourse;
    }

    public enum SyntacticRuleType {
        SIMPLE, COMPLEX
    }

//    public List<LabeledFuzzySet> getLabeledFuzzySetsForChosenFuzzySets(List<String> chosenSetsLabels) {
//        List<LabeledFuzzySet> tmp = new ArrayList<>(chosenSetsLabels.size());
//        for (var fuzzySetLabel : chosenSetsLabels) {
//            FuzzySet fuzzySet = this.semanticRule.get(fuzzySetLabel);
//            tmp.add(new LabeledFuzzySet(
//                    fuzzySetLabel,
//                    fuzzySet.getMembershipFunction(),
//                    this.universeOfDiscourse,
//                    this.column,
//                    this.syntacticRule));
//        }
//        return linguisticValues.stream().collect(Collectors.toList());
//    }

    // example: "Student is/have: buffed"
    public static String simpleDescription(String fuzzySetLabel) {
        return fuzzySetLabel;
    }

    // example: "Student is/have: strongman musculature"
    public static String complexDescription(String fuzzySetLabel) {
        return fuzzySetLabel + " " + this.name;
    }
}
