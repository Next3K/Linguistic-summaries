package project;

import org.example.model.summary.MultiSubjectSummary;
import org.example.model.summary.Summary;

public class Results {
    private String text;
    private String T;
    private String T1;
    private String T2;
    private String T3;
    private String T4;
    private String T5;
    private String T6;
    private String T7;
    private String T8;
    private String T9;
    private String T10;
    private String T11;


    public Results(Summary sum) {
        this.text = sum.getTextualRepresentation();
        this.T = String.format("%.2f", sum.getQualityMeasure());
        this.T1 = String.format("%.2f", sum.getDegreeOfTruth());
        this.T2 = String.format("%.2f", sum.getDegreeOfImprecision());
        this.T3 = String.format("%.2f", sum.getDegreeOfCovering());
        this.T4 = String.format("%.2f", sum.getDegreeOfAppropriateness());
        this.T5 = String.format("%.2f", sum.getLengthOfSummary());
        this.T6 = String.format("%.2f", sum.getDegreeOfQuantifierImprecision());
        this.T7 = String.format("%.2f", sum.getDegreeOfQuantifierCardinality());
        this.T8 = String.format("%.2f", sum.getDegreeOfSummarizerCardinality());
        this.T9 = String.format("%.2f", sum.getDegreeOfQualifierImprecision());
        this.T10 = String.format("%.2f", sum.getDegreeOfQualifierCardinality());
        this.T11 = String.format("%.2f", sum.getLengthOfQualifier());

    }

    public Results(MultiSubjectSummary sum) {
        this.text = sum.getTwoSubjectSummaryForTable();
        this.T = String.format("%.2f", sum.getQualityMeasure());
    }


    public String getText() {
        return this.text;
    }

    public String getT() {
        return T;
    }

    public String getT1() {
        return T1;
    }

    public String getT2() {
        return T2;
    }

    public String getT3() {
        return T3;
    }

    public String getT4() {
        return T4;
    }

    public String getT5() {
        return T5;
    }

    public String getT6() {
        return T6;
    }

    public String getT7() {
        return T7;
    }

    public String getT8() {
        return T8;
    }

    public String getT9() {
        return T9;
    }

    public String getT10() {
        return T10;
    }

    public String getT11() {
        return T11;
    }
}
