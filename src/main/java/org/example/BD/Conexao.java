package org.example.BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String url = "jdbc:mysql://localhost:3306/saude";
    private static final String user = "root";
    private static final String password = "1234";

    private static Connection connection;

    private Conexao(){

    }

    public static Connection getConexao() throws SQLException {
        if (connection == null) {
            return connection = DriverManager.getConnection(url, user, password);
        }else
            return connection;
    }
}
