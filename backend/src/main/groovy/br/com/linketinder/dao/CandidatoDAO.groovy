package br.com.linketinder.dao

import groovy.sql.Sql
import java.time.LocalDate
import groovy.sql.GroovyRowResult
import br.com.linketinder.model.Candidato

class CandidatoDAO {

    private Sql sql

    CandidatoDAO(Sql sql) {
        this.sql = sql
    }

    Integer inserir(Candidato candidato) {
        GroovyRowResult row = sql.firstRow('''
        INSERT INTO candidato 
        (nome, sobrenome, data_nascimento, email, telefone, cpf, pais, estado, cidade, cep, descricao, linkedin, senha)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        RETURNING id
    ''', [candidato.nome, candidato.sobrenome, candidato.dataNascimento, candidato.email, candidato.telefone, candidato.cpf, candidato.pais, candidato.estado, candidato.cidade, candidato.cep, candidato.descricao, candidato.linkedin, candidato.senha])
        return row?.id as Integer
    }

    List<Candidato> listarTodos() {
        List<GroovyRowResult> rows = sql.rows('SELECT * FROM candidato')

        return rows.collect { GroovyRowResult r ->
            new Candidato(
                    id: r.id as Integer,
                    nome: r.nome as String,
                    sobrenome: r.sobrenome as String,
                    dataNascimento: r.data_nascimento?.toLocalDate(),
                    email: r.email as String,
                    telefone: r.telefone as String,
                    cpf: r.cpf as String,
                    pais: r.pais as String,
                    estado: r.estado as String,
                    cidade: r.cidade as String,
                    cep: r.cep as String,
                    descricao: r.descricao as String,
                    linkedin: r.linkedin as String,
                    senha: r.senha as String
            )
        }
    }

    Candidato buscarPorId(Integer id) {
        GroovyRowResult row = sql.firstRow('SELECT * FROM candidato WHERE id = ?', [id])
        if (!row){
            return null
        }

        return new Candidato(
                id: row.id as Integer,
                nome: row.nome as String,
                sobrenome: row.sobrenome as String,
                dataNascimento: row.data_nascimento?.toLocalDate(),
                email: row.email as String,
                telefone: row.telefone as String,
                cpf: row.cpf as String,
                pais: row.pais as String,
                estado: row.estado as String,
                cidade: row.cidade as String,
                cep: row.cep as String,
                descricao: row.descricao as String,
                linkedin: row.linkedin as String,
                senha: row.senha as String
        )
    }

    Integer update(Candidato candidato) {
        sql.executeUpdate('''
        UPDATE candidato SET
            nome = ?, sobrenome = ?, data_nascimento = ?, email = ?, telefone = ?, cpf = ?,
            pais = ?, estado = ?, cidade = ?, cep = ?, descricao = ?, linkedin = ?, senha = ?,
            updated_at = NOW()
        WHERE id = ?
    ''', [candidato.nome, candidato.sobrenome, candidato.dataNascimento, candidato.email, candidato.telefone, candidato.cpf, candidato.pais, candidato.estado, candidato.cidade, candidato.cep, candidato.descricao, candidato.linkedin, candidato.senha, candidato.id])
        return candidato.id
    }

    boolean delete(Integer id) {
        sql.executeUpdate('DELETE FROM candidato_competencia WHERE candidato_id = ?', [id])
        Integer isDeleted = sql.executeUpdate('DELETE FROM candidato WHERE id = ?', [id])
        return isDeleted > 0
    }

    void adicionarCompetencia(Integer candidatoId, Integer competenciaId) {
        sql.executeInsert('''
            INSERT INTO candidato_competencia (candidato_id, competencia_id)
            VALUES (?, ?)
        ''', [candidatoId, competenciaId])
    }
}