package br.com.linketinder.service

import br.com.linketinder.dao.EmpresaDAO
import br.com.linketinder.database.ConexaoDB
import br.com.linketinder.model.Empresa
import br.com.linketinder.view.EmpresaView
import groovy.sql.Sql
import java.sql.Connection
import java.sql.SQLException
import java.util.logging.Logger

class EmpresaService {

    private static final Logger logger = Logger.getLogger(EmpresaService.class.getName())

    private final EmpresaDAO empresaDAO
    private final EmpresaView empresaView

    EmpresaService() {
        Connection connection = ConexaoDB.getConnection()
        Sql sql = new Sql(connection)
        this.empresaDAO = new EmpresaDAO(sql)
        this.empresaView = new EmpresaView()
    }

    void inserir() {
        try {
            Empresa empresa = empresaView.coletarDadosEmpresa()
            Integer id = empresaDAO.inserir(empresa)
            if (id) {
                empresaView.exibirSucessoCadastro()
            } else {
                empresaView.exibirErro()
            }
        } catch (IllegalArgumentException e) {
            logger.warning("Dado inválido informado pelo usuário: ${e.printStackTrace()}")
            empresaView.exibirErro()
        } catch (SQLException e) {
            logger.severe("Erro de banco ao inserir empresa: ${e.printStackTrace()}")
            empresaView.exibirErro()
        }
    }

    void listarTodos() {
        try {
            List<Empresa> empresas = empresaDAO.listarTodos()
            empresaView.exibirTodasEmpresas(empresas)
        } catch (SQLException e) {
            logger.severe("Erro de banco ao listar empresas: ${e.printStackTrace()}")
            empresaView.exibirErro()
        }
    }

    void listarPorId() {
        try {
            Integer id = empresaView.capturarId()
            Empresa empresa = empresaDAO.buscarPorId(id)
            if (empresa) {
                empresaView.exibirEmpresa(empresa)
            } else {
                empresaView.exibirEmpresaNaoEncontrada(id)
            }
        } catch (IllegalArgumentException e) {
            logger.warning("ID inválido informado pelo usuário: ${e.printStackTrace()}")
            empresaView.exibirErro()
        } catch (SQLException e) {
            logger.severe("Erro de banco ao buscar empresa: ${e.printStackTrace()}")
            empresaView.exibirErro()
        }
    }

    void atualizar() {
        try {
            Integer id = empresaView.capturarId()
            Empresa empresaExistente = empresaDAO.buscarPorId(id)
            if (!empresaExistente) {
                empresaView.exibirEmpresaNaoEncontrada(id)
                return
            }
            Empresa empresaAtualizada = empresaView.coletarDadosEmpresa()
            empresaAtualizada.id = id
            empresaDAO.update(empresaAtualizada)
            empresaView.exibirSucessoAtualizacao()
        } catch (IllegalArgumentException e) {
            logger.warning("Dado inválido informado pelo usuário: ${e.printStackTrace()}")
            empresaView.exibirErro()
        } catch (SQLException e) {
            logger.severe("Erro de banco ao atualizar empresa: ${e.printStackTrace()}")
            empresaView.exibirErro()
        }
    }

    void deletar() {
        try {
            Integer id = empresaView.capturarId()
            Empresa empresaExistente = empresaDAO.buscarPorId(id)
            if (!empresaExistente) {
                empresaView.exibirEmpresaNaoEncontrada(id)
                return
            }
            boolean deletado = empresaDAO.delete(id)
            if (deletado) {
                empresaView.exibirSucessoDelecao()
            } else {
                empresaView.exibirErro()
            }
        } catch (IllegalArgumentException e) {
            logger.warning("ID inválido informado pelo usuário: ${e.printStackTrace()}")
            empresaView.exibirErro()
        } catch (SQLException e) {
            logger.severe("Erro de banco ao deletar empresa: ${e.printStackTrace()}")
            empresaView.exibirErro()
        }
    }
}