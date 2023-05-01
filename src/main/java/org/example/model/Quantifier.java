package org.example.model;

public interface Quantifier {

    String getTextualRepresentation();

    // value from 0 to 1
    Double getQuantified(Double d);

    Boolean isRelative();
}
