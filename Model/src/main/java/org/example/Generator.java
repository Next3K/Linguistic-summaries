 package org.example;

import org.example.model.db.Entry;
import org.example.model.quantifiers.Quantifier;
import org.example.model.sets.Summarizer;
import org.example.model.statements.Statement;
import java.util.ArrayList;
import java.util.List;import java.util.Map;

public class Generator {


    public static List<Statement> generateStatements(List<Entry> records,
                                                     List<Quantifier> absoluteQuantifiers,
                                                     List<Quantifier> relativeQuantifiers,
                                                     Map<Entry.DatabaseColumn, List<Summarizer>> attributesAndSummarizers) {

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
}
