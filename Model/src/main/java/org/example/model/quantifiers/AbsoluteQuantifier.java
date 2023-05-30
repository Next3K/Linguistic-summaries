package org.example.model.quantifiers;

import org.example.model.functions.MembershipFunction;
import org.example.model.sets.UniverseOfDiscourseTwo;


public class AbsoluteQuantifier extends Quantifier {

    private final Double value;

    // custom absolute quantifiers
    public AbsoluteQuantifier(Double value,
                              String label,
                              MembershipFunction membershipFunction,
                              UniverseOfDiscourseTwo universeOfDiscourse) {
        super(label, membershipFunction, universeOfDiscourse);

        if (!universeOfDiscourse.getNonFuzzySet().isValueInTheSet(value)) {
            throw new IllegalArgumentException(
                    "Provided value: " + value +
                    " is not in universe of discourse! Value should be between:" +
                    universeOfDiscourse.getNonFuzzySet().getMinimum() + " and " +
                            universeOfDiscourse.getNonFuzzySet().getMaximum());
        }
        this.value = value;
    }


//    public enum AbsoluteQuantifierType {
//        ABOUT, LESS_THAN, OVER;
//
//        private static final UniverseOfDiscourse universeOfDiscourse = new DiscreteUniverseOfDiscourse(0, 15000);
//
//        public String getInTextualForm() {
//            return switch (this) {
//                case OVER -> "More than";
//                case ABOUT -> "About";
//                case LESS_THAN -> "Less than";
//            };
//        }
//
//        public FuzzySet getFuzzySet(double value) {
//            var maximum = universeOfDiscourse.getMaximum();
//            var minimum = universeOfDiscourse.getMinimum();
//            return switch (this) {
//                case OVER -> new FuzzySet(
//                        new TrapezoidMembershipFunction(minimum, value, maximum, maximum),
//                        universeOfDiscourse);
//                case ABOUT -> new FuzzySet(
//                        new TriangularMembershipFunction(minimum + (0.5 * (value - minimum)), maximum - (0.5 * (maximum - value)), value),
//                        universeOfDiscourse);
//                case LESS_THAN -> new FuzzySet(
//                        new TrapezoidMembershipFunction(minimum, minimum, value, maximum),
//                        universeOfDiscourse);
//            };
//        }
//    }


    @Override
    public String getTextualRepresentation() {
        return this.getLabel() + " " + value.intValue();
    }

    @Override
    public Double getQuantified(Double d) {
        return calculateMembershipFunctionValue(d);
    }

    @Override
    public boolean isRelative() {
        return false;
    }
}
