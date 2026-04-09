package br.com.linketinder.dao

import groovy.sql.Sql
import groovy.sql.GroovyRowResult
import br.com.linketinder.model.Vaga

class VagaDAO {

    private Sql sql

    VagaDAO(Sql sql) {
        this.sql = sql
    }

    Integer inserir(Vaga vaga) {
        GroovyRowResult row = sql.firstRow('''
            INSERT INTO vaga (empresa_id, nome, cidade, estado, descricao)
            VALUES (?, ?, ?, ?, ?)
            RETURNING id
        ''', [vaga.empresaId, vaga.nome, vaga.cidade, vaga.estado, vaga.descricao])

        return row?.id as Integer
    }

    List<Vaga> listarTodos() {
        List<GroovyRowResult> rows = sql.rows('SELECT * FROM vaga')

        return rows.collect { GroovyRowResult r ->
            new Vaga(
                    id: r.id as Integer,
                    nome: r.nome as String,
                    cidade: r.cidade as String,
                    estado: r.estado as String,
                    descricao: r.descricao as String,
                    empresaId: r.empresa_id as Integer
            )
        }
    }

    Vaga buscarPorId(Integer id) {
        GroovyRowResult row = sql.firstRow('SELECT * FROM vaga WHERE id = ?', [id])
        if (!row){
            return null
        }

        return new Vaga(
                id: row.id as Integer,
                nome: row.nome as String,
                cidade: row.cidade as String,
                estado: row.estado as String,
                descricao: row.descricao as String,
                empresaId: row.empresa_id as Integer
        )
    }

    Integer update(Vaga vaga) {
        sql.executeUpdate('''
            UPDATE vaga SET
                nome = ?, cidade = ?, estado = ?, descricao = ?,
                updated_at = NOW()
            WHERE id = ?
        ''', [vaga.nome, vaga.cidade, vaga.estado, vaga.descricao, vaga.id])

        return vaga.id
    }

    boolean delete(Integer id) {
        sql.executeUpdate('DELETE FROM vaga_competencia WHERE vaga_id = ?', [id])
        Integer isDeleted = sql.executeUpdate('DELETE FROM vaga WHERE id = ?', [id])
        return isDeleted > 0
    }

    void adicionarCompetencia(Integer vagaId, Integer competenciaId) {
        sql.executeInsert('''
            INSERT INTO vaga_competencia (vaga_id, competencia_id)
            VALUES (?, ?)
        ''', [vagaId, competenciaId])
    }
}