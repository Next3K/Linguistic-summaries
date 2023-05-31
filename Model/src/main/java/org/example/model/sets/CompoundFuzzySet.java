package org.example.model.sets;

import lombok.Getter;
import org.example.model.db.Entry;

import java.util.Set;


@Getter
public class CompoundFuzzySet {

    private final Set<FuzzySet> fuzzySets;

    public CompoundFuzzySet(Set<FuzzySet> subsets) {
        if (subsets.isEmpty()) throw new IllegalArgumentException("Subset cannot be empty!");
        this.fuzzySets = subsets;
    }

    public String getTextualRepresentation() {
        StringBuilder builder = new StringBuilder();
        int count = 0;
        for (var set : fuzzySets) {
            builder.append(set.getTextualRepresentation());
            if (count < fuzzySets.size() - 1) {
                builder.append(" and ");
            }
            count++;
        }
        return builder.toString();
    }

    public Double evaluateFor(Entry entry) {
        return fuzzySets
                .stream()
                .map(e -> e.evaluateFor(entry))
                .min(Double::compareTo)
                .orElseGet(() -> 0d);
    }

    public Double evaluateFor(double value) {
        return fuzzySets
                .stream()
                .map(e -> e.evaluateFor(value))
                .min(Double::compareTo)
                .orElseGet(() -> 0d);
    }

    public int getSize() {
        return fuzzySets.size();
    }

}
