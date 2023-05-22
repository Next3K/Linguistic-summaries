package org.example.model.sets;

import lombok.Getter;

import java.util.List;


@Getter
public class CompoundLabeledFuzzySet {

    public static final String AND = " and ";
    private final List<LabeledFuzzySet> subset;

    public CompoundLabeledFuzzySet(List<LabeledFuzzySet> subsets) {
        if (subsets.isEmpty()) throw new IllegalArgumentException("Subset cannot be empty!");
        this.subset = subsets;
    }

    public String getTextualRepresentation() {
        StringBuilder builder = new StringBuilder();
        for (var set : subset) {
            builder.append(set.getTextualRepresentation()).append(AND);
        }
        return builder.toString();
    }

    public Double getSummarizerValueFor(double x) {
        return subset.stream().map(e -> e.getSummarizerValueFor(x)).min(Double::compareTo).get();
    }

}
