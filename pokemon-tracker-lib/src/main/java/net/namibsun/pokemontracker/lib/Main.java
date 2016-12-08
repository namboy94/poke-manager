package net.namibsun.pokemontracker.lib;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by hermann on 12/5/16.
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        System.out.println(1);

        String url = "jdbc:sqlite:/home/hermann/test.db";
        Connection conn = DriverManager.getConnection(url);

        conn.nativeSQL("Create TABLE test (x INTEGER )");
        conn.close();

    }
}
