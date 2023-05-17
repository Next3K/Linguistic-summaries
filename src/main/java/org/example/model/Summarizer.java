package org.example.model;

public class Summarizer {

    private final String label;
    private final FuzzySet fuzzySet;
    private final LinguisticVariable linguisticVariable;

    public Summarizer(LinguisticVariable linguisticVariable, String label) {
        this.linguisticVariable = linguisticVariable;
        this.label = label;
        this.fuzzySet = linguisticVariable.getSemanticRule().get(label);
    }

    public String getTextualRepresentation() {
        return linguisticVariable.getSyntacticRule().apply(label);
    }

    public Double getValueFor(double x) {
        return fuzzySet.calculateMembershipFunctionValue(x);
    }
}
