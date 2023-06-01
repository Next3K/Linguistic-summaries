 package org.example;

import org.example.model.db.Entry;
import org.example.model.quantifiers.Quantifier;
import org.example.model.sets.CompoundFuzzySet;
import org.example.model.sets.FuzzySet;
import org.example.model.summary.FirstFormSummary;
import org.example.model.summary.SecondFormSummary;
import org.example.model.summary.Summary;
import org.example.model.summary.MultiSubjectSummary;

import java.util.*;
import java.util.stream.Collectors;

 public class Generator {


    public static List<Summary> generateSummaries(List<Entry> records,
                                                  List<Quantifier> absoluteQuantifiers,
                                                  List<Quantifier> relativeQuantifiers,
                                                  List<FuzzySet> attributesAndSummarizers,
                                                  List<Double> weights) {

        List<Set<FuzzySet>> subsets = Util.generateSubsets(attributesAndSummarizers);

        List<CompoundFuzzySet> combinations =
                subsets.stream().map(CompoundFuzzySet::new).toList();

        int size =
                absoluteQuantifiers.size() * combinations.size() +
                relativeQuantifiers.size() * combinations.size() +
                relativeQuantifiers.size() * 2 * combinations.size();

        List<Summary> statements = new ArrayList<>(size);

        // first type statements
        for (var quantifier : absoluteQuantifiers) {
            for (var summarizer : combinations) {
                Summary statement = new FirstFormSummary(quantifier, summarizer);
                statements.add(statement);
            }
        }

        for (var quantifier : relativeQuantifiers) {
            for (var summarizer : combinations) {
                Summary statement = new FirstFormSummary(quantifier, summarizer);
                statements.add(statement);
            }
        }


        // second type statements
        for (var quantifier : relativeQuantifiers) {
            for (var summarizer : combinations) {
                for (var qualifier : combinations) {
                    boolean disjoint = Collections.disjoint(summarizer.getFuzzySets(), qualifier.getFuzzySets());
                    if (qualifier != summarizer && disjoint) {
                        Summary statement = new SecondFormSummary(quantifier, summarizer, qualifier);
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

    public static List<MultiSubjectSummary> generateTwoSubjectSummaries(List<Entry> records,
                                                                        List<Quantifier> relativeQuantifiers,
                                                                        Map<Entry.DatabaseColumn, List<FuzzySet>> attributesAndSummarizers) {
        List<MultiSubjectSummary> statements = new ArrayList<>(100);

        Map<Boolean, List<Entry>> partitioned = records.stream().collect(Collectors.partitioningBy(e ->
                e.getSubjectType().equals(Entry.SubjectType.CURRENT)));

        Map<Entry.SubjectType, List<Entry>> map =
                Map.of(Entry.SubjectType.CURRENT, partitioned.get(true),
                Entry.SubjectType.PREMILLENIAL, partitioned.get(false));

        return null;
    }

}
