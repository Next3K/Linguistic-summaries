package org.example.model.sets;

import lombok.Getter;
import org.example.model.db.Entry;

import java.util.Set;


@Getter
public class CompoundableLabeledFuzzySet {

    public static final String AND = " and ";
    private final Set<LabeledFuzzySet> subset;

    public CompoundableLabeledFuzzySet(Set<LabeledFuzzySet> subsets) {
        if (subsets.isEmpty()) throw new IllegalArgumentException("Subset cannot be empty!");
        this.subset = subsets;
    }

    public int getSize() {
        return subset.size();
    }

    public String getTextualRepresentation() {
        StringBuilder builder = new StringBuilder();
        int count = 0;
        for (var set : subset) {
            builder.append(set.getTextualRepresentation());
            if (count != set.measure - 1) {
                builder.append(AND);
            }
            count++;
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
