package com.belong.db;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by belong on 16-11-5.
 */
public class DBCP {
    private static Connection conn;
    private static InputStream is;
    private static DataSource ds;
    private static Properties pro;
    static {
        is = DBCP.class.getClassLoader().getResourceAsStream("dbcp.ini");
        pro = new Properties();
        try {
            pro.load(is);
            ds = BasicDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for(int i = 2;i<3;i++){
            System.out.println(i);
        }
    }
    public static Connection getConnection(){
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
