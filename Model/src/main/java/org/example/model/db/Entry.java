package org.example.model.db;

import lombok.Getter;

import java.util.Map;

@Getter
public class Entry {
    public Entry(SubjectType subjectType, Map<DatabaseColumn, Double> values) {
        if (subjectType == null) throw new IllegalArgumentException("Subject type cannot be null");
        if (values.size() == 0) throw new IllegalArgumentException("At least one attribute has to be chosen");
        this.subjectType = subjectType;
        this.values = values;
        // Map.of(DatabaseColumn.AFTERNOON_HUMIDITY, 10d, DatabaseColumn.INSOLATION, 12d);
    }

    private SubjectType subjectType;
    private Map<DatabaseColumn, Double> values;

    public enum SubjectType {
        CURRENT, PREMILLENIAL
    }

    public enum DatabaseColumn {
        MAX_TEMPERATURE, MIN_TEMPERATURE, MORNING_HUMIDITY, AFTERNOON_HUMIDITY, WIND, RAINFALL, INSOLATION, EVAPORATION,
        RADIATION, EVAPOTRANSPIRATION;

        public String databaseColumnName() {
            return switch (this) {
                case MAX_TEMPERATURE -> "maxTemperature";
                case MIN_TEMPERATURE -> "minTemperature";
                case MORNING_HUMIDITY -> "morningHumidity";
                case AFTERNOON_HUMIDITY -> "afternoonHumidity";
                case WIND -> "wind";
                case RAINFALL -> "rainfall";
                case INSOLATION -> "insolation";
                case EVAPORATION -> "evaporation";
                case RADIATION -> "radiation";
                case EVAPOTRANSPIRATION -> "evapotranspiration";
            };
        }
    }
}
