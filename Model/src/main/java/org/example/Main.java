package org.example;

import org.example.model.db.Entry;
import org.example.model.sets.LabeledFuzzySet;
import org.example.model.LinguisticVariable;
import org.example.model.quantifiers.Quantifier;
import org.example.model.statements.Summary;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<Double> weights =
                new ArrayList<>(List.of(0.30, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07, 0.07));

        // load database
        List<Entry> records = Util.loadFromDatabase();

        System.out.println(records.get(1).getSubjectType());
        System.out.println(records.get(1).getValues());

        // load default relative quantifiers
        List<Quantifier> relativeQuantifiers = Util.loadDefaultRelativeQuantifiers();

        // load default absolute quantifiers
        List<Quantifier> absoluteQuantifiers = Util.loadDefaultAbsoluteQuantifiers();

        // load all defined variables
        List<LinguisticVariable> linguisticVariables = Util.getDefaultLinguisticVariables();

        List<LabeledFuzzySet> fuzzySets = new ArrayList<>(10);

        // users chose which fuzzy sets are of interest
        LinguisticVariable someLinguisticVariableOne = linguisticVariables.get(0);
        LinguisticVariable someLinguisticVariableTwo = linguisticVariables.get(1);
        fuzzySets.addAll(Util.getLabeledFuzzySets(someLinguisticVariableOne, List.of("warm", "hot")));
        fuzzySets.addAll(Util.getLabeledFuzzySets(someLinguisticVariableTwo, List.of("cool", "moderate")));

        // generate statements for database
        List<Summary> statements =
                Generator.generateStatements(
                        records,
                        absoluteQuantifiers,
                        relativeQuantifiers,
                        fuzzySets,
                        weights);

        // print report
        for (var s : statements) {
            System.out.println(s.getTextualSummary());
        }
    }
}