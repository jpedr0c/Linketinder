package br.com.linketinder.dao

import br.com.linketinder.model.Competencia
import groovy.sql.Sql
import groovy.sql.GroovyRowResult

class CompetenciaDAO {

    private Sql sql

    CompetenciaDAO(Sql sql){
        this.sql = sql
    }

    Integer inserir(Competencia competencia) {
        GroovyRowResult row = sql.firstRow('''
            INSERT INTO competencia (nome)
            VALUES (?)
            RETURNING id
        ''', [competencia.nome])

        return row?.id as Integer
    }

    List<Competencia> listarTodos() {
        List<GroovyRowResult> rows = sql.rows('SELECT * FROM competencia')

        rows.collect { r ->
            new Competencia(
                    id: r.id as Integer,
                    nome: r.nome as String
            )
        }
    }

    Competencia buscarPorId(Integer id) {
        GroovyRowResult row = sql.firstRow('SELECT * FROM competencia WHERE id = ?', [id])
        if (!row) {
            return null
        }

        return new Competencia(
            id: row.id as Integer,
            nome: row.nome as String
        )
    }

    Integer update(Competencia competencia) {
        sql.executeUpdate('UPDATE competencia SET nome = ? WHERE id = ?', [competencia.nome, competencia.id])

        return competencia.id
    }

    boolean delete(Integer id) {
        sql.executeUpdate('DELETE FROM candidato_competencia WHERE competencia_id = ?', [id])
        sql.executeUpdate('DELETE FROM vaga_competencia WHERE competencia_id = ?', [id])
        Integer isDeleted = sql.executeUpdate('DELETE FROM competencia WHERE id = ?', [id])

        return isDeleted > 0
    }
}