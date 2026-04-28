package br.com.linketinder.service

import br.com.linketinder.model.Empresa
import br.com.linketinder.dao.EmpresaDAO
import br.com.linketinder.database.ConexaoDB
import groovy.sql.Sql
import java.sql.Connection

class EmpresaService {

    private EmpresaDAO empresaDAO

    EmpresaService() {
        Connection connection = ConexaoDB.getConnection()
        Sql sql = new Sql(connection)
        this.empresaDAO = new EmpresaDAO(sql)
    }

    boolean adicionar(Empresa empresa) {
        return empresaDAO.inserir(empresa)
    }

    List<Empresa> listarTodos() {
        return empresaDAO.listarTodos()
    }
}