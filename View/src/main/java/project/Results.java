package project;

import org.example.model.statements.Summary;

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
        this.T = sum.getQualityMeasure().toString();
        this.T1 = sum.getDegreeOfTruth().toString();
        this.T2 = sum.getDegreeOfImprecision().toString();
        this.T3 = sum.getDegreeOfCovering().toString();
        this.T4 = sum.getDegreeOfAppropriateness().toString();
        this.T5 = sum.getLengthOfSummary().toString();
        this.T6 = sum.getDegreeOfQualifierImprecision().toString();
        this.T7 = sum.getDegreeOfQualifierCardinality().toString();
        this.T8 = sum.getDegreeOfSummarizerCardinality().toString();
        this.T9 = sum.getDegreeOfQualifierImprecision().toString();
        this.T10 = sum.getDegreeOfQualifierCardinality().toString();
        this.T11 = sum.getLengthOfQualifier().toString();

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
