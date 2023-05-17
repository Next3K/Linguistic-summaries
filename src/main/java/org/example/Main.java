package org.example;

import org.example.model.Statement;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Program has started");

        List<Statement> statements = Util.generateStatements(100);

        // sort by degree of truth
        statements.sort(Comparator.comparingDouble(Statement::calculateQualityMeasure));

        // print
        statements.forEach(System.out::println);

    }
}