package br.com.linketinder.dao

import groovy.sql.Sql
import groovy.sql.GroovyRowResult
import br.com.linketinder.model.Empresa

class EmpresaDAO {

    private Sql sql

    EmpresaDAO(Sql sql) {
        this.sql = sql
    }

    Integer inserir(Empresa empresa) {
        GroovyRowResult row = sql.firstRow('''
            INSERT INTO empresa (nome, cnpj, email, pais, estado, cidade, cep, descricao, senha)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
            RETURNING id
        ''', [empresa.nome, empresa.cnpj, empresa.email, empresa.pais, empresa.estado, empresa.cidade, empresa.cep, empresa.descricao, empresa.senha])

        return row?.id as Integer
    }

    List<Empresa> listarTodos() {
        List<GroovyRowResult> rows = sql.rows('SELECT * FROM empresa')

        return rows.collect { GroovyRowResult r ->
            new Empresa(
                    id: r.id as Integer,
                    nome: r.nome as String,
                    email: r.email as String,
                    cnpj: r.cnpj as String,
                    pais: r.pais as String,
                    estado: r.estado as String,
                    cidade: r.cidade as String,
                    cep: r.cep as String,
                    descricao: r.descricao as String,
                    senha: r.senha as String
            )
        }
    }

    Empresa buscarPorId(Integer id) {
        GroovyRowResult row = sql.firstRow('SELECT * FROM empresa WHERE id = ?', [id])
        if (!row){
            return null
        }

        return new Empresa(
                id: row.id as Integer,
                nome: row.nome as String,
                email: row.email as String,
                cnpj: row.cnpj as String,
                pais: row.pais as String,
                estado: row.estado as String,
                cidade: row.cidade as String,
                cep: row.cep as String,
                descricao: row.descricao as String,
                senha: row.senha as String
        )
    }

    Integer update(Empresa empresa) {
        sql.executeUpdate('''
        UPDATE empresa SET
            nome = ?, email = ?, cnpj = ?, pais = ?, estado = ?, cidade = ?, cep = ?, descricao = ?, senha = ?,
            updated_at = NOW()
        WHERE id = ?
    ''', [empresa.nome, empresa.email, empresa.cnpj, empresa.pais, empresa.estado, empresa.cidade, empresa.cep, empresa.descricao, empresa.senha, empresa.id])
        return empresa.id
    }

    boolean delete(Integer id) {
        Integer isDeleted = sql.executeUpdate('DELETE FROM empresa WHERE id = ?', [id])
        return isDeleted > 0
    }
}