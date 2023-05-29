package org.example.model.sets;

import org.example.model.LinguisticVariable;
import org.example.model.db.Entry;
import org.example.model.functions.MembershipFunction;
import lombok.Getter;

import java.util.Random;
import java.util.function.Function;

@Getter
public class FuzzySet extends NonFuzzySet {

    // trzeba sprawdzać czy przestrzeń rozważan jest niepusta
    private static final Random random = new Random();

    private final String label;
    private final Entry.DatabaseColumn column;
    private final Function<String, String> descriptionProducer;

    public FuzzySet(String label,
                    Entry.DatabaseColumn column,
                    Function<String, String> descriptionProducer,
                    MembershipFunction membershipFunction,
                    UniverseOfDiscourse universe) {
        super(membershipFunction, universe);
        this.column = column;
        this.descriptionProducer = descriptionProducer;
        this.label = label;
    }

    public FuzzySet(String label,
                    Entry.DatabaseColumn column,
                    MembershipFunction membershipFunction,
                    UniverseOfDiscourse universe) {
        super(membershipFunction, universe);
        this.column = column;
        this.descriptionProducer = LinguisticVariable::complexDescription;
        this.label = label;
    }

    public Double calculateMembershipFunctionValue(double x) {
        return membershipFunction.evaluate(x);
    }

    public boolean isNormal() {
        return membershipFunction.getMaxValue() == 1;
    }

    public boolean isConvex() {
        double min = membershipFunction.getUniverseOfDiscourse().getMinimum();
        double max = membershipFunction.getUniverseOfDiscourse().getMinimum();
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
        return this.getSupport()
                .getMembershipFunction()
                .getUniverseOfDiscourse()
                .calculateMeasure()
                .doubleValue() /
                this.membershipFunction
                        .getUniverseOfDiscourse()
                        .calculateMeasure()
                        .doubleValue();
    }

    public NonFuzzySet getSupport() {
        return membershipFunction.getSupport();
    }

    public NonFuzzySet getAlfaCut(double y) {
        return membershipFunction.getAlfaCut(y);
    }

    public double getCardinality() {
        double sum = 0;
        UniverseOfDiscourse universeOfDiscourse = this.membershipFunction.getUniverseOfDiscourse();
        for (int i = (int) universeOfDiscourse.getMinimum(); i < (int) universeOfDiscourse.getMaximum(); i++) {
            sum += membershipFunction.evaluate((double) i);
        }
        return sum;
    }

    public double getCardinalityLikeMeasure() {
        return membershipFunction.getIntegral();
    }

    public getTextualRepresentation() {
        return descriptionProducer.apply(label);
    }
}
