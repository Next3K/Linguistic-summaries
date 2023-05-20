package org.example.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

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
    private static final String DB_URL = "jdbc:sqlite:Model/src/main/resources/ksr_weather.db";
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

    public void selectAllRows() {
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM weather;");
            while(result.next()) {
                //Add data to
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
