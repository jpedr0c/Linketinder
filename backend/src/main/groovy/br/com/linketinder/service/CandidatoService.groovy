package br.com.linketinder.service

import br.com.linketinder.model.Candidato
import br.com.linketinder.dao.CandidatoDAO
import br.com.linketinder.database.ConexaoDB
import br.com.linketinder.view.CandidatoView
import groovy.sql.Sql
import java.util.logging.Logger
import java.sql.SQLException
import java.sql.Connection

class CandidatoService {

    private static final Logger logger = Logger.getLogger(CandidatoService.class.getName())

    private final CandidatoDAO candidatoDAO
    private final CandidatoView candidatoView

    CandidatoService() {
        Connection connection = ConexaoDB.getConnection()
        Sql sql = new Sql(connection)
        this.candidatoDAO = new CandidatoDAO(sql)
        this.candidatoView = new CandidatoView()
    }

    void inserir() {
        try{
            Candidato candidato = candidatoView.coletarDadosCandidato()
            Integer id = candidatoDAO.inserir(candidato)

            if (id) {
                candidatoView.exibirSucessoCadastro()
            } else {
                candidatoView.exibirErroCadastro()
            }
        } catch (IllegalArgumentException e) {
            logger.warning("Dado inválido informado pelo usuário: ${e.printStackTrace()}")
            candidatoView.exibirErroCadastro()
        } catch (SQLException e) {
            logger.severe("Erro de banco de dados ao cadastrar usuário: ${e.printStackTrace()}")
            candidatoView.exibirErroCadastro()
        }
    }

    List<Candidato> listarTodos() {
        try {
            return candidatoDAO.listarTodos()
        } catch (SQLException e) {
            logger.severe("Erro de banco de dados ao listar candidatos: ${e.printStackTrace()}")
            return []
        }
    }

    void listarPorId() {
        try {
            Integer id = candidatoView.capturarId()
            Candidato candidato = candidatoDAO.buscarPorId(id)

            if (candidato) {
                candidatoView.exibirCandidato(candidato)
            } else {
                candidatoView.exibirCandidatoNaoEncontrado(id)
            }
        } catch (IllegalArgumentException e) {
            logger.warning("ID inválido informado pelo usuário: ${e.message}")
            candidatoView.exibirErroCadastro()
        } catch (SQLException e) {
            logger.severe("Erro de banco de dados ao buscar candidato: ${e.message}")
            candidatoView.exibirErroCadastro()
        }
    }

    void atualizar() {
        try {
            Integer id = candidatoView.capturarId()
            Candidato candidatoExistente = candidatoDAO.buscarPorId(id)

            if (!candidatoExistente) {
                candidatoView.exibirCandidatoNaoEncontrado(id)
                return
            }

            Candidato candidatoAtualizado = candidatoView.coletarDadosCandidato()
            candidatoAtualizado.id = id
            candidatoDAO.update(candidatoAtualizado)
            candidatoView.exibirSucessoAtualizacao()
        } catch (IllegalArgumentException e) {
            logger.warning("Dado inválido informado pelo usuário: ${e.message}")
            candidatoView.exibirErroCadastro()
        } catch (SQLException e) {
            logger.severe("Erro de banco de dados ao atualizar candidato: ${e.message}")
            candidatoView.exibirErroCadastro()
        }
    }

    void deletar() {
        try {
            Integer id = candidatoView.capturarId()
            Candidato candidatoExistente = candidatoDAO.buscarPorId(id)

            if (!candidatoExistente) {
                candidatoView.exibirCandidatoNaoEncontrado(id)
                return
            }

            boolean deletado = candidatoDAO.delete(id)
            if (deletado) {
                candidatoView.exibirSucessoDelecao()
            } else {
                candidatoView.exibirErroCadastro()
            }
        } catch (IllegalArgumentException e) {
            logger.warning("ID inválido informado pelo usuário: ${e.message}")
            candidatoView.exibirErroCadastro()
        } catch (SQLException e) {
            logger.severe("Erro de banco de dados ao deletar candidato: ${e.message}")
            candidatoView.exibirErroCadastro()
        }
    }
}