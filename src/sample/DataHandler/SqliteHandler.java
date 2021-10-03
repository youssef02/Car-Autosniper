package sample.DataHandler;

import java.sql.*;

public class SqliteHandler {
    private final String dbName = "database.db";
    private Connection conn = null;
    private Statement stat = null;

    public SqliteHandler() throws SQLException, ClassNotFoundException{
        String URL = "jdbc:sqlite:" + dbName;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection(URL);
        init();
    }

    private void init() throws SQLException {
        initsettings();
        initfilter();
        initcardata();
        initconfig();

    }

    private void initconfig() throws SQLException {
        stat = conn.createStatement();
        ResultSet r = stat.executeQuery("SELECT * FROM sqlite_master WHERE type='table' AND name = 'Config' ");
        if (!r.next()) {
            stat.execute("CREATE TABLE Config(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " Name VARCHAR(25) NOT NULL," +
                    " Domain VARCHAR(50) NOT NULL," +
                    " Link VARCHAR(50) );");

        } else {
            System.out.print(r.getString("name")+" Created!\n");
        }
    }

    private void initcardatainfo() throws SQLException {
        stat = conn.createStatement();
        ResultSet r = stat.executeQuery("SELECT * FROM sqlite_master WHERE type='table' AND name = 'CarDataInfo' ");
        if (!r.next()) {
            stat.execute("CREATE TABLE CarDataInfo(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " Tel VARCHAR(25) NOT NULL," +
                    " Owner VARCHAR(50) NOT NULL," +
                    " Link VARCHAR(50) );");

        } else {
            System.out.print(r.getString("name")+" Created!\n");
        }
    }

    private void initcardatadescription() throws SQLException {
        stat = conn.createStatement();
        ResultSet r = stat.executeQuery("SELECT * FROM sqlite_master WHERE type='table' AND name = 'CarDataDescription' ");
        if (!r.next()) {
            stat.execute("CREATE TABLE CarDataDescription(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " Title VARCHAR(100) NOT NULL," +
                    " Carmodel VARCHAR(50) NOT NULL," +
                    " Color VARCHAR(25) NOT NULL," +
                    " Manufacturer VARCHAR(25) NOT NULL," +
                    " EngineType VARCHAR(25) NOT NULL);");

        } else {
            System.out.print(r.getString("name")+" Created!\n");
        }
    }

    private void initcardata() throws SQLException {
        stat = conn.createStatement();
        ResultSet r = stat.executeQuery("SELECT * FROM sqlite_master WHERE type='table' AND name = 'CarData' ");
        if (!r.next()) {
            //INIT FOREIGN KEYS FIRST

            initcardatadescription();
            initcardatainfo();

            //then create CarData
            stat.execute("CREATE TABLE CarData(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " CDD INTEGER NOT NULL," +
                    " CDI INTEGER NOT NULL," +
                    " Filter INTEGER NOT NULL," +
                    " IMG VARCHAR(100) NOT NULL,"+
                    " FOREIGN KEY(CDD) REFERENCES CarDataDescription(ID)," +
                    " FOREIGN KEY(CDI) REFERENCES CarDataInfo(ID),"+
                    " FOREIGN KEY(Filter) REFERENCES Filter(ID));");

        } else {
            System.out.print(r.getString("name")+" Created!\n");
        }
    }

    private void initfilter() throws SQLException {
        stat = conn.createStatement();
        ResultSet r = stat.executeQuery("SELECT * FROM sqlite_master WHERE type='table' AND name = 'Filter' ");
        if (!r.next()) {
            stat.execute("CREATE TABLE Filter(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " Name VARCHAR(50) NOT NULL," +
                    " Status INTEGER(1) DEFAULT 0);");

        } else {
            System.out.print(r.getString("name")+" Created!\n");
        }
    }

    private void initsettings() throws SQLException {
        stat = conn.createStatement();
        ResultSet r = stat.executeQuery("SELECT * FROM sqlite_master WHERE type='table' AND name = 'settings' ");
        if (!r.next()) {
            stat.execute("CREATE TABLE settings(ID INTEGER PRIMARY KEY AUTOINCREMENT, KEY VARCHAR(50) NOT NULL, VALUE VARCHAR(50) NOT NULL);");

        } else {
            System.out.print(r.getString("name")+" Created!\n");
        }
    }

    public void Close() throws SQLException {
        stat.close();
        conn.close();
    }
}
