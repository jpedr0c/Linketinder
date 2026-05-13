package br.com.linketinder.controller

import br.com.linketinder.dao.EmpresaDAO
import br.com.linketinder.database.ConexaoDB
import br.com.linketinder.model.Empresa
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovy.sql.Sql

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.util.logging.Logger

@WebServlet("/empresas")
class EmpresaController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(EmpresaController.class.getName())

    private final EmpresaDAO empresaDAO

    EmpresaController() {
        this.empresaDAO = new EmpresaDAO(new Sql(ConexaoDB.getConnection()))
    }

    EmpresaController(EmpresaDAO empresaDAO) {
        this.empresaDAO = empresaDAO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        configurarResposta(response)
        try {
            String idParam = request.getParameter("id")

            if (idParam == null) {
                listarTodos(response)
            } else {
                Integer id = parsearId(idParam)
                listarPorId(id, response)
            }
        } catch (IllegalArgumentException e) {
            logger.warning("ID inválido na requisição: ${e.message}")
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            response.writer.print(JsonOutput.toJson([erro: e.message]))
        } catch (Exception e) {
            logger.severe("Erro ao buscar empresa: ${e.message}")
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            response.writer.print(JsonOutput.toJson([erro: "Erro interno no servidor."]))
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        configurarResposta(response)
        try {
            Map body = parsearBody(request)
            Empresa empresa = mapearEmpresa(body)
            Integer id = empresaDAO.inserir(empresa)

            response.setStatus(HttpServletResponse.SC_CREATED)
            response.writer.print(JsonOutput.toJson([
                    mensagem: "Empresa cadastrada com sucesso.",
                    id: id
            ]))
        } catch (IllegalArgumentException e) {
            logger.warning("Dados inválidos na requisição: ${e.message}")
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            response.writer.print(JsonOutput.toJson([erro: e.message]))
        } catch (Exception e) {
            logger.severe("Erro ao cadastrar empresa: ${e.message}")
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            response.writer.print(JsonOutput.toJson([erro: "Erro interno no servidor."]))
        }
    }

    private void listarTodos(HttpServletResponse response) {
        List<Empresa> empresas = empresaDAO.listarTodos()
        response.setStatus(HttpServletResponse.SC_OK)
        response.writer.print(JsonOutput.toJson(
                empresas.collect { Empresa e -> mapearParaJson(e) }
        ))
    }

    private void listarPorId(Integer id, HttpServletResponse response) {
        Empresa empresa = empresaDAO.buscarPorId(id)
        if (!empresa) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND)
            response.writer.print(JsonOutput.toJson([erro: "Empresa não encontrada com o ID ${id}."]))
            return
        }
        response.setStatus(HttpServletResponse.SC_OK)
        response.writer.print(JsonOutput.toJson(mapearParaJson(empresa)))
    }

    private static Map mapearParaJson(Empresa e) {
        return [
                id:        e.id,
                nome:      e.nome,
                cnpj:      e.cnpj,
                email:     e.email,
                pais:      e.pais,
                estado:    e.estado,
                cidade:    e.cidade,
                cep:       e.cep,
                descricao: e.descricao
        ]
    }

    private static Empresa mapearEmpresa(Map body) {
        validarCamposObrigatorios(body, ["nome", "cnpj", "email", "senha"])
        return new Empresa(
                nome:      body.nome as String,
                cnpj:      body.cnpj as String,
                email:     body.email as String,
                pais:      body.pais as String,
                estado:    body.estado as String,
                cidade:    body.cidade as String,
                cep:       body.cep as String,
                descricao: body.descricao as String,
                senha:     body.senha as String
        )
    }

    private static Integer parsearId(String idParam) {
        if (!idParam.isInteger()) {
            throw new IllegalArgumentException("ID inválido: '${idParam}'. Deve ser um número inteiro.")
        }
        return idParam.toInteger()
    }

    private static Map parsearBody(HttpServletRequest request) {
        String body = request.reader.text
        if (!body) {
            throw new IllegalArgumentException("O corpo da requisição não pode ser vazio.")
        }
        return new JsonSlurper().parseText(body) as Map
    }

    private static void validarCamposObrigatorios(Map body, List<String> campos) {
        campos.each { String campo ->
            if (!body[campo]) {
                throw new IllegalArgumentException("O campo '${campo}' é obrigatório.")
            }
        }
    }

    private static void configurarResposta(HttpServletResponse response) {
        response.contentType = "application/json"
        response.characterEncoding = "UTF-8"
    }
}