package org.example;

import org.example.model.db.Connect;
import org.example.model.db.Entry;
import org.example.model.functions.GaussianShape;
import org.example.model.quantifiers.AbsoluteQuantifier;
import org.example.model.sets.*;
import org.example.model.LinguisticVariable;
import org.example.model.functions.TrapezoidShape;
import org.example.model.functions.TriangularShape;
import org.example.model.quantifiers.Quantifier;
import org.example.model.quantifiers.RelativeQuantifier;

import java.util.*;

public class Util {

    public static List<LinguisticVariable> getDefaultLinguisticVariables() {

        UniverseOfDiscourse uni1 = new UniverseOfDiscourse(0d, 60d);
        LinguisticVariable minTemperature = new LinguisticVariable(
                Map.of("cold", new FuzzySet(
                                "cold",
                                Entry.DatabaseColumn.MIN_TEMPERATURE,
                                new TrapezoidShape(-1d, 0d, 5d, 15d),
                                uni1), "cool",
                        new FuzzySet("cool",
                                Entry.DatabaseColumn.MIN_TEMPERATURE,
                                new TriangularShape(5d, 15d, 25d),
                                uni1), "moderate",
                        new FuzzySet(
                                "moderate",
                                Entry.DatabaseColumn.MIN_TEMPERATURE,
                                new TriangularShape(15d, 25d, 35d),
                                uni1), "warm",
                        new FuzzySet(
                                "warm",
                                Entry.DatabaseColumn.MIN_TEMPERATURE,
                                new TriangularShape(25d, 35d, 45d),
                                uni1), "hot",
                        new FuzzySet(
                                "hot",
                                Entry.DatabaseColumn.MIN_TEMPERATURE,
                                new TrapezoidShape(35d, 45d, 60d, 100d),
                                uni1)),
                uni1);


        UniverseOfDiscourse uni2 = new UniverseOfDiscourse(0d, 60d);
        LinguisticVariable maxTemperature = new LinguisticVariable(
                Map.of("cold",
                        new FuzzySet(
                                "cold",
                                Entry.DatabaseColumn.MAX_TEMPERATURE,
                                new TrapezoidShape(-1d, 0d, 5d, 15d),
                                uni2),
                        "cool",
                        new FuzzySet("cool",
                                Entry.DatabaseColumn.MAX_TEMPERATURE,
                                new TriangularShape(5d, 15d, 25d),
                                uni2),
                        "moderate",
                        new FuzzySet(
                                "moderate",
                                Entry.DatabaseColumn.MAX_TEMPERATURE,
                                new TriangularShape(15d, 25d, 35d),
                                uni2),
                        "warm",
                        new FuzzySet(
                                "warm",
                                Entry.DatabaseColumn.MAX_TEMPERATURE,
                                new TriangularShape(25d, 35d, 45d),
                                uni2),
                        "hot",
                        new FuzzySet(
                                "hot",
                                Entry.DatabaseColumn.MAX_TEMPERATURE,
                                new TrapezoidShape(35d, 45d, 60d, 100d),
                                uni2)),
                uni2);

        UniverseOfDiscourse uni3 = new UniverseOfDiscourse(0d, 100d);
        LinguisticVariable morningHumidity = new LinguisticVariable(
                Map.of("dry",
                        new FuzzySet(
                                "dry",
                                Entry.DatabaseColumn.MORNING_HUMIDITY,
                                new TrapezoidShape(-1d, 0d, 10d, 30d),
                                uni3),
                        "comfortable",
                        new FuzzySet("comfortable",
                                Entry.DatabaseColumn.MORNING_HUMIDITY,
                                new TrapezoidShape(10d, 30d, 50d, 70d),
                                uni3),
                        "dumpy",
                        new FuzzySet(
                                "dumpy",
                                Entry.DatabaseColumn.MORNING_HUMIDITY,
                                new TrapezoidShape(50d, 70d, 100d, 110d),
                                uni3)),
                uni3);

        UniverseOfDiscourse uni4 = new UniverseOfDiscourse(0d, 100d);
        LinguisticVariable afternoonHumidity = new LinguisticVariable(
                Map.of("dry",
                        new FuzzySet(
                                "dry",
                                Entry.DatabaseColumn.AFTERNOON_HUMIDITY,
                                new TrapezoidShape(-1d, 0d, 10d, 30d),
                                uni4),
                        "comfortable",
                        new FuzzySet("comfortable",
                                Entry.DatabaseColumn.AFTERNOON_HUMIDITY,
                                new TrapezoidShape(10d, 30d, 50d, 70d),
                                uni4),
                        "dumpy",
                        new FuzzySet(
                                "dumpy",
                                Entry.DatabaseColumn.AFTERNOON_HUMIDITY,
                                new TrapezoidShape(50d, 70d, 100d, 110d),
                                uni4)),
                uni4);


        UniverseOfDiscourse uni5 = new UniverseOfDiscourse(0d, 140d);
        LinguisticVariable windSpeed = new LinguisticVariable(
                Map.of("calm",
                        new FuzzySet(
                                "calm",
                                Entry.DatabaseColumn.WIND,
                                new TriangularShape(-1d, 0d, 10d),
                                uni5),
                        "breeze",
                        new FuzzySet("breeze",
                                Entry.DatabaseColumn.WIND,
                                new TrapezoidShape(0d, 10d, 25d, 35d),
                                uni5),
                        "strong breeze",
                        new FuzzySet(
                                "strong breeze",
                                Entry.DatabaseColumn.WIND,
                                new TrapezoidShape(25d, 35d, 45d, 55d),
                                uni5),
                        "near gale",
                        new FuzzySet(
                                "near gale",
                                Entry.DatabaseColumn.WIND,
                                new TriangularShape(45d, 55d, 65d),
                                uni5),
                        "gale",
                        new FuzzySet(
                                "gale",
                                Entry.DatabaseColumn.WIND,
                                new TrapezoidShape(55d, 65d, 70d, 80d),
                                uni5),
                        "strog gale",
                        new FuzzySet(
                                "strog gale",
                                Entry.DatabaseColumn.WIND,
                                new TrapezoidShape(70d, 80d, 95d, 105d),
                                uni5),
                        "storm",
                        new FuzzySet(
                                "storm",
                                Entry.DatabaseColumn.WIND,
                                new TrapezoidShape(95d, 105d, 115d, 125d),
                                uni5),
                        "hurricane",
                        new FuzzySet(
                                "hurricane",
                                Entry.DatabaseColumn.WIND,
                                new TrapezoidShape(115d, 125d, 140d, 150d),
                                uni5)),
                uni5);

        UniverseOfDiscourse uni6 = new UniverseOfDiscourse(0d, 300d);
        LinguisticVariable rainfall = new LinguisticVariable(
                Map.of("mist to light",
                        new FuzzySet(
                                "mist to light",
                                Entry.DatabaseColumn.RAINFALL,
                                new TriangularShape(-1d, 0d, 10d),
                                uni6),
                        "moderate",
                        new FuzzySet("moderate",
                                Entry.DatabaseColumn.RAINFALL,
                                new TriangularShape(0d, 10d, 20d),
                                uni6),
                        "heavy",
                        new FuzzySet(
                                "heavy",
                                Entry.DatabaseColumn.RAINFALL,
                                new TrapezoidShape(10d, 20d, 25d, 35d),
                                uni6),
                        "very heavy",
                        new FuzzySet(
                                "very heavy",
                                Entry.DatabaseColumn.RAINFALL,
                                new TrapezoidShape(25d, 35d, 40d, 50d),
                                uni6),
                        "intense",
                        new FuzzySet(
                                "intense",
                                Entry.DatabaseColumn.RAINFALL,
                                new TrapezoidShape(40d, 50d, 55d, 65d),
                                uni6),
                        "extreme",
                        new FuzzySet(
                                "extreme",
                                Entry.DatabaseColumn.RAINFALL,
                                new TrapezoidShape(55d, 65d, 70d, 80d),
                                uni6)),
                uni6);

        UniverseOfDiscourse uni7 = new UniverseOfDiscourse(0d, 14d);
        LinguisticVariable insolation = new LinguisticVariable(
                Map.of("fully cloudy",
                        new FuzzySet(
                                "fully cloudy",
                                Entry.DatabaseColumn.INSOLATION,
                                new TriangularShape(-1d, 0d, 4d),
                                uni7),
                        "moderately cloudy",
                        new FuzzySet("moderately cloudy",
                                Entry.DatabaseColumn.INSOLATION,
                                new TriangularShape(0d, 4d, 8d),
                                uni7),
                        "lightly cloudy",
                        new FuzzySet(
                                "lightly cloudy",
                                Entry.DatabaseColumn.INSOLATION,
                                new TriangularShape(4d, 8d, 12d),
                                uni7),
                        "cloudless",
                        new FuzzySet(
                                "cloudless",
                                Entry.DatabaseColumn.INSOLATION,
                                new TrapezoidShape(8d, 12d, 14d, 16d),
                                uni7)),
                uni7);


        UniverseOfDiscourse uni8 = new UniverseOfDiscourse(0d, 24d);
        LinguisticVariable evaporation = new LinguisticVariable(
                Map.of("light",
                        new FuzzySet(
                                "light",
                                Entry.DatabaseColumn.EVAPORATION,
                                new TrapezoidShape(-1d, 0d, 4d, 8d),
                                uni8),
                        "gentle",
                        new FuzzySet("gentle",
                                Entry.DatabaseColumn.EVAPORATION,
                                new TrapezoidShape(4d, 8d, 10d, 14d),
                                uni8),
                        "moderate",
                        new FuzzySet(
                                "moderate",
                                Entry.DatabaseColumn.EVAPORATION,
                                new TrapezoidShape(10d, 14d, 16d, 20d),
                                uni8),
                        "high",
                        new FuzzySet(
                                "high",
                                Entry.DatabaseColumn.EVAPORATION,
                                new TrapezoidShape(16d, 20d, 24d, 30d),
                                uni8)),
                uni8);

        UniverseOfDiscourse uni9 = new UniverseOfDiscourse(0d, 40d);
        LinguisticVariable radiation = new LinguisticVariable(
                Map.of("light",
                        new FuzzySet(
                                "light",
                                Entry.DatabaseColumn.RADIATION,
                                new TrapezoidShape(-1d, 0d, 5d, 15d),
                                uni9),
                        "moderate",
                        new FuzzySet("moderate",
                                Entry.DatabaseColumn.RADIATION,
                                new TriangularShape(5d, 15d, 25d),
                                uni9),
                        "high",
                        new FuzzySet(
                                "high",
                                Entry.DatabaseColumn.RADIATION,
                                new TriangularShape(15d, 25d, 35d),
                                uni9),
                        "extreme",
                        new FuzzySet(
                                "extreme",
                                Entry.DatabaseColumn.RADIATION,
                                new TrapezoidShape(25d, 35d, 40d, 50d),
                                uni9)),
                uni9);


        UniverseOfDiscourse uni10 = new UniverseOfDiscourse(0d, 40d);
        LinguisticVariable evapotranspiration = new LinguisticVariable(
                Map.of("light",
                        new FuzzySet(
                                "light",
                                Entry.DatabaseColumn.EVAPOTRANSPIRATION,
                                new TrapezoidShape(-1d, 0d, 3d, 5d),
                                uni10),
                        "moderate",
                        new FuzzySet("moderate",
                                Entry.DatabaseColumn.EVAPOTRANSPIRATION,
                                new TriangularShape(3d, 5d, 7d),
                                uni10),
                        "high",
                        new FuzzySet(
                                "high",
                                Entry.DatabaseColumn.EVAPOTRANSPIRATION,
                                new TrapezoidShape(5d, 7d, 8d, 10d),
                                uni10),
                        "extreme",
                        new FuzzySet(
                                "extreme",
                                Entry.DatabaseColumn.EVAPOTRANSPIRATION,
                                new TrapezoidShape(8d, 10d, 14d, 20d),
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
        var universe = UniverseOfDiscourse.relativeQuantifierUniverseInstance();

        Quantifier almostNone =
                new RelativeQuantifier(
                        "Almost none",
                        new TriangularShape(-1d, 0d, 0.1d),
                        universe);
        Quantifier few =
                new RelativeQuantifier(
                        "Few",
                        new TriangularShape(0d, 0.1d, 0.2d),
                        universe);
        Quantifier aboutQuarter =
                new RelativeQuantifier(
                        "About quarter",
                        new TriangularShape(0.1d, 0.2d, 0.3d),
                        universe);
        Quantifier some =
                new RelativeQuantifier(
                        "Some",
                        new TrapezoidShape(0.2d, 0.3d, 0.35d, 0.45d),
                        universe);
        Quantifier aboutHalf =
                new RelativeQuantifier(
                        "About half",
                        new TrapezoidShape(0.3d, 0.45d, 0.55d, 0.7d),
                        universe);
        Quantifier aboutThreeQuarters =
                new RelativeQuantifier(
                        "About three-quarters",
                        new TriangularShape(0.6d, 0.7d, 0.8d),
                        universe);
        Quantifier many =
                new RelativeQuantifier(
                        "Many",
                        new TrapezoidShape(0.7d, 0.85d, 1d, 1.2d),
                        universe);
        Quantifier almostAll =
                new RelativeQuantifier(
                        "Almost all",
                        new TrapezoidShape(0.85d, 0.95d, 1d, 1.2d),
                        universe);

        return new ArrayList<>(List.of(
                almostNone,
                few,
                aboutQuarter,
                some,
                aboutHalf,
                aboutThreeQuarters,
                many,
                almostAll));
    }


    public static List<Quantifier> loadDefaultAbsoluteQuantifiers() {
        UniverseOfDiscourse universe = UniverseOfDiscourse.absoluteQuantifierUniverseInstance();

        Quantifier twoEightZero = new AbsoluteQuantifier(
                "About 280",
                new GaussianShape(280, 170d),
                universe);

        Quantifier oneTwoZeroZero = new AbsoluteQuantifier(
                "About 1200",
                new GaussianShape(1200, 300d),
                universe);

        Quantifier threeSixZeroZero = new AbsoluteQuantifier(
                "About 3600",
                new GaussianShape(3600, 850d),
                universe);

        Quantifier sevenTwoZeroZero = new AbsoluteQuantifier(
                "About 7200",
                new GaussianShape(7200, 900d),
                universe);

        Quantifier oneZeroEightZeroZero = new AbsoluteQuantifier(
                "About 10800",
                new GaussianShape(10800, 1000d),
                universe);

        Quantifier oneFourFourZeroZero = new AbsoluteQuantifier(
                "About 14600",
                new GaussianShape(14600, 1200d),
                universe);


        return new ArrayList<>(List.of(
                twoEightZero,
                oneTwoZeroZero,
                threeSixZeroZero,
                sevenTwoZeroZero,
                oneZeroEightZeroZero,
                oneFourFourZeroZero));
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
        return Math.min(a.evaluateFor(e), b.evaluateFor(e));
    }

    public double or(FuzzySet a, FuzzySet b, Entry e) {
        return Math.max(a.evaluateFor(e), b.evaluateFor(e));
    }

    public double not(FuzzySet a, Entry e) {
        return 1 - a.evaluateFor(e);
    }

}
