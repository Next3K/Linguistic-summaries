package org.example.model.sets;

import org.example.model.db.Entry;
import org.example.model.functions.MembershipShape;
import lombok.Getter;

import java.util.List;
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
        double min = universeOfDiscourse.getNonFuzzySet().getMin().doubleValue();
        double max = universeOfDiscourse.getNonFuzzySet().getMax().doubleValue();
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
        double v = this.getSupport().calculateSize();
        double v1 = universeOfDiscourse.getNonFuzzySet().calculateSize();
        double v2 = v / v1;
        return v2;
    }

    public NonFuzzySet getSupport() {
        return membershipFunction.getSupport(universeOfDiscourse);
    }

    public NonFuzzySet getAlfaCut(double y) {
        return membershipFunction.getAlfaCut(universeOfDiscourse, y);
    }

    public double getCardinality(List<Entry> entries) {
        double sum = 0;
        for (var entry : entries) {
            sum += getMembershipFunctionValueFor(entry);
        }
        return sum;
    }

    public double getCardinalityLikeMeasure() {
        double a = universeOfDiscourse.getNonFuzzySet().getMinimum().doubleValue();
        double b = universeOfDiscourse.getNonFuzzySet().getMaximum().doubleValue();
        return membershipFunction.getIntegral(a, b);
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
        return this.label + " " + this.column.variableName();
    }

    public enum DescriptionType {
        SIMPLE, COMPLEX
    }
}
