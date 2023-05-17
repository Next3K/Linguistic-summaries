package org.example;

import org.example.model.Entry;
import org.example.model.Quantifier;
import org.example.model.Statement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // load database
        List<Entry> database = Util.loadFromDatabase();

        List<Quantifier> relativeQuantifiers = Util.loadDefaultRelativeQuantifiers();
        List<Quantifier> absoluteQuantifiers = Util.loadDefaultAbsoluteQuantifiers();

        // generate statements
        List<Statement> statements = Util.generateStatements(relativeQuantifiers, absoluteQuantifiers, new ArrayList<>());

        // sort by degree of truth
        statements.sort(Comparator.comparingDouble(Statement::calculateQualityMeasure));

        // print
        statements.forEach(System.out::println);

    }
}