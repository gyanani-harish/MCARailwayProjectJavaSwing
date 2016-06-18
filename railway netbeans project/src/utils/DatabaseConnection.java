/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import beans.TrainStationInfoBean;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Harish
 */
public class DatabaseConnection {
//tables

    public static final String TABLE_STATION_CODE_AND_NAME = "station_code_and_name";
    public static final String TABLE_STATION_AND_TRAIN = "station_and_train";
    public static final String TABLE_TRAIN_INFO = "train_info";
    public static final String TABLE_TRAIN_AND_DAYS = "train_and_days";
    public static final String TABLE_TRAIN_AND_CLASS = "train_and_class";
    public static final String TABLE_CLASS = "class";
    public static final String TABLE_TRAIN_TYPE = "train_type";
    private static final String CLASS_TO_LOAD = "java.sql.DriverManager";
    private static final String CONNECTION_INFO_DRIVER = "jdbc";
    private static final String CONNECTION_INFO_DB_VENDOR = "mysql";
    private static final String CONNECTION_INFO_HOSTNAME = "localhost";
    private static final int CONNECTION_INFO_PORT = 3306;
    private static final String DATABASE_NAME = "railway";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "123";
    private static Connection connection = null;
    private static DatabaseConnection instance = null;
    private static Statement statement = null;
    private static final String completeConnectionString = CONNECTION_INFO_DRIVER + ":" + CONNECTION_INFO_DB_VENDOR + "://"
            + CONNECTION_INFO_HOSTNAME + ":" + CONNECTION_INFO_PORT + "/" + DATABASE_NAME;

    protected DatabaseConnection() {
        // Exists only to defeat instantiation.
    }

    private static DatabaseConnection getDBConnection() {
        try {
            Class.forName(CLASS_TO_LOAD);
            if (connection == null) {
                connection = (Connection) DriverManager.getConnection(completeConnectionString, USER_NAME, PASSWORD);
            }
            if (statement == null) {
                statement = (Statement) connection.createStatement();
            }
            if (instance == null) {
                instance = new DatabaseConnection();
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return instance;
    }

    public static void closeConnection() {
        connection = null;
        statement = null;
        instance = null;
    }

    public static int executeUpdate(String query) {
        getDBConnection();
        int updatedRows = -1;
        try {
            updatedRows = statement.executeUpdate(query);
        } catch (SQLException e) {
            updatedRows = -1;
            System.out.println(e.getMessage());
        }
        closeConnection();
        return updatedRows;
    }

    public static ResultSet executeQuery(String query) {
        getDBConnection();
        ResultSet res = null;
        try {
            res = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        closeConnection();
        return res;
    }

    public static boolean execute(String query) {
        getDBConnection();
        boolean res = true;
        try {
            res = statement.execute(query);
        } catch (SQLException e) {
            if (e.getMessage().startsWith("Duplicate entry")) {
                JOptionPane.showMessageDialog(null, "Duplicate entry");
            }
            System.out.println("hh" + e.getMessage());
        }
        closeConnection();
        return res;
    }

    public static void prepareStatementForInsertStations(ArrayList<TrainStationInfoBean> list) {
        getDBConnection();
        String query = "Insert into " + DatabaseConnection.TABLE_STATION_AND_TRAIN + "(station_code,train_no,station_order,time) "
                + "values(?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            for (int i = 0; i < list.size(); i++) {
                preparedStatement.setString(1, list.get(i).getStationCode());
                preparedStatement.setInt(2, list.get(i).getTrainNo());
                preparedStatement.setInt(3, list.get(i).getStationOrder());
                preparedStatement.setString(4, list.get(i).getTime());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                System.out.println("prepare statement exception" + ex.getMessage());
            }
        }
        closeConnection();
    }
}
