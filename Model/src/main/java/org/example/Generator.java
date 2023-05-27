 package org.example;

import org.example.model.db.Entry;
import org.example.model.quantifiers.Quantifier;
import org.example.model.sets.CompoundableLabeledFuzzySet;
import org.example.model.sets.LabeledFuzzySet;
import org.example.model.statements.FirstTypeSummary;
import org.example.model.statements.SecondTypeSummary;
import org.example.model.statements.Summary;
import org.example.model.statements.TwoSubjectSummary;

import java.beans.Statement;
import java.util.*;
import java.util.stream.Collectors;

 public class Generator {


    public static List<Summary> generateStatements(List<Entry> records,
                                                   List<Quantifier> absoluteQuantifiers,
                                                   List<Quantifier> relativeQuantifiers,
                                                   List<LabeledFuzzySet> attributesAndSummarizers,
                                                   List<Double> weights) {

        List<Set<LabeledFuzzySet>> subsets = Util.generateSubsets(attributesAndSummarizers);

        List<CompoundableLabeledFuzzySet> combinations =
                subsets.stream().map(CompoundableLabeledFuzzySet::new).toList();

        int size =
                absoluteQuantifiers.size() * combinations.size() +
                relativeQuantifiers.size() * combinations.size() +
                relativeQuantifiers.size() * 2 * combinations.size();

        List<Summary> statements = new ArrayList<>(size);

        // first type statements
        for (var quantifier : absoluteQuantifiers) {
            for (var summarizer : combinations) {
                Summary statement = new FirstTypeSummary(quantifier, summarizer);
                statements.add(statement);
            }
        }

        for (var quantifier : relativeQuantifiers) {
            for (var summarizer : combinations) {
                Summary statement = new FirstTypeSummary(quantifier, summarizer);
                statements.add(statement);
            }
        }


        // second type statements
        for (var quantifier : relativeQuantifiers) {
            for (var summarizer : combinations) {
                for (var qualifier : combinations) {
                    boolean disjoint = Collections.disjoint(summarizer.getSubset(), qualifier.getSubset());
                    if (qualifier != summarizer && disjoint) {
                        Summary statement = new SecondTypeSummary(quantifier, summarizer, qualifier);
                        statements.add(statement);
                    }
                }
            }
        }

        // calculate T1 - T11
        statements.forEach(statement -> statement.calculateQualityMeasure(records, weights));

        // sort by quality measure
        statements.sort(Comparator.comparingDouble(Summary::getQualityMeasure).reversed());

        return statements;
    }

    public static List<TwoSubjectSummary> generateTwoSubjectStatements(List<Entry> records,
                                                                       List<Quantifier> relativeQuantifiers,
                                                                       Map<Entry.DatabaseColumn, List<LabeledFuzzySet>> attributesAndSummarizers) {
        List<TwoSubjectSummary> statements = new ArrayList<>(100);
        return null;
    }

}
