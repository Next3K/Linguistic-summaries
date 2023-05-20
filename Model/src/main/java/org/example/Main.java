package org.example;

import org.example.model.db.Entry;
import org.example.model.sets.Summarizer;
import org.example.model.LinguisticVariable;
import org.example.model.quantifiers.Quantifier;
import org.example.model.statements.Statement;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Map<LinguisticVariable, Entry.DatabaseColumn> columnsCorrespondingToVariables =
                Util.getDefaultLinguisticVariables();

        // load database, chose which columns are important
        List<Entry> records = Util.loadFromDatabase(
                Set.of(Entry.DatabaseColumn.MAX_TEMPERATURE,
                        Entry.DatabaseColumn.MIN_TEMPERATURE,
                        Entry.DatabaseColumn.EVAPOTRANSPIRATION,
                        Entry.DatabaseColumn.RADIATION));

        List<Quantifier> relativeQuantifiers = Util.loadDefaultRelativeQuantifiers();
        List<Quantifier> absoluteQuantifiers = Util.loadDefaultAbsoluteQuantifiers();

        // chosen attributes with their chosen summarizers
        Map<Entry.DatabaseColumn, List<Summarizer>> summarizers = Util.chooseAttributesAndTheirSummarizers();

        // generate statements
        List<Statement> statements =
                Generator.generateStatements(records, relativeQuantifiers, absoluteQuantifiers, summarizers);

        // sort by degree of truth
        statements.sort(Comparator.comparingDouble(Statement::getQualityMeasure));

        // print
        statements.forEach(System.out::println);
    }
}