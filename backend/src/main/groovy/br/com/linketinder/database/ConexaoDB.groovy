package br.com.linketinder.database

import java.sql.Connection
import java.sql.DriverManager

class ConexaoDB {
    static Connection getConnection() {
        String url = "jdbc:postgresql://localhost:5432/linketinder"
        String user = "admin"
        String password = "123456"
        Connection connection = DriverManager.getConnection(url, user, password)
        connection.createStatement().execute("SET timezone = 'America/Sao_Paulo'")
        return connection
    }
}

