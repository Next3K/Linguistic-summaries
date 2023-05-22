 package org.example;

import org.example.model.db.Entry;
import org.example.model.quantifiers.Quantifier;
import org.example.model.sets.LabeledFuzzySet;
import org.example.model.statements.Statement;
import org.example.model.statements.TwoSubjectStatement;

import java.util.ArrayList;
import java.util.List;import java.util.Map;

public class Generator {


    public static List<Statement> generateStatements(List<Entry> records,
                                                     List<Quantifier> absoluteQuantifiers,
                                                     List<Quantifier> relativeQuantifiers,
                                                     Map<Entry.DatabaseColumn, List<LabeledFuzzySet>> attributesAndSummarizers) {

        List<Statement> statements = new ArrayList<>(10_000);

        for (var quantifier : absoluteQuantifiers) {
            System.out.println("generate stuff");
        }

        for (var quantifier : relativeQuantifiers) {
            System.out.println("generate stuff");
        }

        for (var statement : statements) {
            statement.calculateQualityMeasure();
        }

        return statements;
    }

    public static List<TwoSubjectStatement> generateTwoSubjectStatements(List<Entry> records,
                                                                         List<Quantifier> relativeQuantifiers,
                                                                         Map<Entry.DatabaseColumn, List<LabeledFuzzySet>> attributesAndSummarizers) {
        List<TwoSubjectStatement> statements = new ArrayList<>(100);
        return null;
    }
}
