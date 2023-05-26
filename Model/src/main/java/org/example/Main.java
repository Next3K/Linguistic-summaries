package org.example;

import org.example.model.db.Entry;
import org.example.model.sets.LabeledFuzzySet;
import org.example.model.LinguisticVariable;
import org.example.model.quantifiers.Quantifier;
import org.example.model.statements.Summary;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        // load database
        List<Entry> records = Util.loadFromDatabase();

        // load default relative quantifiers
        List<Quantifier> relativeQuantifiers = Util.loadDefaultRelativeQuantifiers();

        // load default absolute quantifiers
        List<Quantifier> absoluteQuantifiers = Util.loadDefaultAbsoluteQuantifiers();

        // load all defined variables
        List<LinguisticVariable> linguisticVariables = Util.getDefaultLinguisticVariables();

        // users chose which fuzzy sets are of interest
        List<LabeledFuzzySet> fuzzySets = new ArrayList<>(10);
        LinguisticVariable someLinguisticVariableOne = linguisticVariables.get(0);
        LinguisticVariable someLinguisticVariableTwo = linguisticVariables.get(1);
        fuzzySets.addAll(getLabeledFuzzySets(someLinguisticVariableOne, List.of("warm", "hot")));
        fuzzySets.addAll(getLabeledFuzzySets(someLinguisticVariableTwo, List.of("cool", "moderate")));

        // generate statements
        List<Summary> statements =
                Generator.generateStatements(records, relativeQuantifiers, absoluteQuantifiers, fuzzySets);

        // print
        statements.forEach(System.out::println);
    }

    public static List<LabeledFuzzySet> getLabeledFuzzySets(LinguisticVariable variable,
                                                            List<String> labelsOfChosenSet) {
        return variable.getLabeledFuzzySetsForChosenFuzzySets(labelsOfChosenSet);
    }
}