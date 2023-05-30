package org.example.model.sets;

import org.example.model.db.Entry;
import org.example.model.functions.MembershipShape;
import lombok.Getter;

import java.util.Random;


@Getter
public class FuzzySet {

    private static final Random random = new Random();
    private final String label;
    private final Entry.DatabaseColumn column;
    private final DescriptionType descriptionType;
    protected final MembershipShape membershipFunction;
    protected final UniverseOfDiscourse universeOfDiscourse;

    public FuzzySet(String label,
                    Entry.DatabaseColumn column,
                    MembershipShape membershipFunction,
                    UniverseOfDiscourse universeOfDiscourse) {
        this.membershipFunction = membershipFunction;
        this.column = column;
        this.label = label;
        this.universeOfDiscourse = universeOfDiscourse;
        this.descriptionType = DescriptionType.COMPLEX;
    }

    public FuzzySet(String label,
                    Entry.DatabaseColumn column,
                    MembershipShape membershipFunction,
                    UniverseOfDiscourse universeOfDiscourse,
                    DescriptionType descriptionProducer) {
        this.membershipFunction = membershipFunction;
        this.column = column;
        this.descriptionType = descriptionProducer;
        this.label = label;
        this.universeOfDiscourse = universeOfDiscourse;
    }

    public Double calculateMembershipFunctionValue(double x) {
        return membershipFunction.evaluate(x);
    }

    public Double getMembershipFunctionValueFor(Entry e) {
        return this.calculateMembershipFunctionValue(e.getValues().get(this.column));
    }

    public boolean isNormal() {
        return membershipFunction.getMaxValue() == 1;
    }

    public boolean isConvex() {
        double min = membershipFunction.getUniverseOfDiscourse().getNonFuzzySet().getMin().doubleValue();
        double max = membershipFunction.getUniverseOfDiscourse().getNonFuzzySet().getMax().doubleValue();
        double diff = max - min;
        for (int i = 0; i < 50; i++) {
            double a = min + random.nextDouble() * diff;
            double b = min + random.nextDouble() * diff;
            double mid = (a + b) / 2.0d;
            if (calculateMembershipFunctionValue(mid) <
                    Math.min(calculateMembershipFunctionValue(a), calculateMembershipFunctionValue(b))) {
                return false;
            }
        }
        return true;
    }

    public double getDegreeOfFuzziness() {
        return this.getSupport().calculateSize() /
                this.membershipFunction.getUniverseOfDiscourse().getNonFuzzySet().calculateSize();
    }

    public NonFuzzySet getSupport() {
        return membershipFunction.getSupport();
    }

    public NonFuzzySet getAlfaCut(double y) {
        return membershipFunction.getAlfaCut(y);
    }

    public double getCardinality() {
        double sum = 0;
        UniverseOfDiscourse uni = this.membershipFunction.getUniverseOfDiscourse();
        for (int i = uni.getNonFuzzySet().getMin().intValue(); i < uni.getNonFuzzySet().getMax().intValue(); i++) {
            sum += membershipFunction.evaluate((double) i);
        }
        return sum;
    }

    public double getCardinalityLikeMeasure() {
        return membershipFunction.getIntegral();
    }

    public String getTextualRepresentation() {
        return switch (descriptionType) {
            case SIMPLE -> simpleDescription();
            case COMPLEX -> complexDescription();
        };
    }

    // example: "Student is/have: buffed"
    private String simpleDescription() {
        return this.label;
    }

    // example: "Student is/have: strongman musculature"
    private String complexDescription() {
        return this.column.variableName() + " " + this.label;
    }

    public enum DescriptionType {
        SIMPLE, COMPLEX
    }
}
