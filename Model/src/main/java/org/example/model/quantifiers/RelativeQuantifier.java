package org.example.model.quantifiers;

import org.example.model.sets.ContinuousUniverseOfDiscourse;
import org.example.model.sets.FuzzySet;
import org.example.model.sets.UniverseOfDiscourse;
import org.example.model.functions.TrapezoidMembershipFunction;
import org.example.model.functions.TriangularMembershipFunction;


public class RelativeQuantifier extends Quantifier {

    private final FuzzySet set;
    private final String textualForm;

    // standard relative quantifiers
    public RelativeQuantifier(RelativeQuantifierType type) {
        super(type.getFuzzySet().getMembershipFunction(), type.getFuzzySet().getUniverseOfDiscourse());
        this.set = type.getFuzzySet();
        this.textualForm = type.getInTextualForm();
    }

    // custom relative quantifiers
    public RelativeQuantifier(String label, FuzzySet fuzzySet) {
        super(fuzzySet.getMembershipFunction(), fuzzySet.getUniverseOfDiscourse());
        this.set = fuzzySet;
        this.textualForm = label;
    }

    public enum RelativeQuantifierType {
        ALMOST_NONE, FEW, ABOUT_QUARTER, SOME, ABOUT_HALF, ABOUT_THREE_QUARTERS, MANY, ALMOST_ALL;

        private final UniverseOfDiscourse universeOfDiscourse = new ContinuousUniverseOfDiscourse(0d, 1d);

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

        public FuzzySet getFuzzySet() {
            return switch (this) {
                case ALMOST_NONE -> new FuzzySet(
                        new TriangularMembershipFunction(-1d, 0d, 0.1d),
                        universeOfDiscourse);
                case FEW -> new FuzzySet(
                        new TriangularMembershipFunction(0d, 0.1d, 0.2d),
                        universeOfDiscourse);
                case ABOUT_QUARTER -> new FuzzySet(
                        new TriangularMembershipFunction(0.1d, 0.2d, 0.3d),
                        universeOfDiscourse);
                case SOME -> new FuzzySet(
                        new TrapezoidMembershipFunction(0.2d, 0.3d, 0.35d, 0.45d),
                        universeOfDiscourse);
                case ABOUT_HALF -> new FuzzySet(
                        new TrapezoidMembershipFunction(0.3d, 0.45d, 0.55d, 0.7d),
                        universeOfDiscourse);
                case ABOUT_THREE_QUARTERS -> new FuzzySet(
                        new TriangularMembershipFunction(0.6d, 0.7d, 0.8d),
                        universeOfDiscourse);
                case MANY -> new FuzzySet(
                        new TrapezoidMembershipFunction(0.7d, 0.85d, 1d, 1.2d),
                        universeOfDiscourse);
                case ALMOST_ALL -> new FuzzySet(
                        new TrapezoidMembershipFunction(0.85d, 0.95d, 1d, 1.2d),
                        universeOfDiscourse);
            };
        }
    }

    @Override
    public String getTextualRepresentation() {
        return this.textualForm;
    }

    @Override
    public Double getQuantified(Double d) {
        return set.calculateMembershipFunctionValue(d);
    }

    @Override
    public Boolean isRelative() {
        return true;
    }
}
