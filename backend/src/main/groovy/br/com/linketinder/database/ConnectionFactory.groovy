package br.com.linketinder.database

import javax.xml.crypto.Data
import java.sql.Connection
import java.sql.DriverManager
import java.util.logging.Logger

class ConnectionFactory {
    private static final Logger logger = Logger.getLogger(ConnectionFactory.class.getName())

    private static final Map<DatabaseType, Closure<Connection>> FACTORIES = [
            (DatabaseType.POSTGRESQL): {
                String url = System.getenv("DB_URL") ?: "jdbc:postgresql://localhost:5432/linketinder"
                String user = System.getenv("DB_USER") ?: "admin"
                String senha = System.getenv("DB_PASSWORD") ?: "123456"
                return DriverManager.getConnection(url, user, senha)
            },
            (DatabaseType.MYSQL): {
                String url = System.getenv("DB_URL") ?: "jdbc:mysql://localhost:3306/linketinder"
                String user = System.getenv("DB_USER") ?: "admin"
                String senha = System.getenv("DB_PASSWORD") ?: "123456"
                return DriverManager.getConnection(url, user, senha)
            }
    ]

    static Connection criar(DatabaseType tipo) {
        try{
            Closure<Connection> factory = FACTORIES[tipo]
            if (!factory){
                throw new IllegalArgumentException("Banco de dados ${tipo} não suportado")
            }
            return factory()
        } catch (Exception e) {
            logger.severe("Erro ao criar conexão com o banco ${tipo}: ${e.printStackTrace()}")
            throw new RuntimeException("Falha ao conectar com o banco de dados", e)
        }
    }
}
