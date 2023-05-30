package org.example.model.quantifiers;

import org.example.model.functions.MembershipShape;
import org.example.model.functions.TrapezoidShape;
import org.example.model.functions.TriangularShape;
import org.example.model.sets.UniverseOfDiscourse;


public class RelativeQuantifier extends Quantifier {

    // custom relative quantifiers
    public RelativeQuantifier(String label,
                              MembershipShape membershipFunction,
                              UniverseOfDiscourse universeOfDiscourse) {
        super(label, membershipFunction, universeOfDiscourse);
    }

    // default relative quantifiers
    public RelativeQuantifier(RelativeQuantifierType type) {
        super(type.getInTextualForm(), type.getFunction(), type.getUniverse());
    }

    public enum RelativeQuantifierType {
        ALMOST_NONE, FEW, ABOUT_QUARTER, SOME, ABOUT_HALF, ABOUT_THREE_QUARTERS, MANY, ALMOST_ALL;

        private static final UniverseOfDiscourse universeOfDiscourse = new UniverseOfDiscourse(0d, 1d);

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

        public MembershipShape getFunction() {
            return switch (this) {
                case ALMOST_NONE -> new TriangularShape(-1d, 0d, 0.1d);
                case FEW -> new TriangularShape(0d, 0.1d, 0.2d);
                case ABOUT_QUARTER -> new TriangularShape(0.1d, 0.2d, 0.3d);
                case SOME -> new TrapezoidShape(0.2d, 0.3d, 0.35d, 0.45d);
                case ABOUT_HALF -> new TrapezoidShape(0.3d, 0.45d, 0.55d, 0.7d);
                case ABOUT_THREE_QUARTERS -> new TriangularShape(0.6d, 0.7d, 0.8d);
                case MANY -> new TrapezoidShape(0.7d, 0.85d, 1d, 1.2d);
                case ALMOST_ALL -> new TrapezoidShape(0.85d, 0.95d, 1d, 1.2d);
            };
        }

        public UniverseOfDiscourse getUniverse() {
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
