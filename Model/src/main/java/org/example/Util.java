package org.example;

import org.example.model.db.Connect;
import org.example.model.db.Entry;
import org.example.model.functions.GaussianMembershipFunction;
import org.example.model.quantifiers.AbsoluteQuantifier;
import org.example.model.sets.*;
import org.example.model.LinguisticVariable;
import org.example.model.functions.TrapezoidMembershipFunction;
import org.example.model.functions.TriangularMembershipFunction;
import org.example.model.quantifiers.Quantifier;
import org.example.model.quantifiers.RelativeQuantifier;

import java.util.*;

public class Util {

    public static List<LinguisticVariable> getDefaultLinguisticVariables() {

        UniverseOfDiscourseTwo uni1 = new UniverseOfDiscourseTwo(0d, 60d);
        LinguisticVariable minTemperature = new LinguisticVariable(
                Set.of(new FuzzySet(
                                "cold",
                                Entry.DatabaseColumn.MIN_TEMPERATURE,
                                new TrapezoidMembershipFunction(-1d, 0d, 5d, 15d),
                                uni1),
                        new FuzzySet("cool",
                                Entry.DatabaseColumn.MIN_TEMPERATURE,
                                new TriangularMembershipFunction(5d, 15d, 25d),
                                uni1),
                        new FuzzySet(
                                "moderate",
                                Entry.DatabaseColumn.MIN_TEMPERATURE,
                                new TriangularMembershipFunction(15d, 25d, 35d),
                                uni1),
                        new FuzzySet(
                                "warm",
                                Entry.DatabaseColumn.MIN_TEMPERATURE,
                                new TriangularMembershipFunction(25d, 35d, 45d),
                                uni1),
                        new FuzzySet(
                                "hot",
                                Entry.DatabaseColumn.MIN_TEMPERATURE,
                                new TrapezoidMembershipFunction(35d, 45d, 60d, 100d),
                                uni1)),
                uni1);


        UniverseOfDiscourseTwo uni2 = new UniverseOfDiscourseTwo(0d, 60d);
        LinguisticVariable maxTemperature = new LinguisticVariable(
                Set.of(new FuzzySet(
                                "cold",
                                Entry.DatabaseColumn.MAX_TEMPERATURE,
                                new TrapezoidMembershipFunction(-1d, 0d, 5d, 15d),
                                uni2),
                        new FuzzySet("cool",
                                Entry.DatabaseColumn.MAX_TEMPERATURE,
                                new TriangularMembershipFunction(5d, 15d, 25d),
                                uni2),
                        new FuzzySet(
                                "moderate",
                                Entry.DatabaseColumn.MAX_TEMPERATURE,
                                new TriangularMembershipFunction(15d, 25d, 35d),
                                uni2),
                        new FuzzySet(
                                "warm",
                                Entry.DatabaseColumn.MAX_TEMPERATURE,
                                new TriangularMembershipFunction(25d, 35d, 45d),
                                uni2),
                        new FuzzySet(
                                "hot",
                                Entry.DatabaseColumn.MAX_TEMPERATURE,
                                new TrapezoidMembershipFunction(35d, 45d, 60d, 100d),
                                uni2)),
                uni2);

        UniverseOfDiscourseTwo uni3 = new UniverseOfDiscourseTwo(0d, 100d);
        LinguisticVariable morningHumidity = new LinguisticVariable(
                Set.of(new FuzzySet(
                                "dry",
                                Entry.DatabaseColumn.MORNING_HUMIDITY,
                                new TrapezoidMembershipFunction(-1d, 0d, 10d, 30d),
                                uni3),
                        new FuzzySet("comfortable",
                                Entry.DatabaseColumn.MORNING_HUMIDITY,
                                new TrapezoidMembershipFunction(10d, 30d, 50d, 70d),
                                uni3),
                        new FuzzySet(
                                "dumpy",
                                Entry.DatabaseColumn.MORNING_HUMIDITY,
                                new TrapezoidMembershipFunction(50d, 70d, 100d, 110d),
                                uni3)),
                uni3);

        UniverseOfDiscourseTwo uni4 = new UniverseOfDiscourseTwo(0d, 100d);
        LinguisticVariable afternoonHumidity = new LinguisticVariable(
                Set.of(new FuzzySet(
                                "dry",
                                Entry.DatabaseColumn.AFTERNOON_HUMIDITY,
                                new TrapezoidMembershipFunction(-1d, 0d, 10d, 30d),
                                uni4),
                        new FuzzySet("comfortable",
                                Entry.DatabaseColumn.AFTERNOON_HUMIDITY,
                                new TrapezoidMembershipFunction(10d, 30d, 50d, 70d),
                                uni4),
                        new FuzzySet(
                                "dumpy",
                                Entry.DatabaseColumn.AFTERNOON_HUMIDITY,
                                new TrapezoidMembershipFunction(50d, 70d, 100d, 110d),
                                uni4)),
                uni4);



        UniverseOfDiscourseTwo uni5 = new UniverseOfDiscourseTwo(0d, 140d);
        LinguisticVariable windSpeed = new LinguisticVariable(
                Set.of(new FuzzySet(
                                "cold",
                                Entry.DatabaseColumn.WIND,
                                new TriangularMembershipFunction(-1d, 0d, 10d),
                                uni5),
                        new FuzzySet("cool",
                                Entry.DatabaseColumn.WIND,
                                new TrapezoidMembershipFunction(0d, 10d, 25d, 35d),
                                uni5),
                        new FuzzySet(
                                "moderate",
                                Entry.DatabaseColumn.WIND,
                                new TrapezoidMembershipFunction(25d, 35d, 45d, 55d),
                                uni5),
                        new FuzzySet(
                                "warm",
                                Entry.DatabaseColumn.WIND,
                                new TriangularMembershipFunction(45d, 55d, 65d),
                                uni5),
                        new FuzzySet(
                                "moderate",
                                Entry.DatabaseColumn.WIND,
                                new TrapezoidMembershipFunction(55d, 65d, 70d, 80d),
                                uni5),
                        new FuzzySet(
                                "warm",
                                Entry.DatabaseColumn.WIND,
                                new TrapezoidMembershipFunction(70d, 80d, 95d, 105d),
                                uni5),
                        new FuzzySet(
                                "warm",
                                Entry.DatabaseColumn.WIND,
                                new TrapezoidMembershipFunction(95d, 105d, 115d, 125d),
                                uni5),
                        new FuzzySet(
                                "hot",
                                Entry.DatabaseColumn.WIND,
                                new TrapezoidMembershipFunction(115d, 125d, 140d, 150d),
                                uni5)),
                uni5);

        UniverseOfDiscourseTwo uni6 = new UniverseOfDiscourseTwo(0d, 300d);
        LinguisticVariable rainfall = new LinguisticVariable(
                Set.of(new FuzzySet(
                                "mist to light",
                                Entry.DatabaseColumn.RAINFALL,
                                new TriangularMembershipFunction(-1d, 0d, 10d),
                                uni6),
                        new FuzzySet("moderate",
                                Entry.DatabaseColumn.RAINFALL,
                                new TriangularMembershipFunction(0d, 10d, 20d),
                                uni6),
                        new FuzzySet(
                                "heavy",
                                Entry.DatabaseColumn.RAINFALL,
                                new TrapezoidMembershipFunction(10d, 20d, 25d, 35d),
                                uni6),
                        new FuzzySet(
                                "very heavy",
                                Entry.DatabaseColumn.RAINFALL,
                                new TrapezoidMembershipFunction(25d, 35d, 40d, 50d),
                                uni6),
                        new FuzzySet(
                                "intense",
                                Entry.DatabaseColumn.RAINFALL,
                                new TrapezoidMembershipFunction(40d, 50d, 55d, 65d),
                                uni6),
                        new FuzzySet(
                                "extreme",
                                Entry.DatabaseColumn.RAINFALL,
                                new TrapezoidMembershipFunction(55d, 65d, 70d, 80d),
                                uni6)),
                uni6);

        UniverseOfDiscourseTwo uni7 = new UniverseOfDiscourseTwo(0d, 14d);
        LinguisticVariable insolation = new LinguisticVariable(
                Set.of(new FuzzySet(
                                "fully cloudy",
                                Entry.DatabaseColumn.INSOLATION,
                                new TriangularMembershipFunction(-1d, 0d, 4d),
                                uni7),
                        new FuzzySet("moderately cloudy",
                                Entry.DatabaseColumn.INSOLATION,
                                new TriangularMembershipFunction(0d, 4d, 8d),
                                uni7),
                        new FuzzySet(
                                "lightly cloudy",
                                Entry.DatabaseColumn.INSOLATION,
                                new TriangularMembershipFunction(4d, 8d, 12d),
                                uni7),
                        new FuzzySet(
                                "cloudless",
                                Entry.DatabaseColumn.INSOLATION,
                                new TrapezoidMembershipFunction(8d, 12d, 14d, 16d),
                                uni7)),
                uni7);


        UniverseOfDiscourseTwo uni8 = new UniverseOfDiscourseTwo(0d, 24d);
        LinguisticVariable evaporation = new LinguisticVariable(
                Set.of(new FuzzySet(
                                "light",
                                Entry.DatabaseColumn.EVAPORATION,
                                new TrapezoidMembershipFunction(-1d, 0d, 4d, 8d),
                                uni8),
                        new FuzzySet("gentle",
                                Entry.DatabaseColumn.EVAPORATION,
                                new TrapezoidMembershipFunction(4d, 8d, 10d, 14d),
                                uni8),
                        new FuzzySet(
                                "moderate",
                                Entry.DatabaseColumn.EVAPORATION,
                                new TrapezoidMembershipFunction(10d, 14d, 16d, 20d),
                                uni8),
                        new FuzzySet(
                                "high",
                                Entry.DatabaseColumn.EVAPORATION,
                                new TrapezoidMembershipFunction(16d, 20d, 24d, 30d),
                                uni8)),
                uni8);

        UniverseOfDiscourseTwo uni9 = new UniverseOfDiscourseTwo(0d, 40d);
        LinguisticVariable radiation = new LinguisticVariable(
                Set.of(new FuzzySet(
                                "light",
                                Entry.DatabaseColumn.RADIATION,
                                new TrapezoidMembershipFunction(-1d, 0d, 5d, 15d),
                                uni9),
                        new FuzzySet("moderate",
                                Entry.DatabaseColumn.RADIATION,
                                new TriangularMembershipFunction(5d, 15d, 25d),
                                uni9),
                        new FuzzySet(
                                "high",
                                Entry.DatabaseColumn.RADIATION,
                                new TriangularMembershipFunction(15d, 25d, 35d),
                                uni9),
                        new FuzzySet(
                                "extreme",
                                Entry.DatabaseColumn.RADIATION,
                                new TrapezoidMembershipFunction(25d, 35d, 40d, 50d),
                                uni9)),
                uni9);


        UniverseOfDiscourseTwo uni10 = new UniverseOfDiscourseTwo(0d, 40d);
        LinguisticVariable evapotranspiration = new LinguisticVariable(
                Set.of(new FuzzySet(
                                "light",
                                Entry.DatabaseColumn.EVAPOTRANSPIRATION,
                                new TrapezoidMembershipFunction(-1d, 0d, 3d, 5d),
                                uni10),
                        new FuzzySet("moderate",
                                Entry.DatabaseColumn.EVAPOTRANSPIRATION,
                                new TriangularMembershipFunction(3d, 5d, 7d),
                                uni10),
                        new FuzzySet(
                                "high",
                                Entry.DatabaseColumn.EVAPOTRANSPIRATION,
                                new TrapezoidMembershipFunction(5d, 7d, 8d, 10d),
                                uni10),
                        new FuzzySet(
                                "extreme",
                                Entry.DatabaseColumn.EVAPOTRANSPIRATION,
                                new TrapezoidMembershipFunction(8d, 10d, 14d, 20d),
                                uni10)),
                uni10);



        return new ArrayList<>(
                List.of(minTemperature,
                        maxTemperature,
                        morningHumidity,
                        afternoonHumidity,
                        windSpeed,
                        rainfall,
                        insolation,
                        evaporation,
                        radiation,
                        evapotranspiration));
    }

    public static List<Entry> loadFromDatabase(Set<Entry.DatabaseColumn> columnsOfInterest) {
        Connect dbase = new Connect();

        List<Entry> entries = dbase.selectAllRows();
        return entries;
    }

    // load entire database
    public static List<Entry> loadFromDatabase() {
        Connect dbase = new Connect();
        List<Entry> entries = dbase.selectAllRows();
        return entries;
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
        UniverseOfDiscourseTwo universe = new UniverseOfDiscourseTwo(0, 14_854);

        String about = "About";

        Quantifier twoEightZero = new AbsoluteQuantifier(
                280d,
                about,
                new GaussianMembershipFunction(280, 170d),
                universe);


        Quantifier oneTwoZeroZero = new AbsoluteQuantifier(
                1200d,
                about,
                new GaussianMembershipFunction(1200, 300d),
                universe);


        Quantifier threeSixZeroZero = new AbsoluteQuantifier(
                3600d,
                about,
                new GaussianMembershipFunction(3600, 850d),
                universe);

        Quantifier sevenTwoZeroZero = new AbsoluteQuantifier(
                7200d,
                about,
                new GaussianMembershipFunction(7200, 900d),
                universe);

        Quantifier oneZeroEightZeroZero = new AbsoluteQuantifier(
                10800d,
                about,
                new GaussianMembershipFunction(10800, 1000d),
                universe);

        Quantifier oneFourFourZeroZero = new AbsoluteQuantifier(
                14600d,
                about,
                new GaussianMembershipFunction(14600, 1200d),
                universe);


        return new ArrayList<>(List.of(twoEightZero, oneTwoZeroZero, threeSixZeroZero,
                sevenTwoZeroZero, oneZeroEightZeroZero, oneFourFourZeroZero));
    }


    public static <T> List<Set<T>> generateSubsets(List<T> set) {
        int n = set.size();
        int numberOfSubsets = 1 << n;
        List<Set<T>> subsets = new ArrayList<>(numberOfSubsets);

        for (int i = 1; i < numberOfSubsets; i++) {
            HashSet<T> tmp = new HashSet<>();
            for (int j = 0; j < n; j++) {
                if (((0b1 << j) & i) == (0b1 << j)) {
                    tmp.add(set.get(j));
                }
            }
            subsets.add(tmp);
        }
        return subsets;
    }



    public double and(FuzzySet a, FuzzySet b, Entry e) {
        return Math.min(a.getMembershipFunctionValueFor(e), b.getMembershipFunctionValueFor(e));
    }

    public double or(FuzzySet a, FuzzySet b, Entry e) {
        return Math.max(a.getMembershipFunctionValueFor(e), b.getMembershipFunctionValueFor(e));
    }

    public double not(FuzzySet a, FuzzySet b, Entry e) {
        return 1 - a.getMembershipFunctionValueFor(e);
    }

}
