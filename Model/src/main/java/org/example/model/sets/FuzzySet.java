package org.example.model.sets;

import org.example.model.db.Entry;
import org.example.model.functions.MembershipShape;
import lombok.Getter;

import java.util.List;
import java.util.Random;


@Getter
public class FuzzySet {

    private final String label;
    private final Entry.DatabaseColumn databaseColumn;
    private final DescriptionType descriptionType;
    protected final MembershipShape membershipShape;
    protected final UniverseOfDiscourse universeOfDiscourse;

    public FuzzySet(String label,
                    Entry.DatabaseColumn column,
                    MembershipShape membershipFunction,
                    UniverseOfDiscourse universeOfDiscourse) {
        this.membershipShape = membershipFunction;
        this.databaseColumn = column;
        this.label = label;
        this.universeOfDiscourse = universeOfDiscourse;
        this.descriptionType = DescriptionType.COMPLEX;
    }

    public FuzzySet(String label,
                    Entry.DatabaseColumn column,
                    MembershipShape membershipFunction,
                    UniverseOfDiscourse universeOfDiscourse,
                    DescriptionType descriptionType) {
        this.membershipShape = membershipFunction;
        this.databaseColumn = column;
        this.descriptionType = descriptionType;
        this.label = label;
        this.universeOfDiscourse = universeOfDiscourse;
    }


    public Double evaluateFor(Entry entry) {
        return this.evaluateFor(entry.getValues().get(this.databaseColumn));
    }

    public Double evaluateFor(double value) {
        return (universeOfDiscourse.getNonFuzzySet().isValueInTheSet(value)) ?  membershipShape.evaluate(value) : 0;
    }

    public boolean isNormal() {
        return membershipShape.getMaxValue() == 1;
    }

    public boolean isConvex() {
        Random random = new Random();
        double min = universeOfDiscourse.getNonFuzzySet().getMin().doubleValue();
        double max = universeOfDiscourse.getNonFuzzySet().getMax().doubleValue();
        double diff = max - min;
        for (int i = 0; i < 50; i++) {
            double a = min + random.nextDouble() * diff;
            double b = min + random.nextDouble() * diff;
            double mid = (a + b) / 2.0d;
            if (evaluateFor(mid) <
                    Math.min(evaluateFor(a), evaluateFor(b))) {
                return false;
            }
        }
        return true;
    }

    public double getDegreeOfFuzziness() {
        double v = this.getSupport().evaluateSize();
        double v1 = universeOfDiscourse.getNonFuzzySet().evaluateSize();
        return v / v1;
    }

    public double getDegreeOfFuzziness(List<Entry> entries) {
        NonFuzzySet support = this.getSupport();
        double count = entries.stream().filter(e ->
                support.isValueInTheSet(e.getValues().get(this.databaseColumn))).count();
        double whole = entries.stream().filter(e ->
                universeOfDiscourse.getNonFuzzySet().isValueInTheSet(e.getValues().get(this.databaseColumn))).count();
        return count / whole;
    }

    public NonFuzzySet getSupport() {
        return membershipShape.getSupport(universeOfDiscourse);
    }

    public NonFuzzySet getAlfaCut(double y) {
        return membershipShape.getAlfaCut(universeOfDiscourse, y);
    }

    public double getCardinality(List<Entry> entries) {
        double sum = 0;
        for (var entry : entries) {
            sum += evaluateFor(entry);
        }
        return sum;
    }

    public double getCardinalityLikeMeasure() {
        double a = universeOfDiscourse.getNonFuzzySet().getMinimum().doubleValue();
        double b = universeOfDiscourse.getNonFuzzySet().getMaximum().doubleValue();
        return membershipShape.getIntegral(a, b);
    }


    public String getTextualRepresentation() {
        return switch (descriptionType) {
            case SIMPLE -> generateSimpleDescription();
            case COMPLEX -> generateComplexDescription();
        };
    }

    // example: "Student is/have: buffed"
    private String generateSimpleDescription() {
        return this.label;
    }

    // example: "Student is/have: strongman musculature"
    private String generateComplexDescription() {
        return this.label + " " + this.databaseColumn.variableName();
    }

    public enum DescriptionType {
        SIMPLE, COMPLEX
    }
}
