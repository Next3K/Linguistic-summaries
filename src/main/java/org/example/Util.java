package org.example;

import org.example.model.*;

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
        return new ArrayList<>(10);
    }

    public static List<Quantifier> loadDefaultAbsoluteQuantifiers() {
        return new ArrayList<>(10);
    }

}
