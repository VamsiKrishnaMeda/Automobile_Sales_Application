package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import sample.util.DBUtil;

import java.sql.*;

public class MarketingOfficeController {

    @FXML
    private TextArea result_console;

    public void queryTwo() {

        try {
            Class.forName(DBUtil.JDBC_DRIVER);
            try {
                Connection connection = DriverManager.getConnection(DBUtil.CONN_STRING, DBUtil.USER_NAME, DBUtil.PASS);
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("WITH IncomeRange AS ( SELECT\n" +
                        "                      CASE\n" +
                        "                      WHEN Annual_Income BETWEEN 10000 AND 400000 THEN '10000 and 400000'\n" +
                        "                      WHEN Annual_Income BETWEEN 400001 AND 800000 THEN '400001 and 800000'\n" +
                        "                      END AS RANGE\n" +
                        "                      FROM customer )\n" +
                        "SELECT EXTRACT (YEAR FROM Sale_Date) AS Year_Trend, customer.Gender, IncomeRange.Range, SUM(sale.Sale_Price)\n" +
                        "FROM brand, model, vehicle, customer, sale, IncomeRange\n" +
                        "WHERE brand.Name = model.Brand_Name AND model.Name = vehicle.Model_Name AND model.Body_Style = vehicle.Body_Style AND model.Year = vehicle.Year AND vehicle.VIN = sale.VIN AND Sale_Date BETWEEN TO_DATE('01-JAN-2016') AND TO_DATE('31-DEC-2018')\n" +
                        "GROUP BY EXTRACT(YEAR FROM Sale_Date), customer.Gender, IncomeRange.range\n" +
                        "ORDER BY Year_Trend ASC");

                displayResult(result);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void queryThree() {

        try {
            Class.forName(DBUtil.JDBC_DRIVER);
            try {
                Connection connection = DriverManager.getConnection(DBUtil.CONN_STRING, DBUtil.USER_NAME, DBUtil.PASS);
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("WITH top_brand AS (SELECT brand.Name, SUM(sale.Sale_Price) AS Dollar_Amount\n" +
                        "                   FROM brand, model, sale, vehicle\n" +
                        "                   WHERE brand.Name = model.Brand_Name AND model.Name = vehicle.Model_Name AND model.Body_Style = vehicle.Body_Style AND model.Year = vehicle.Year AND vehicle.VIN = sale.VIN AND EXTRACT(YEAR FROM Manufacture_Date) = 2018\n" +
                        "                   GROUP BY brand.Name)\n" +
                        "SELECT name AS Top_Brand_By_Dollars\n" +
                        "FROM top_brand\n" +
                        "WHERE Dollar_Amount = (SELECT MAX(Dollar_Amount)\n" +
                        "                       FROM top_brand)");

                displayResult(result);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void queryFour() {

        try {
            Class.forName(DBUtil.JDBC_DRIVER);
            try {
                Connection connection = DriverManager.getConnection(DBUtil.CONN_STRING, DBUtil.USER_NAME, DBUtil.PASS);
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("WITH top_dealer AS (SELECT dealership.Name, SUM(sale.Sale_Price) AS Dollar_Amount\n" +
                        "                    FROM dealership, model, sale, vehicle\n" +
                        "                    WHERE dealership.ID = sale.Dealer_ID AND EXTRACT(YEAR FROM Sale.Sale_Date) = 2018\n" +
                        "                    GROUP BY dealership.Name)\n" +
                        "SELECT Name AS Top_Dealer_By_Dollars\n" +
                        "FROM top_dealer\n" +
                        "WHERE Dollar_Amount = (SELECT MAX(Dollar_Amount)\n" +
                        "                       FROM top_dealer)");

                displayResult(result);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void queryFive() {

        try {
            Class.forName(DBUtil.JDBC_DRIVER);
            try {
                Connection connection = DriverManager.getConnection(DBUtil.CONN_STRING, DBUtil.USER_NAME, DBUtil.PASS);
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("WITH unit_sales AS (SELECT brand.Name, COUNT(*) AS Units_Sold\n" +
                        "                    FROM brand, model, vehicle, sale\n" +
                        "                    WHERE brand.Name = model.Brand_Name AND model.Name = vehicle.Model_Name AND model.Body_Style = vehicle.Body_Style AND model.Year = vehicle.Year AND vehicle.VIN = sale.VIN AND EXTRACT(YEAR FROM sale.Sale_Date) = 2018\n" +
                        "                    GROUP BY brand.Name)\n" +
                        "SELECT Name AS Top_Brand_By_Units\n" +
                        "FROM unit_sales\n" +
                        "WHERE Units_Sold = (SELECT MAX(Units_Sold)\n" +
                        "                    FROM unit_sales)");

                displayResult(result);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void querySix() {

        try {
            Class.forName(DBUtil.JDBC_DRIVER);
            try {
                Connection connection = DriverManager.getConnection(DBUtil.CONN_STRING, DBUtil.USER_NAME, DBUtil.PASS);
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("WITH convertible_month AS (SELECT EXTRACT(MONTH FROM sale.Sale_Date) AS Sale_Month, COUNT(*) AS Convertibles_Sold\n" +
                        "                           FROM sale NATURAL JOIN vehicle\n" +
                        "                           WHERE Body_Style = 'Convertible'\n" +
                        "                           GROUP BY EXTRACT(MONTH FROM sale.Sale_Date))\n" +
                        "SELECT Sale_Month\n" +
                        "FROM convertible_month\n" +
                        "WHERE Convertibles_Sold = (SELECT MAX(Convertibles_Sold)\n" +
                        "                           FROM convertible_month)");

                displayResult(result);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void querySeven() {

        try {
            Class.forName(DBUtil.JDBC_DRIVER);
            try {
                Connection connection = DriverManager.getConnection(DBUtil.CONN_STRING, DBUtil.USER_NAME, DBUtil.PASS);
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("WITH durations AS (SELECT Dealer_ID, AVG(sale.Sale_Date - inventory.inventory_date) AS Hold_Duration\n" +
                        "                   FROM inventory NATURAL JOIN sale\n" +
                        "                   GROUP BY Dealer_ID)\n" +
                        "SELECT Name AS Dealership_Name\n" +
                        "FROM durations, dealership\n" +
                        "WHERE durations.Dealer_ID = dealership.ID AND Hold_Duration = (SELECT MAX(Hold_Duration)\n" +
                        "                                                               FROM durations)");

                displayResult(result);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void displayResult(ResultSet result) throws SQLException {

        result_console.clear();

        ResultSetMetaData data = result.getMetaData();
        int count = data.getColumnCount();
        for (int i = 1; i <= count; i++) {
            result_console.appendText(data.getColumnName(i) + "\t");
        }
        result_console.appendText("\n");

        while (result.next()) {
            for (int i = 1; i <= count; i++) {
                result_console.appendText(result.getString(i) + "\t");
                if (i == 1)
                    result_console.appendText("\t");
            }
            result_console.appendText("\n");
        }

    }

}