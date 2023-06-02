 package org.example;

import org.example.model.db.Entry;
import org.example.model.quantifiers.Quantifier;
import org.example.model.sets.CompoundFuzzySet;
import org.example.model.sets.FuzzySet;
import org.example.model.summary.*;

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
                                                                        List<FuzzySet> attributesAndSummarizers) {
        List<MultiSubjectSummary> summaries = new ArrayList<>(100);

        List<Set<FuzzySet>> subsets = Util.generateSubsets(attributesAndSummarizers);
        List<CompoundFuzzySet> combinations =
                subsets.stream().map(CompoundFuzzySet::new).toList();

        Map<Boolean, List<Entry>> partitioned = records.stream().collect(Collectors.partitioningBy(e ->
                e.getSubjectType().equals(Entry.SubjectType.CURRENT)));

        Map<Entry.SubjectType, List<Entry>> map =
                Map.of(Entry.SubjectType.CURRENT, partitioned.get(true),
                Entry.SubjectType.PREMILLENIAL, partitioned.get(false));



        // type 1
        for (var quantifier : relativeQuantifiers) {
            for (var fuzzySet : combinations) {

                var s1 = new MultiSubjectSummaryTypeOne(
                        Entry.SubjectType.CURRENT,
                        Entry.SubjectType.PREMILLENIAL,
                        quantifier,
                        fuzzySet);

                var s2 = new MultiSubjectSummaryTypeOne(
                        Entry.SubjectType.PREMILLENIAL,
                        Entry.SubjectType.CURRENT,
                        quantifier,
                        fuzzySet);


                s1.calculateQualityMeasure(map);
                s2.calculateQualityMeasure(map);
                summaries.add(s1);
                summaries.add(s2);
            }
        }

        // type 2 & 3
        for (var quantifier : relativeQuantifiers) {
            for (var summarizer : combinations) {
                for (var qualifier : combinations) {
                    boolean disjoint = Collections.disjoint(summarizer.getFuzzySets(), qualifier.getFuzzySets());
                    if (qualifier != summarizer && disjoint) {
                        var s1 = new MultiSubjectSummaryTypeTwo(
                                Entry.SubjectType.PREMILLENIAL,
                                Entry.SubjectType.CURRENT,
                                quantifier,
                                summarizer,
                                qualifier);

                        var s2 = new MultiSubjectSummaryTypeTwo(
                                Entry.SubjectType.CURRENT,
                                Entry.SubjectType.PREMILLENIAL,
                                quantifier,
                                summarizer,
                                qualifier);

                        var s3 = new MultiSubjectSummaryTypeThree(
                                Entry.SubjectType.PREMILLENIAL,
                                Entry.SubjectType.CURRENT,
                                quantifier,
                                summarizer,
                                qualifier);


                        var s4 = new MultiSubjectSummaryTypeThree(
                                Entry.SubjectType.CURRENT,
                                Entry.SubjectType.PREMILLENIAL,
                                quantifier,
                                summarizer,
                                qualifier);

                        s1.calculateQualityMeasure(map);
                        s2.calculateQualityMeasure(map);
                        s3.calculateQualityMeasure(map);
                        s4.calculateQualityMeasure(map);
                        summaries.add(s1);
                        summaries.add(s2);
                        summaries.add(s3);
                        summaries.add(s4);
                    }
                }
            }
        }



        // type 4
        for (var fuzzySet : combinations) {
            var s1 = new MultiSubjectSummaryTypeFour(
                    Entry.SubjectType.CURRENT,
                    Entry.SubjectType.PREMILLENIAL,
                    fuzzySet);

            var s2 = new MultiSubjectSummaryTypeFour(
                    Entry.SubjectType.PREMILLENIAL,
                    Entry.SubjectType.CURRENT,
                    fuzzySet);

            s1.calculateQualityMeasure(map);
            s2.calculateQualityMeasure(map);
            summaries.add(s1);
            summaries.add(s2);
        }

        // sort by quality measure
        summaries.sort(Comparator.comparingDouble(MultiSubjectSummary::getQualityMeasure).reversed());
        return summaries;
    }

}
