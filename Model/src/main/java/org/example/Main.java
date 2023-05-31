package org.example;

import org.example.model.db.Entry;
import org.example.model.LinguisticVariable;
import org.example.model.quantifiers.Quantifier;
import org.example.model.sets.FuzzySet;
import org.example.model.summary.Summary;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<Double> weights =
                new ArrayList<>(List.of(0.30, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07));

        // load database
        List<Entry> records = Util.loadFromDatabase();

        // load default relative quantifiers
        List<Quantifier> relativeQuantifiers = Util.loadDefaultRelativeQuantifiers();

        // load default absolute quantifiers
        List<Quantifier> absoluteQuantifiers = Util.loadDefaultAbsoluteQuantifiers();

        // load all defined variables
        List<LinguisticVariable> linguisticVariables = Util.getDefaultLinguisticVariables();

        List<FuzzySet> fuzzySets = new ArrayList<>(10);


        fuzzySets.add(linguisticVariables.get(0).getSet("cold"));
        fuzzySets.add(linguisticVariables.get(1).getSet("cool"));
        fuzzySets.add(linguisticVariables.get(2).getSet("dry"));

        // just one relative quantifier
        ArrayList<Quantifier> oneQualifier = new ArrayList<>(List.of(relativeQuantifiers.get(0)));
        // zero absolute quantifiers
        ArrayList<Quantifier> zerQualifier = new ArrayList<>();

        List<Summary> statements =
                Generator.generateStatements(
                        records,
                        zerQualifier,
                        oneQualifier,
                        fuzzySets,
                        weights);


        // print report
        for (var s : statements) {
            System.out.println(s.getTextualSummary());
        }
    }
}