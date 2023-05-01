package org.example.model;

public class RelativeQuantifier implements Quantifier{

    private final FuzzySet set;

    public RelativeQuantifier(FuzzySet set) {
        this.set = set;
    }

    @Override
    public String getTextualRepresentation() {
        return null;
    }

    @Override
    public Double getQuantified(Double d) {
        return null;
    }

    @Override
    public Boolean isRelative() {
        return null;
    }
}
