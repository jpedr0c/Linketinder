package br.com.linketinder.database

import java.sql.Connection
import java.util.logging.Logger

class ConexaoDB {

    private static final Logger logger = Logger.getLogger(ConexaoDB.class.getName())
    private static final DatabaseType BANCO_ATIVO = DatabaseType.POSTGRESQL

    private static Connection instancia

    private ConexaoDB () {

    }

    static synchronized Connection getConnection() {
        if (instancia == null || instancia.isClosed()) {
            logger.info("Criando nova conexão com o banco: ${BANCO_ATIVO}")
            instancia = ConnectionFactory.criar(BANCO_ATIVO)
        }
        return instancia
    }

    static synchronized void fechar() {
        if (instancia != null && !instancia.isClosed()) {
            instancia.close()
            instancia = null
            logger.info("Conexão encerrada")
        }
    }
}

