package org.example.model.quantifiers;

import org.example.model.functions.MembershipFunction;
import org.example.model.functions.TrapezoidMembershipFunction;
import org.example.model.functions.TriangularMembershipFunction;
import org.example.model.sets.UniverseOfDiscourseTwo;


public class RelativeQuantifier extends Quantifier {

    // custom relative quantifiers
    public RelativeQuantifier(String label,
                              MembershipFunction membershipFunction,
                              UniverseOfDiscourseTwo universeOfDiscourse) {
        super(label, membershipFunction, universeOfDiscourse);
    }

    // default relative quantifiers
    public RelativeQuantifier(RelativeQuantifierType type) {
        super(type.getInTextualForm(), type.getFunction(), type.getUniverse());
    }

    public enum RelativeQuantifierType {
        ALMOST_NONE, FEW, ABOUT_QUARTER, SOME, ABOUT_HALF, ABOUT_THREE_QUARTERS, MANY, ALMOST_ALL;

        private static final UniverseOfDiscourseTwo universeOfDiscourse = new UniverseOfDiscourseTwo(0d, 1d);

        public String getInTextualForm() {
            return switch (this) {
                case ALMOST_NONE -> "Almost none";
                case FEW -> "Few";
                case ABOUT_QUARTER -> "About quarter";
                case SOME -> "Some";
                case ABOUT_HALF -> "About half";
                case ABOUT_THREE_QUARTERS -> "About three quarters";
                case MANY -> "Many";
                case ALMOST_ALL -> "Almost all";
            };
        }

        public MembershipFunction getFunction() {
            return switch (this) {
                case ALMOST_NONE -> new TriangularMembershipFunction(-1d, 0d, 0.1d);
                case FEW -> new TriangularMembershipFunction(0d, 0.1d, 0.2d);
                case ABOUT_QUARTER -> new TriangularMembershipFunction(0.1d, 0.2d, 0.3d);
                case SOME -> new TrapezoidMembershipFunction(0.2d, 0.3d, 0.35d, 0.45d);
                case ABOUT_HALF -> new TrapezoidMembershipFunction(0.3d, 0.45d, 0.55d, 0.7d);
                case ABOUT_THREE_QUARTERS -> new TriangularMembershipFunction(0.6d, 0.7d, 0.8d);
                case MANY -> new TrapezoidMembershipFunction(0.7d, 0.85d, 1d, 1.2d);
                case ALMOST_ALL -> new TrapezoidMembershipFunction(0.85d, 0.95d, 1d, 1.2d);
            };
        }

        public UniverseOfDiscourseTwo getUniverse() {
            return universeOfDiscourse;
        }
    }

    @Override
    public String getTextualRepresentation() {
        return this.getLabel();
    }

    @Override
    public Double getQuantified(Double d) {
        return membershipFunction.evaluate(d);
    }

    @Override
    public boolean isRelative() {
        return true;
    }
}
