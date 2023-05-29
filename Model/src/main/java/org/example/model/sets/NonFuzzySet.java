package org.example.model.sets;


import lombok.Getter;
import org.example.model.functions.MembershipFunction;

@Getter
public class NonFuzzySet {

    protected final MembershipFunction membershipFunction;
    // przestrzeń na pewno musi byc tak w fuzzy set
    protected final UniverseOfDiscourse universe;

    public NonFuzzySet(MembershipFunction membershipFunction, UniverseOfDiscourse universe) {
        this.membershipFunction = membershipFunction;
        this.universe = universe;
    }

    public boolean isEmpty() {
        return universe.isEmpty();
    }
}
