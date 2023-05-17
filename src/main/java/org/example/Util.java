package org.example;

import org.example.model.*;
import org.example.model.functions.TrapezoidMembershipFunction;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static List<Statement> generateStatements(List<Quantifier> absoluteQuantifiers,
                                                     List<Quantifier> relativeQuantifiers,
                                                     List<FuzzySet> chosenSummarizers) {
        return new ArrayList<>(200);
    }
    public static List<Entry> loadFromDatabase() {
        return new ArrayList<>(15000);
    }

    public static List<Quantifier> loadDefaultRelativeQuantifiers() {
        Quantifier almostNone = new RelativeQuantifier(RelativeQuantifier.RelativeQuantifierType.ALMOST_NONE);
        Quantifier few = new RelativeQuantifier(RelativeQuantifier.RelativeQuantifierType.FEW);
        Quantifier aboutQuarter = new RelativeQuantifier(RelativeQuantifier.RelativeQuantifierType.ABOUT_QUARTER);
        Quantifier some = new RelativeQuantifier(RelativeQuantifier.RelativeQuantifierType.SOME);
        Quantifier aboutHalf = new RelativeQuantifier(RelativeQuantifier.RelativeQuantifierType.ABOUT_HALF);
        Quantifier aboutThreeQuarters =
                new RelativeQuantifier(RelativeQuantifier.RelativeQuantifierType.ABOUT_THREE_QUARTERS);
        Quantifier many = new RelativeQuantifier(RelativeQuantifier.RelativeQuantifierType.MANY);
        Quantifier almostAll = new RelativeQuantifier(RelativeQuantifier.RelativeQuantifierType.ALMOST_ALL);

        return new ArrayList<>(List.of(almostNone, few, aboutQuarter,
                some, aboutHalf, aboutThreeQuarters, many, almostAll));
    }

    public static List<Quantifier> loadDefaultAbsoluteQuantifiers() {
        UniverseOfDiscourse universe = new UniverseOfDiscourse(0d, 15_000d);

        Quantifier twoEightZero = new AbsoluteQuantifier(
                "About",
                280d,
                new FuzzySet(new TrapezoidMembershipFunction(-1, 0d, 450d, 700d), universe));


        Quantifier oneTwoZeroZero = new AbsoluteQuantifier(
                "About",
                1200d,
                new FuzzySet(new TrapezoidMembershipFunction(400d, 900d, 1550d, 2050d), universe));


        Quantifier threeSixZeroZero = new AbsoluteQuantifier(
                "About",
                3600d,
                new FuzzySet(new TrapezoidMembershipFunction(1750d, 2750d, 4450d, 5450d), universe));

        Quantifier sevenTwoZeroZero = new AbsoluteQuantifier(
                "About",
                7200d,
                new FuzzySet(new TrapezoidMembershipFunction(4500, 6500d, 8100d, 10100d), universe));

        Quantifier oneZeroEightZeroZero = new AbsoluteQuantifier(
                "About",
                10800d,
                new FuzzySet(new TrapezoidMembershipFunction(8200, 10200d, 11800d, 13800d), universe));

        Quantifier oneFourFourZeroZero = new AbsoluteQuantifier(
                "About",
                14600d,
                new FuzzySet(new TrapezoidMembershipFunction(2400, 3400, 1500, 1600), universe));


        return new ArrayList<>(List.of(twoEightZero, oneTwoZeroZero, threeSixZeroZero,
                sevenTwoZeroZero, oneZeroEightZeroZero, oneFourFourZeroZero));
    }

}
