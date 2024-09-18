package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {

    private static Connection conn;

    public static Connection getConn() {
        try {
//            Class.forName("com.sqlserver.cj.jdbc.Driver");
//            con = DriverManager.getConnection("jdbc:sqlserver://THAWGXD\\THANG:53042/CDStation",
//                     "root", "password");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\THANG:1433;databaseName=CDStation", "sa", "123");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
