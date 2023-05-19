package org.example.model.quantifiers;

import org.example.model.sets.FuzzySet;
import org.example.model.sets.UniverseOfDiscourse;
import org.example.model.functions.TrapezoidMembershipFunction;
import org.example.model.functions.TriangularMembershipFunction;


public class AbsoluteQuantifier extends Quantifier {

    private final FuzzySet fuzzySet;
    private final Double value;
    private final String textualForm;

    // standard absolute quantifiers
    public AbsoluteQuantifier(AbsoluteQuantifierType type,
                              Double value) {
        super(type.getFuzzySet(value).getMembershipFunction(), type.getFuzzySet(value).getUniverseOfDiscourse());

        if (!AbsoluteQuantifierType.universeOfDiscourse.valueInUniverseOfDiscourse(value)) {
            throw new IllegalArgumentException(
                    "Provided value: " + value +
                            " is not in universe of discourse! Value should be between:" +
                            AbsoluteQuantifierType.universeOfDiscourse.getMinimum() + " and " +
                            AbsoluteQuantifierType.universeOfDiscourse.getMaximum());
        }
        this.value = value;
        this.fuzzySet = type.getFuzzySet(value);
        this.textualForm = type.getInTextualForm();
    }

    // custom absolute quantifiers
    public AbsoluteQuantifier(String label, Double value, FuzzySet fuzzySet) {
        super(fuzzySet.getMembershipFunction(), fuzzySet.getUniverseOfDiscourse());

        if (!AbsoluteQuantifierType.universeOfDiscourse.valueInUniverseOfDiscourse(value)) {
            throw new IllegalArgumentException(
                    "Provided value: " + value +
                    " is not in universe of discourse! Value should be between:" +
                    AbsoluteQuantifierType.universeOfDiscourse.getMinimum() + " and " +
                    AbsoluteQuantifierType.universeOfDiscourse.getMaximum());
        }
        this.value = value;
        this.fuzzySet = fuzzySet;
        this.textualForm = label;
    }


    public enum AbsoluteQuantifierType {
        ABOUT, LESS_THAN, OVER;

        private static final UniverseOfDiscourse universeOfDiscourse = new UniverseOfDiscourse(0d, 15_000d);

        public String getInTextualForm() {
            return switch (this) {
                case OVER -> "More than";
                case ABOUT -> "About";
                case LESS_THAN -> "Less than";
            };
        }

        public FuzzySet getFuzzySet(double value) {
            var maximum = universeOfDiscourse.getMaximum();
            var minimum = universeOfDiscourse.getMinimum();
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


    @Override
    public String getTextualRepresentation() {
        return this.textualForm + " " + value;
    }

    @Override
    public Double getQuantified(Double d) {
        return fuzzySet.calculateMembershipFunctionValue(d);
    }

    @Override
    public Boolean isRelative() {
        return false;
    }
}
