package org.example;

import org.example.model.Entry;
import org.example.model.Summarizer;
import org.example.model.quantifiers.Quantifier;
import org.example.model.statements.Statement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // load database
        List<Entry> database = Util.loadFromDatabase();

        List<Quantifier> relativeQuantifiers = Util.loadDefaultRelativeQuantifiers();
        List<Quantifier> absoluteQuantifiers = Util.loadDefaultAbsoluteQuantifiers();
        List<Summarizer> summarizers = new ArrayList<>();

        // generate statements
        List<Statement> statements = Util.generateStatements(relativeQuantifiers, absoluteQuantifiers, summarizers);

        // sort by degree of truth
        statements.sort(Comparator.comparingDouble(Statement::calculateQualityMeasure));

        // print
        statements.forEach(System.out::println);
    }
}