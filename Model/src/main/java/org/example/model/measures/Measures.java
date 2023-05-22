package org.example.model.measures;

import org.example.model.db.Entry;
import org.example.model.quantifiers.Quantifier;
import org.example.model.statements.FirstTypeSummary;
import org.example.model.statements.SecondTypeSummary;
import org.example.model.statements.Summary;
import org.example.model.statements.TwoSubjectSummary;

import java.util.List;

public class Measures {

    public static double calculateDegreeOfTruth(Summary statement) {
        Quantifier quantifier = statement.getQuantifier();
        double calculatedValue = 0.1d; // TODO implement
        return quantifier.getQuantified(calculatedValue);
    }

    public static double calculateDegreeOfTruth(TwoSubjectSummary statement) {
        double calculatedValue = 0.1d; // TODO implement
        return calculatedValue;
    }

    public static double calculateDegreeOfImprecision(Summary statement,List<Entry> entries) {
        return 0;
    }

    public static double calculateDegreeOfCovering(FirstTypeSummary statement, List<Entry> entries) {
        return 0;
    }

    public static double calculateDegreeOfCovering(SecondTypeSummary statement, List<Entry> entries) {
        return 0;
    }

    public static double calculateDegreeOfAppropriateness(Summary statement, List<Entry> entries) {
        return 0;
    }

    public static double calculateLengthOfSummary(Summary statement, List<Entry> entries) {
        return 0;
    }

    public static double calculateOptimalSummary(Summary statement, List<Entry> entries) {
        return 0;
    }

    public static double calculateDegreeOfQuantifierImprecision(Summary statement, List<Entry> entries) {
        return 0;
    }

    public static double calculateDegreeOfQuantifierCardinality(Summary statement, List<Entry> entries) {
        return 0;
    }

    public static double calculateDegreeOfQualifierCardinality(Summary statement, List<Entry> entries) {
        return 0;
    }

    public static double calculateDegreeOfQualifierImprecision(Summary statement, List<Entry> entries) {
        return 0;
    }

    public static double calculateLengthOfQualifier(Summary statement, List<Entry> entries) {
        return 0;
    }

}
