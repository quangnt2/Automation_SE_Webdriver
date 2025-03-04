package com.example.queryData;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public static List<String> contractsListColum(String sql) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<String> list = new ArrayList<>();
        while (resultSet.next()) {
            String str = resultSet.getString(1);
            list.add(str);
            connection.close();
        }
        return list;
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
            connection.close();
        }
        return list;
    }

    public static List<String> suplierList(String sql) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<String> list = new ArrayList<>();
        while (resultSet.next()) {
            String data = resultSet.getString(1);
            list.add(data);
        }
        connection.close();
        return list;
    }

    public static List<String> getIdUserLogin(String email) throws SQLException {
        Connection connection = getConnection();
        String sql = "select \"Id\" from \"AbpUsers\" where \"Id\"  = ?";
        // String sql1 = "select * from \"AbpUsers\" where \"Id\" between 1 and 1000 order by \"Id\"ASC";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();
        List<String> listId = new ArrayList<>();
        while (resultSet.next()) {
            String data = resultSet.getString(1);
            listId.add(data);
        }
        connection.close();
        return listId;
    }

    public static List<String> listPotential() throws SQLException {
        Connection connection = getConnection();
        String sql = "select \"Name\" from \"Customers\" where \"Type\" = 1";
        PreparedStatement statement = getConnection().prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        List<String> listData = new ArrayList<>();
        while (rs.next()) {
            String data = rs.getString(1);
            listData.add(data);
        }
        connection.close();
        return listData;
    }

    public static void main(String[] args) throws SQLException {
        List<String> list = listPotential();
        for (String list1 : list) {
            System.out.println(list1);
        }
    }
}

