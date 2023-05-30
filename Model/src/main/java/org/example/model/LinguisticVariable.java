package org.example.model;

import org.example.model.sets.FuzzySet;
import lombok.Getter;
import org.example.model.sets.UniverseOfDiscourseTwo;

import java.util.Set;

@Getter
public class LinguisticVariable {

    // tutaj trzeba wywalic wiekszosc
    // tutaj set tych fuzzysetow
    private final Set<FuzzySet> linguisticValues;
    private final UniverseOfDiscourseTwo universeOfDiscourse;

    public LinguisticVariable(Set<FuzzySet> linguisticValues,
                              UniverseOfDiscourseTwo universeOfDiscourse) {
        this.linguisticValues = linguisticValues;
        this.universeOfDiscourse = universeOfDiscourse;
    }

//    public LinguisticVariable(String name,
//                              Set<FuzzySet> linguisticValues,
//                              UniverseOfDiscourse universeOfDiscourse) {
//        this.name = name;
//        this.linguisticValues = linguisticValues;
//        this.universeOfDiscourse = universeOfDiscourse;
//    }



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

}
