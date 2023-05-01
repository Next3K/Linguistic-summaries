package org.example.model;

public class NonRelativeQuantifier implements Quantifier {

    private final FuzzySet set;
    private final String description;

    public NonRelativeQuantifier(FuzzySet set, String description) {
        this.set = set;
        this.description = description;
    }

    @Override
    public String getTextualRepresentation() {
        return description;
    }

    @Override
    public Double getQuantified(Double d) {
        return set.calculateMembershipFunctionValue(d);
    }

    @Override
    public Boolean isRelative() {
        return false;
    }
}
