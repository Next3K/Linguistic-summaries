package org.example;

import org.example.model.db.Entry;
import org.example.model.sets.*;
import org.example.model.LinguisticVariable;
import org.example.model.functions.TrapezoidMembershipFunction;
import org.example.model.functions.TriangularMembershipFunction;
import org.example.model.quantifiers.AbsoluteQuantifier;
import org.example.model.quantifiers.Quantifier;
import org.example.model.quantifiers.RelativeQuantifier;

import java.util.*;

public class Util {

    public static List<LinguisticVariable> getDefaultLinguisticVariables() {

        UniverseOfDiscourse uni1 = new ContinuousUniverseOfDiscourse(0d, 60d);
        LinguisticVariable minTemperature = new LinguisticVariable(
                "minimum temperature",
                Set.of("cold", "cool", "moderate","warm","hot"),
                uni1,
                Map.of(
                        "cold", new FuzzySet(new TrapezoidMembershipFunction(-1d, 0d, 5d, 15d), uni1),
                        "cool", new FuzzySet(new TriangularMembershipFunction(5d, 15d, 25d), uni1),
                        "moderate", new FuzzySet(new TriangularMembershipFunction(15d, 25d, 35d), uni1),
                        "warm", new FuzzySet(new TriangularMembershipFunction(25d, 35d, 45d), uni1),
                        "hot", new FuzzySet(new TrapezoidMembershipFunction(35d, 45d, 60d, 100d), uni1)),
                LinguisticVariable.SyntacticRuleType.COMPLEX,
                Entry.DatabaseColumn.MIN_TEMPERATURE);

        UniverseOfDiscourse uni2 = new ContinuousUniverseOfDiscourse(10d, 60d);
        LinguisticVariable maxTemperature = new LinguisticVariable(
                "maximum temperature",
                Set.of("cold", "cool", "moderate","warm","hot"),
                uni2,
                Map.of(
                        "cold", new FuzzySet(new TrapezoidMembershipFunction(-1d, 0d, 5d, 15d), uni2),
                        "cool", new FuzzySet(new TriangularMembershipFunction(5d, 15d, 25d), uni2),
                        "moderate", new FuzzySet(new TriangularMembershipFunction(15d, 25d, 35d), uni2),
                        "warm", new FuzzySet(new TriangularMembershipFunction(25d, 35d, 45d), uni2),
                        "hot", new FuzzySet(new TrapezoidMembershipFunction(35d, 45d, 60d, 100d), uni2)),
                LinguisticVariable.SyntacticRuleType.COMPLEX,
                Entry.DatabaseColumn.MAX_TEMPERATURE);

        UniverseOfDiscourse uni3 = new ContinuousUniverseOfDiscourse(0d, 100d);
        LinguisticVariable morningHumidity = new LinguisticVariable(
                "morning humidity",
                Set.of("dry", "comfortable", "dumpy"),
                uni3,
                Map.of(
                        "dry", new FuzzySet(new TrapezoidMembershipFunction(-1d, 0d, 10d, 30d), uni3),
                        "comfortable", new FuzzySet(new TrapezoidMembershipFunction(10d, 30d, 50d, 70d), uni3),
                        "dumpy", new FuzzySet(new TrapezoidMembershipFunction(50d, 70d, 100d, 110d), uni3)),
                LinguisticVariable.SyntacticRuleType.COMPLEX,
                Entry.DatabaseColumn.MORNING_HUMIDITY);

        UniverseOfDiscourse uni4 = new ContinuousUniverseOfDiscourse(0d, 100d);
        LinguisticVariable afternoonHumidity = new LinguisticVariable(
                "afternoon humidity",
                Set.of("dry", "comfortable", "dumpy"),
                uni4,
                Map.of(
                        "dry", new FuzzySet(new TrapezoidMembershipFunction(-1d, 0d, 10d, 30d), uni4),
                        "comfortable", new FuzzySet(new TrapezoidMembershipFunction(10d, 30d, 50d, 70d), uni4),
                        "dumpy", new FuzzySet(new TrapezoidMembershipFunction(50d, 70d, 100d, 110d), uni4)),
                LinguisticVariable.SyntacticRuleType.COMPLEX,
                Entry.DatabaseColumn.AFTERNOON_HUMIDITY);

        UniverseOfDiscourse uni5 = new ContinuousUniverseOfDiscourse(0d, 140d);
        LinguisticVariable windSpeed = new LinguisticVariable(
                "Wind speed",
                Set.of("calm", "breeze", "strong breeze", "near gale", "gale", "strong gale", "storm", "hurricane"),
                uni5,
                Map.of(
                        "calm", new FuzzySet(new TriangularMembershipFunction(-1d, 0d, 10d), uni5),
                        "breeze", new FuzzySet(new TrapezoidMembershipFunction(0d, 10d, 25d, 35d), uni5),
                        "strong breeze", new FuzzySet(new TrapezoidMembershipFunction(25d, 35d, 45d, 55d), uni5),
                        "near gale", new FuzzySet(new TriangularMembershipFunction(45d, 55d, 65d), uni5),
                        "gale", new FuzzySet(new TrapezoidMembershipFunction(55d, 65d, 70d, 80d), uni5),
                        "strong gale", new FuzzySet(new TrapezoidMembershipFunction(70d, 80d, 95d, 105d), uni5),
                        "storm", new FuzzySet(new TrapezoidMembershipFunction(95d, 105d, 115d, 125d), uni5),
                        "hurricane", new FuzzySet(new TrapezoidMembershipFunction(115d, 125d, 140d, 150d), uni5)),
                LinguisticVariable.SyntacticRuleType.SIMPLE,
                Entry.DatabaseColumn.WIND);


        UniverseOfDiscourse uni6 = new ContinuousUniverseOfDiscourse(0d, 80d);
        LinguisticVariable rainfall = new LinguisticVariable(
                "rainfall",
                Set.of("mist to light", "moderate", "heavy", "very heavy", "intense", "extreme"),
                uni6,
                Map.of(
                        "mist to light", new FuzzySet(new TriangularMembershipFunction(-1d, 0d, 10d), uni6),
                        "moderate", new FuzzySet(new TriangularMembershipFunction(0d, 10d, 20d), uni6),
                        "heavy", new FuzzySet(new TrapezoidMembershipFunction(10d, 20d, 25d, 35d), uni6),
                        "very heavy", new FuzzySet(new TrapezoidMembershipFunction(25d, 35d, 40d, 50d), uni6),
                        "intense", new FuzzySet(new TrapezoidMembershipFunction(40d, 50d, 55d, 65d), uni6),
                        "extreme", new FuzzySet(new TrapezoidMembershipFunction(55d, 65d, 70d, 80d), uni6)),
                LinguisticVariable.SyntacticRuleType.COMPLEX,
                Entry.DatabaseColumn.RAINFALL);


        UniverseOfDiscourse uni7 = new ContinuousUniverseOfDiscourse(0d, 14d);
        LinguisticVariable insolation = new LinguisticVariable(
                "insolation",
                Set.of("fully cloudy", "moderately cloudy", "light cloudy", "cloudless"),
                uni7,
                Map.of(
                        "fully cloudy", new FuzzySet(new TriangularMembershipFunction(-1d, 0d, 4d), uni7),
                        "moderately cloudy", new FuzzySet(new TriangularMembershipFunction(0d, 4d, 8d), uni7),
                        "light cloudy", new FuzzySet(new TriangularMembershipFunction(4d, 8d, 12d), uni7),
                        "cloudless", new FuzzySet(new TrapezoidMembershipFunction(8d, 12d, 14d, 16d), uni7)),
                LinguisticVariable.SyntacticRuleType.SIMPLE,
                Entry.DatabaseColumn.INSOLATION);


        UniverseOfDiscourse uni8 = new ContinuousUniverseOfDiscourse(0d, 24d);
        LinguisticVariable evaporation = new LinguisticVariable(
                "evaporation",
                Set.of("light", "gentle", "moderate", "high"),
                uni8,
                Map.of(
                        "light", new FuzzySet(new TrapezoidMembershipFunction(-1d, 0d, 4d, 8d), uni8),
                        "gentle", new FuzzySet(new TrapezoidMembershipFunction(4d, 8d, 10d, 14d), uni8),
                        "moderate", new FuzzySet(new TrapezoidMembershipFunction(10d, 14d, 16d, 20d), uni8),
                        "high", new FuzzySet(new TrapezoidMembershipFunction(16d, 20d, 24d, 30d), uni8)),
                LinguisticVariable.SyntacticRuleType.COMPLEX,
                Entry.DatabaseColumn.EVAPORATION);


        UniverseOfDiscourse uni9 = new ContinuousUniverseOfDiscourse(0d, 40d);
        LinguisticVariable radiation = new LinguisticVariable(
                "Radiation",
                Set.of("light", "moderate", "high", "extreme"),
                uni9,
                Map.of(
                        "light", new FuzzySet(new TrapezoidMembershipFunction(-1d, 0d, 5d, 15d), uni9),
                        "moderate", new FuzzySet(new TriangularMembershipFunction(5d, 15d, 25d), uni9),
                        "high", new FuzzySet(new TriangularMembershipFunction(15d, 25d, 35d), uni9),
                        "extreme", new FuzzySet(new TrapezoidMembershipFunction(25d, 35d, 40d, 50d), uni9)),
                LinguisticVariable.SyntacticRuleType.COMPLEX,
                Entry.DatabaseColumn.RADIATION);


        UniverseOfDiscourse uni10 = new ContinuousUniverseOfDiscourse(0d, 14d);
        LinguisticVariable evapotranspiration = new LinguisticVariable(
                "evapotranspiration",
                Set.of("light", "moderate", "high", "extreme"),
                uni10,
                Map.of(
                        "light", new FuzzySet(new TrapezoidMembershipFunction(-1d, 0d, 3d, 5d), uni10),
                        "moderate", new FuzzySet(new TriangularMembershipFunction(3d, 5d, 7d), uni10),
                        "high", new FuzzySet(new TrapezoidMembershipFunction(5d, 7d, 8d, 10d), uni10),
                        "extreme", new FuzzySet(new TrapezoidMembershipFunction(8d, 10d, 14d, 20d), uni10)),
                LinguisticVariable.SyntacticRuleType.COMPLEX,
                Entry.DatabaseColumn.EVAPOTRANSPIRATION);



        return List.of(
                minTemperature,
                maxTemperature,
                morningHumidity,
                afternoonHumidity,
                windSpeed,
                rainfall,
                insolation,
                evaporation,
                radiation,
                evapotranspiration);
    }

    public static List<Entry> loadFromDatabase(Set<Entry.DatabaseColumn> columnsOfInterest) {
        return new ArrayList<>(14_854);
    }

    // load entire database
    public static List<Entry> loadFromDatabase() {
        return new ArrayList<>(14_854);
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
        UniverseOfDiscourse universe = new DiscreteUniverseOfDiscourse(0, 15000);

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

    public static Map<Entry.DatabaseColumn, List<LabeledFuzzySet>> chooseAttributesAndTheirSummarizers() {
        return new HashMap<>();
    }

    public static <T> List<Set<T>> generateSubsets(List<T> set) {
        List<Set<T>> subsets = new ArrayList<>(1 << set.size());
        generateSubsetsHelper(set, new ArrayList<>(), subsets, 0);
        return subsets;
    }

    private static <T> void generateSubsetsHelper(List<T> set,
                                                  List<T> currentSubset,
                                                  List<Set<T>> subsets,
                                                  int startIndex) {
        if (!currentSubset.isEmpty()) {
            subsets.add(new HashSet<>(currentSubset));
        }

        for (int i = startIndex; i < set.size(); i++) {
            currentSubset.add(set.get(i));
            generateSubsetsHelper(set, currentSubset, subsets, i + 1);
            currentSubset.remove(currentSubset.size() - 1);
        }
    }

}
