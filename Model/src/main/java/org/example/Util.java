package org.example;

import lombok.Getter;
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

@Getter
public class Util {

    private static final UniverseOfDiscourse relative = new UniverseOfDiscourse(0d, 1d);
    private static final UniverseOfDiscourse absolute = new UniverseOfDiscourse(0d, 14_854d);

    public static List<LinguisticVariable> getDefaultLinguisticVariables() {

        UniverseOfDiscourse uni1 = new UniverseOfDiscourse(0d, 60d);
        Map<String, FuzzySet> mapMinTemp = new LinkedHashMap<>();

        mapMinTemp.put("cold", new FuzzySet(
                                "cold",
                                Entry.DatabaseColumn.MIN_TEMPERATURE,
                                new TrapezoidShape(-1d, 0d, 5d, 15d),
                                uni1));

        mapMinTemp.put("cool", new FuzzySet("cool",
                                Entry.DatabaseColumn.MIN_TEMPERATURE,
                                new TriangularShape(5d, 15d, 25d),
                                uni1));

        mapMinTemp.put("moderate", new FuzzySet(
                                "moderate",
                                Entry.DatabaseColumn.MIN_TEMPERATURE,
                                new TriangularShape(15d, 25d, 35d),
                                uni1));
        mapMinTemp.put("warm", new FuzzySet(
                                "warm",
                                Entry.DatabaseColumn.MIN_TEMPERATURE,
                                new TriangularShape(25d, 35d, 45d),
                                uni1));
        mapMinTemp.put("hot", new FuzzySet(
                                "hot",
                                Entry.DatabaseColumn.MIN_TEMPERATURE,
                                new TrapezoidShape(35d, 45d, 60d, 100d),
                                uni1));
        LinguisticVariable minTemperature = new LinguisticVariable(mapMinTemp, uni1);


        UniverseOfDiscourse uni2 = new UniverseOfDiscourse(0d, 60d);
        Map<String, FuzzySet> mapMaxTemp = new LinkedHashMap<>();

        mapMaxTemp.put("cold", new FuzzySet(
                        "cold",
                        Entry.DatabaseColumn.MAX_TEMPERATURE,
                        new TrapezoidShape(-1d, 0d, 5d, 15d),
                        uni2));

        mapMaxTemp.put("cool", new FuzzySet("cool",
                        Entry.DatabaseColumn.MAX_TEMPERATURE,
                        new TriangularShape(5d, 15d, 25d),
                        uni2));

        mapMaxTemp.put( "moderate", new FuzzySet(
                "moderate",
                Entry.DatabaseColumn.MAX_TEMPERATURE,
                new TriangularShape(15d, 25d, 35d),
                uni2));

        mapMaxTemp.put( "warm", new FuzzySet(
                        "warm",
                        Entry.DatabaseColumn.MAX_TEMPERATURE,
                        new TriangularShape(25d, 35d, 45d),
                        uni2));

        mapMaxTemp.put("hot", new FuzzySet(
                        "hot",
                        Entry.DatabaseColumn.MAX_TEMPERATURE,
                        new TrapezoidShape(35d, 45d, 60d, 100d),
                        uni2));

        LinguisticVariable maxTemperature = new LinguisticVariable(mapMaxTemp, uni2);



        UniverseOfDiscourse uni3 = new UniverseOfDiscourse(0d, 100d);
        Map<String, FuzzySet> mapMorningHumidity = new LinkedHashMap<>();

        mapMorningHumidity.put("dry", new FuzzySet(
                "dry",
                Entry.DatabaseColumn.MORNING_HUMIDITY,
                new TrapezoidShape(-1d, 0d, 10d, 30d),
                uni3));

        mapMorningHumidity.put("comfortable", new FuzzySet("comfortable",
                Entry.DatabaseColumn.MORNING_HUMIDITY,
                new TrapezoidShape(10d, 30d, 50d, 70d),
                uni3));

        mapMorningHumidity.put("dumpy", new FuzzySet(
                "dumpy",
                Entry.DatabaseColumn.MORNING_HUMIDITY,
                new TrapezoidShape(50d, 70d, 100d, 110d),
                uni3));

        LinguisticVariable morningHumidity = new LinguisticVariable(mapMorningHumidity, uni3);


        UniverseOfDiscourse uni4 = new UniverseOfDiscourse(0d, 100d);
        Map<String, FuzzySet> mapAfternoonHumidity = new LinkedHashMap<>();

        mapAfternoonHumidity.put("dry", new FuzzySet(
                "dry",
                Entry.DatabaseColumn.AFTERNOON_HUMIDITY,
                new TrapezoidShape(-1d, 0d, 10d, 30d),
                uni4));

        mapAfternoonHumidity.put("comfortable", new FuzzySet("comfortable",
                Entry.DatabaseColumn.AFTERNOON_HUMIDITY,
                new TrapezoidShape(10d, 30d, 50d, 70d),
                uni4));

        mapAfternoonHumidity.put("dumpy", new FuzzySet(
                "dumpy",
                Entry.DatabaseColumn.AFTERNOON_HUMIDITY,
                new TrapezoidShape(50d, 70d, 100d, 110d),
                uni4));

        LinguisticVariable afternoonHumidity = new LinguisticVariable(mapAfternoonHumidity, uni4);


        UniverseOfDiscourse uni5 = new UniverseOfDiscourse(0d, 140d);
        Map<String, FuzzySet> mapWindSpeed = new LinkedHashMap<>();

        mapWindSpeed.put("calm", new FuzzySet(
                "calm",
                Entry.DatabaseColumn.WIND,
                new TriangularShape(-1d, 0d, 10d),
                uni5));

        mapWindSpeed.put( "breeze",
                new FuzzySet("breeze",
                        Entry.DatabaseColumn.WIND,
                        new TrapezoidShape(0d, 10d, 25d, 35d),
                        uni5));
        mapWindSpeed.put( "strong breeze",
                new FuzzySet(
                        "strong breeze",
                        Entry.DatabaseColumn.WIND,
                        new TrapezoidShape(25d, 35d, 45d, 55d),
                        uni5));
        mapWindSpeed.put(  "near gale",
                new FuzzySet(
                        "near gale",
                        Entry.DatabaseColumn.WIND,
                        new TriangularShape(45d, 55d, 65d),
                        uni5));
        mapWindSpeed.put("gale",
                new FuzzySet(
                        "gale",
                        Entry.DatabaseColumn.WIND,
                        new TrapezoidShape(55d, 65d, 70d, 80d),
                        uni5));
        mapWindSpeed.put("strog gale",
                new FuzzySet(
                        "strog gale",
                        Entry.DatabaseColumn.WIND,
                        new TrapezoidShape(70d, 80d, 95d, 105d),
                        uni5));
        mapWindSpeed.put("storm",
                new FuzzySet(
                        "storm",
                        Entry.DatabaseColumn.WIND,
                        new TrapezoidShape(95d, 105d, 115d, 125d),
                        uni5));
        mapWindSpeed.put( "hurricane",
                new FuzzySet(
                        "hurricane",
                        Entry.DatabaseColumn.WIND,
                        new TrapezoidShape(115d, 125d, 140d, 150d),
                        uni5));

        LinguisticVariable windSpeed = new LinguisticVariable(mapWindSpeed, uni5);


        UniverseOfDiscourse uni6 = new UniverseOfDiscourse(0d, 300d);
        Map<String, FuzzySet> mapRainfall = new LinkedHashMap<>();

        mapRainfall.put("mist to light",
                new FuzzySet(
                        "mist to light",
                        Entry.DatabaseColumn.RAINFALL,
                        new TriangularShape(-1d, 0d, 20d),
                        uni6));
        mapRainfall.put("moderate",
                new FuzzySet("moderate",
                        Entry.DatabaseColumn.RAINFALL,
                        new TrapezoidShape(0d, 20d, 30d, 50d),
                        uni6));
        mapRainfall.put("heavy",
                new FuzzySet(
                        "heavy",
                        Entry.DatabaseColumn.RAINFALL,
                        new TrapezoidShape(30d, 50d, 80d, 100d),
                        uni6));
        mapRainfall.put("very heavy",
                new FuzzySet(
                        "very heavy",
                        Entry.DatabaseColumn.RAINFALL,
                        new TrapezoidShape(80d, 100d, 140d, 160d),
                        uni6));
        mapRainfall.put( "intense",
                new FuzzySet(
                        "intense",
                        Entry.DatabaseColumn.RAINFALL,
                        new TrapezoidShape(140d, 160d, 210d, 230d),
                        uni6));
        mapRainfall.put("extreme",
                new FuzzySet(
                        "extreme",
                        Entry.DatabaseColumn.RAINFALL,
                        new TrapezoidShape(200d, 240d, 300d, 310d),
                        uni6));

        LinguisticVariable rainfall = new LinguisticVariable(mapRainfall, uni6);



        UniverseOfDiscourse uni7 = new UniverseOfDiscourse(0d, 14d);
        Map<String, FuzzySet> mapInsolation = new LinkedHashMap<>();

        mapInsolation.put("fully cloudy",
                new FuzzySet(
                        "fully cloudy",
                        Entry.DatabaseColumn.INSOLATION,
                        new TriangularShape(-1d, 0d, 4d),
                        uni7));
        mapInsolation.put("moderately cloudy",
                new FuzzySet("moderately cloudy",
                        Entry.DatabaseColumn.INSOLATION,
                        new TriangularShape(0d, 4d, 8d),
                        uni7));
        mapInsolation.put("lightly cloudy",
                new FuzzySet(
                        "lightly cloudy",
                        Entry.DatabaseColumn.INSOLATION,
                        new TriangularShape(4d, 8d, 12d),
                        uni7));
        mapInsolation.put("cloudless",
                new FuzzySet(
                        "cloudless",
                        Entry.DatabaseColumn.INSOLATION,
                        new TrapezoidShape(8d, 12d, 14d, 16d),
                        uni7));

        LinguisticVariable insolation = new LinguisticVariable(mapInsolation, uni7);


        UniverseOfDiscourse uni8 = new UniverseOfDiscourse(0d, 24d);
        Map<String, FuzzySet> mapEvaporation = new LinkedHashMap<>();

        mapEvaporation.put("light",
                new FuzzySet(
                        "light",
                        Entry.DatabaseColumn.EVAPORATION,
                        new TrapezoidShape(-1d, 0d, 4d, 8d),
                        uni8));
        mapEvaporation.put("gentle",
                new FuzzySet("gentle",
                        Entry.DatabaseColumn.EVAPORATION,
                        new TrapezoidShape(4d, 8d, 10d, 14d),
                        uni8));
        mapEvaporation.put("moderate",
                new FuzzySet(
                        "moderate",
                        Entry.DatabaseColumn.EVAPORATION,
                        new TrapezoidShape(10d, 14d, 16d, 20d),
                        uni8));
        mapEvaporation.put("high",
                new FuzzySet(
                        "high",
                        Entry.DatabaseColumn.EVAPORATION,
                        new TrapezoidShape(16d, 20d, 24d, 30d),
                        uni8));

        LinguisticVariable evaporation = new LinguisticVariable(mapEvaporation, uni8);

        UniverseOfDiscourse uni9 = new UniverseOfDiscourse(0d, 40d);
        Map<String, FuzzySet> mapRadiation = new LinkedHashMap<>();

        mapRadiation.put("light",
                new FuzzySet(
                        "light",
                        Entry.DatabaseColumn.RADIATION,
                        new TrapezoidShape(-1d, 0d, 5d, 15d),
                        uni9));
        mapRadiation.put("moderate",
                new FuzzySet("moderate",
                        Entry.DatabaseColumn.RADIATION,
                        new TriangularShape(5d, 15d, 25d),
                        uni9));
        mapRadiation.put("high",
                new FuzzySet(
                        "high",
                        Entry.DatabaseColumn.RADIATION,
                        new TriangularShape(15d, 25d, 35d),
                        uni9));
        mapRadiation.put("extreme",
                new FuzzySet(
                        "extreme",
                        Entry.DatabaseColumn.RADIATION,
                        new TrapezoidShape(25d, 35d, 40d, 50d),
                        uni9));

        LinguisticVariable radiation = new LinguisticVariable(mapRadiation, uni9);


        UniverseOfDiscourse uni10 = new UniverseOfDiscourse(0d, 40d);
        Map<String, FuzzySet> mapEvapotranspiration = new LinkedHashMap<>();

        mapEvapotranspiration.put("light",
                new FuzzySet(
                        "light",
                        Entry.DatabaseColumn.EVAPOTRANSPIRATION,
                        new TrapezoidShape(-1d, 0d, 3d, 5d),
                        uni10));
        mapEvapotranspiration.put("moderate",
                new FuzzySet("moderate",
                        Entry.DatabaseColumn.EVAPOTRANSPIRATION,
                        new TriangularShape(3d, 5d, 7d),
                        uni10));
        mapEvapotranspiration.put("high",
                new FuzzySet(
                        "high",
                        Entry.DatabaseColumn.EVAPOTRANSPIRATION,
                        new TrapezoidShape(5d, 7d, 8d, 10d),
                        uni10));
        mapEvapotranspiration.put("extreme",
                new FuzzySet(
                        "extreme",
                        Entry.DatabaseColumn.EVAPOTRANSPIRATION,
                        new TrapezoidShape(8d, 10d, 14d, 20d),
                        uni10));

        LinguisticVariable evapotranspiration = new LinguisticVariable(mapEvapotranspiration, uni10);


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

        Quantifier almostNone =
                new RelativeQuantifier(
                        "Almost none",
                        new TriangularShape(-1d, 0d, 0.1d),
                        relative);
        Quantifier few =
                new RelativeQuantifier(
                        "Few",
                        new TriangularShape(0d, 0.1d, 0.2d),
                        relative);
        Quantifier aboutQuarter =
                new RelativeQuantifier(
                        "About quarter",
                        new TriangularShape(0.1d, 0.2d, 0.3d),
                        relative);
        Quantifier some =
                new RelativeQuantifier(
                        "Some",
                        new TrapezoidShape(0.2d, 0.3d, 0.35d, 0.45d),
                        relative);
        Quantifier aboutHalf =
                new RelativeQuantifier(
                        "About half",
                        new TrapezoidShape(0.3d, 0.45d, 0.55d, 0.7d),
                        relative);
        Quantifier aboutThreeQuarters =
                new RelativeQuantifier(
                        "About three-quarters",
                        new TriangularShape(0.6d, 0.7d, 0.8d),
                        relative);
        Quantifier many =
                new RelativeQuantifier(
                        "Many",
                        new TrapezoidShape(0.7d, 0.8d, 0.85d, 0.95d),
                        relative);
        Quantifier almostAll =
                new RelativeQuantifier(
                        "Almost all",
                        new TrapezoidShape(0.85d, 0.95d, 1d, 1.1d),
                        relative);

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

        Quantifier twoEightZero = new AbsoluteQuantifier(
                "About 280",
                new GaussianShape(280, 170d),
                absolute);

        Quantifier oneTwoZeroZero = new AbsoluteQuantifier(
                "About 1200",
                new GaussianShape(1200, 300d),
                absolute);

        Quantifier threeSixZeroZero = new AbsoluteQuantifier(
                "About 3600",
                new GaussianShape(3600, 850d),
                absolute);

        Quantifier sevenTwoZeroZero = new AbsoluteQuantifier(
                "About 7200",
                new GaussianShape(7200, 900d),
                absolute);

        Quantifier oneZeroEightZeroZero = new AbsoluteQuantifier(
                "About 10800",
                new GaussianShape(10800, 1000d),
                absolute);

        Quantifier oneFourFourZeroZero = new AbsoluteQuantifier(
                "About 14600",
                new GaussianShape(14600, 1200d),
                absolute);


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

    public static UniverseOfDiscourse getRelative() {
        return relative;
    }

    public static UniverseOfDiscourse getAbsolute() {
        return absolute;
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
