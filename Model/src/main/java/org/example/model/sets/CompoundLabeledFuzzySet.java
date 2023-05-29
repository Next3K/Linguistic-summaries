package org.example.model.sets;

import lombok.Getter;
import org.example.model.db.Entry;

import java.util.Set;


@Getter
public class CompoundLabeledFuzzySet {

    private final Set<LabeledFuzzySet> subsets;

    public CompoundLabeledFuzzySet(Set<LabeledFuzzySet> subsets) {
        if (subsets.isEmpty()) throw new IllegalArgumentException("Subset cannot be empty!");
        this.subsets = subsets;
    }

    public int getSize() {
        return subsets.size();
    }

    public String getTextualRepresentation() {
        StringBuilder builder = new StringBuilder();
        int count = 0;
        for (var set : subsets) {
            builder.append(set.getTextualRepresentation());
            if (count < subsets.size() - 1) {
                builder.append(" and ");
            }
            count++;
        }
        return builder.toString();
    }

    public Double getMembershipFunctionValueFor(Entry entry) {
        return subsets
                .stream()
                .map(e -> e.getMembershipFunctionValueFor(entry))
                .min(Double::compareTo)
                .orElseGet(() -> 0d);
    }

}
