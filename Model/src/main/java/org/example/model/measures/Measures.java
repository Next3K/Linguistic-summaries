package org.example.model.measures;

import org.example.model.quantifiers.Quantifier;
import org.example.model.statements.Statement;
import org.example.model.statements.TwoSubjectStatement;

public class Measures {

    public static double calculateDegreeOfTruth(Statement statement) {
        Quantifier quantifier = statement.getQuantifier();
        double calculatedValue = 0.1d; // TODO implement
        return quantifier.getQuantified(calculatedValue);
    }

    public static double calculateDegreeOfTruth(TwoSubjectStatement statement) {
        Quantifier quantifier = statement.getQuantifier();
        double calculatedValue = 0.1d; // TODO implement
        return quantifier.getQuantified(calculatedValue);
    }

    public static double calculateDegreeOfImprecision(Statement statement) {
        return 0;
    }

    public static double calculateDegreeOfCovering(Statement statement) {
        return 0;
    }

    public static double calculateDegreeOfAppropriateness(Statement statement) {
        return 0;
    }

    public static double calculateLengthOfSummary(Statement statement) {
        return 0;
    }

    public static double calculateOptimalSummary(Statement statement) {
        return 0;
    }

    public static double calculateDegreeOfQuantifierImprecision(Statement statement) {
        return 0;
    }

    public static double calculateDegreeOfQuantifierCardinality(Statement statement) {
        return 0;
    }

    public static double calculateDegreeOfQualifierCardinality(Statement statement) {
        return 0;
    }

    public static double calculateDegreeOfQualifierImprecision(Statement statement) {
        return 0;
    }

    public static double calculateLengthOfQualifier(Statement statement) {
        return 0;
    }

    public static double calculateWeightedMeasure(Statement statement) {
        return 0.30 * Measures.calculateDegreeOfTruth(statement) +
                0.07 * Measures.calculateDegreeOfImprecision(statement) +
                0.07 * Measures.calculateDegreeOfCovering(statement) +
                0.07 * Measures.calculateDegreeOfAppropriateness(statement) +
                0.07 * Measures.calculateLengthOfSummary(statement) +
                0.07 * Measures.calculateOptimalSummary(statement) +
                0.07 * Measures.calculateDegreeOfQuantifierImprecision(statement) +
                0.07 * Measures.calculateDegreeOfQuantifierCardinality(statement) +
                0.07 * Measures.calculateDegreeOfQualifierCardinality(statement) +
                0.07 * Measures.calculateDegreeOfQualifierImprecision(statement) +
                0.07 * Measures.calculateLengthOfQualifier(statement);
    }

}
