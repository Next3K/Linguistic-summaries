package org.example.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

//Database schema
//-------------------------------------
//CREATE TABLE weather (
//        station VARCHAR(255) NOT NULL,
//        date DATE NOT NULL,
//        max_t FLOAT NOT NULL,
//        min_t FLOAT NOT NULL,
//        rh1 INT NOT NULL,
//        rh2 INT NOT NULL,
//        wind FLOAT NOT NULL,
//        rain FLOAT NOT NULL,
//        ssh FLOAT NOT NULL,
//        evap FLOAT NOT NULL,
//        radiation FLOAT NOT NULL,
//        fao56_et FLOAT NOT NULL,
//        lat FLOAT NOT NULL,
//        lon FLOAT NOT NULL,
//        cum_rain FLOAT NOT NULL
//        );



public class Connect {
    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:Model/src/main/java/org/example/model/db/ksr_weather.db";
    private Connection conn;
    private Statement stat;

    public Connect() {
        try {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }
    }

    public List<Entry> selectAllRows() {
        List<Entry> entries = new ArrayList<Entry>();
        int i = 0;
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM weather WHERE date < '2000-01-01';");
            while(result.next()) {
                entries.add(new Entry(
                        Entry.SubjectType.PREMILLENIAL,
                        Map.of(
                        Entry.DatabaseColumn.MAX_TEMPERATURE, result.getDouble("max_t"),
                        Entry.DatabaseColumn.MIN_TEMPERATURE, result.getDouble("min_t"),
                        Entry.DatabaseColumn.MORNING_HUMIDITY, result.getDouble("rh1"),
                        Entry.DatabaseColumn.AFTERNOON_HUMIDITY, result.getDouble("rh2"),
                        Entry.DatabaseColumn.WIND, result.getDouble("wind"),
                        Entry.DatabaseColumn.RAINFALL, result.getDouble("rain"),
                        Entry.DatabaseColumn.INSOLATION, result.getDouble("ssh"),
                        Entry.DatabaseColumn.EVAPORATION, result.getDouble("evap"),
                        Entry.DatabaseColumn.RADIATION, result.getDouble("radiation"),
                        Entry.DatabaseColumn.EVAPOTRANSPIRATION, result.getDouble("fao56_et")
                        )));
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM weather WHERE date >= '2000-01-01';");
            while(result.next()) {
                entries.add(new Entry(
                        Entry.SubjectType.CURRENT,
                        Map.of(
                                Entry.DatabaseColumn.MAX_TEMPERATURE, result.getDouble("max_t"),
                                Entry.DatabaseColumn.MIN_TEMPERATURE, result.getDouble("min_t"),
                                Entry.DatabaseColumn.MORNING_HUMIDITY, result.getDouble("rh1"),
                                Entry.DatabaseColumn.AFTERNOON_HUMIDITY, result.getDouble("rh2"),
                                Entry.DatabaseColumn.WIND, result.getDouble("wind"),
                                Entry.DatabaseColumn.RAINFALL, result.getDouble("rain"),
                                Entry.DatabaseColumn.INSOLATION, result.getDouble("ssh"),
                                Entry.DatabaseColumn.EVAPORATION, result.getDouble("evap"),
                                Entry.DatabaseColumn.RADIATION, result.getDouble("radiation"),
                                Entry.DatabaseColumn.EVAPOTRANSPIRATION, result.getDouble("fao56_et")
                        )));
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entries;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Problem z zamknieciem polaczenia");
            e.printStackTrace();
        }
    }


}
