package sample.util;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;

public class DBUtil {

    public static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String CONN_STRING= "jdbc:oracle:thin:@citdb.nku.edu:1521:csc450";
    public static final String USER_NAME = "medav1";
    public static final String PASS = "csc222";
    public static Connection connection = null;

    public static void  dbConnect() throws SQLException, ClassNotFoundException {

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            throw e;
        }
        System.out.println("Oracle JDBC Driver Registered!");
        try {
            connection = DriverManager.getConnection(CONN_STRING, USER_NAME, PASS);
        } catch (SQLException e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
            throw e;
        }

    }

    public static void dbDisconnect() throws SQLException {
        try {
            if(connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {

        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;

        try {
            System.out.println("Select statement: " + queryStmt + "\n");
            stmt = connection.createStatement();
            resultSet = stmt.executeQuery(queryStmt);
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem encountered at executeQuery operation: " + e);
            throw e;
        } finally {
            if (resultSet != null)
                resultSet.close();
            if (stmt != null)
                stmt.close();
        }
        return crs;

    }

    public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException{

        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem occured at executeUpdate operation: " + e);
            throw e;
        } finally {
            if (stmt != null)
                stmt.close();
        }
        dbDisconnect();

    }

}