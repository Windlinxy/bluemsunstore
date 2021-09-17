package com.bluemsun.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class C3P0Utils {
    private static Connection conn;

    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    public ComboPooledDataSource getDataSource() {
        return dataSource;
    }

    public static Connection getConnection() {
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public static void releaseConnection(AutoCloseable... autoCloseables) {
        try {
            for (AutoCloseable a : autoCloseables) {
                if (a != null) {
                    a.close();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}