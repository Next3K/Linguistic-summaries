package org.example.model;

public class AbsoluteQuantifier implements Quantifier {

    private final FuzzySet set;
    private final Integer value;
    private final AbsoluteQuantifierType type;
    private final UniverseOfDiscourse universeOfDiscourse;

    public enum AbsoluteQuantifierType {
        ABOUT, LESS_THAN, OVER;

        public String getInTextualForm() {
            return switch (this) {
                case OVER -> "over";
                case ABOUT -> "about";
                case LESS_THAN -> "less than";
            };
        }

        public FuzzySet getFuzzySet(int value, UniverseOfDiscourse universeOfDiscourse) {
            Integer maximum = universeOfDiscourse.getMaximum();
            Integer minimum = universeOfDiscourse.getMinimum();
            return switch (this) {
                case OVER -> new FuzzySet(
                        new TrapezoidMembershipFunction(minimum, value, maximum, maximum),
                        universeOfDiscourse);
                case ABOUT -> new FuzzySet(
                        new TriangularMembershipFunction(minimum + (0.5 * (value - minimum)), maximum - (0.5 * (maximum - value)), value),
                        universeOfDiscourse);
                case LESS_THAN -> new FuzzySet(
                        new TrapezoidMembershipFunction(minimum, minimum, value, maximum),
                        universeOfDiscourse);
            };
        }
    }

    public AbsoluteQuantifier(AbsoluteQuantifierType type, Integer value, UniverseOfDiscourse universeOfDiscourse) {
        this.universeOfDiscourse = universeOfDiscourse;
        if (!universeOfDiscourse.valueInUniverseOfDiscourse(value)) {
            throw new IllegalArgumentException("Provided value is not in universe of discourse!");
        }
        this.value = value;
        this.type = type;
        this.set = type.getFuzzySet(value, universeOfDiscourse);
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
