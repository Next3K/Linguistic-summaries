package org.example.model;

public class AbsoluteQuantifier implements Quantifier {

    private final FuzzySet set;
    private final Integer value;
    private final AbsoluteQuantifierType type;

    public enum AbsoluteQuantifierType {
        ABOUT, LESS_THAN, OVER;

        public String getInTextualForm() {
            return switch (this) {
                case OVER -> "over";
                case ABOUT -> "about";
                case LESS_THAN -> "less than";
            };
        }

        public FuzzySet getFuzzySet(int value) {
            return switch (this) {
                case OVER -> null;
                case ABOUT -> null;
                case LESS_THAN -> null;
            };
        }
    }

    public AbsoluteQuantifier(AbsoluteQuantifierType type, Integer value) {
        this.value = value;
        this.type = type;
        this.set = type.getFuzzySet(value);
    }

    @Override
    public String getTextualRepresentation() {
        return this.type.getInTextualForm() + " " + value;
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
