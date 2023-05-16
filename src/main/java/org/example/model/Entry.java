package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Entry {
    private SubjectType subjectType;
    private Double maxTemperature;
    private Double minTemperature;
    private Double morningHumidity;
    private Double afternoonHumidity;
    private Double wind;
    private Double rainfall;
    private Double insolation;
    private Double evaporation;
    private Double radiation;
    private Double evapotranspiration;

    public enum SubjectType {
        CURRENT, PREMILLENIAL
    }
}
