package org.example.model;

public class RelativeQuantifier implements Quantifier {

    private final FuzzySet set;
    private final RelativeQuantifierType type;

    public RelativeQuantifier(RelativeQuantifierType type) {
        this.type = type;
        this.set = type.getFuzzySet();
    }

    public enum RelativeQuantifierType {
        ABOUT_HALF, ALMOST_ALL, ALMOST_NONE, MOST, SOME, ABOUT_QUARTER;

        public String getInTextualForm() {
            return switch (this) {
                case ABOUT_HALF -> "About half";
                case ALMOST_ALL -> "Almost all";
                case ALMOST_NONE -> "Almost none";
                case MOST -> "Most";
                case SOME -> "Some";
                case ABOUT_QUARTER -> "About quarter";
            };
        }

        public FuzzySet getFuzzySet() {
            return switch (this) {
                case ABOUT_HALF -> new FuzzySet(new TriangularMembershipFunction(0.25d, 0.75, 0.5));
                case ALMOST_ALL -> new FuzzySet(new TrapezoidMembershipFunction(0.75d, 1.0, 0.90, 1.0));
                case ALMOST_NONE -> new FuzzySet(new TrapezoidMembershipFunction(0.0d, 0.25, 0.0, 0.1));
                case MOST -> new FuzzySet(new TrapezoidMembershipFunction(0.25d, 1.0, 0.5, 1.0));
                case SOME -> new FuzzySet(new TrapezoidMembershipFunction(0.0d, 0.5, 0.0, 0.25));
                case ABOUT_QUARTER -> new FuzzySet(new TriangularMembershipFunction(0d, 0.5, 0.25));
            };
        }
    }

    @Override
    public String getTextualRepresentation() {
        return this.type.getInTextualForm();
    }

    @Override
    public Double getQuantified(Double d) {
        if (d < 0 || d > 1) throw new IllegalStateException("Number should be between 0 and 1!");
        return set.calculateMembershipFunctionValue(d);
    }

    @Override
    public Boolean isRelative() {
        return true;
    }
}
