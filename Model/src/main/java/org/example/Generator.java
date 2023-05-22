 package org.example;

import org.example.model.db.Entry;
import org.example.model.quantifiers.Quantifier;
import org.example.model.sets.LabeledFuzzySet;
import org.example.model.statements.Summary;
import org.example.model.statements.TwoSubjectSummary;

import java.util.ArrayList;
import java.util.List;import java.util.Map;

public class Generator {


    public static List<Summary> generateStatements(List<Entry> records,
                                                   List<Quantifier> absoluteQuantifiers,
                                                   List<Quantifier> relativeQuantifiers,
                                                   Map<Entry.DatabaseColumn, List<LabeledFuzzySet>> attributesAndSummarizers) {

        List<Summary> statements = new ArrayList<>(10_000);

        for (var quantifier : absoluteQuantifiers) {
            System.out.println("generate stuff");
        }

        for (var quantifier : relativeQuantifiers) {
            System.out.println("generate stuff");
        }

        for (var statement : statements) {
            statement.calculateQualityMeasure(records);
        }

        return statements;
    }

    public static List<TwoSubjectSummary> generateTwoSubjectStatements(List<Entry> records,
                                                                       List<Quantifier> relativeQuantifiers,
                                                                       Map<Entry.DatabaseColumn, List<LabeledFuzzySet>> attributesAndSummarizers) {
        List<TwoSubjectSummary> statements = new ArrayList<>(100);
        return null;
    }
}
