package com.example.queryData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class queryData {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://103.138.113.158:5432/TNT30";
        String username = "postgres";
        String password = "tringhia@12345";
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

    public static String queryCountContracts(String sql) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        String data = "";
        while (rs.next()) {
            data = rs.getString(1);
            connection.close();
        }
        return data;
    }

    public static List<String> contractRecord() throws SQLException {
        Connection connection = getConnection();
        String query = "select \"ContractName\" from \"Contracts\"";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<String> list = new ArrayList<>();
        while (resultSet.next()) {
            String data = resultSet.getString(1);
            list.add(data);
        }
        return list;
    }
}

