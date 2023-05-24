package org.example.model.sets;

import lombok.Getter;
import org.example.model.db.Entry;

import java.util.List;


@Getter
public class CompoundableLabeledFuzzySet {

    public static final String AND = " and ";
    private final List<LabeledFuzzySet> subset;

    public CompoundableLabeledFuzzySet(List<LabeledFuzzySet> subsets) {
        if (subsets.isEmpty()) throw new IllegalArgumentException("Subset cannot be empty!");
        this.subset = subsets;
    }

    public int getSize() {
        return subset.size();
    }

    public String getTextualRepresentation() {
        StringBuilder builder = new StringBuilder();
        for (var set : subset) {
            builder.append(set.getTextualRepresentation()).append(AND);
        }
        return builder.toString();
    }

    public Double getMembershipFunctionValueFor(Entry entry) {
        return subset
                .stream()
                .map(e -> e.getSummarizerValueFor(entry))
                .min(Double::compareTo)
                .get();
    }

}
