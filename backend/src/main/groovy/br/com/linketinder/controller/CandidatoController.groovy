package br.com.linketinder.controller

import br.com.linketinder.dao.CandidatoDAO
import br.com.linketinder.database.ConexaoDB
import br.com.linketinder.model.Candidato
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovy.sql.Sql

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.time.LocalDate
import java.util.logging.Logger

@WebServlet("/candidatos")
class CandidatoController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(CandidatoController.class.getName())

    private final CandidatoDAO candidatoDAO

    CandidatoController() {
        this.candidatoDAO = new CandidatoDAO(new Sql(ConexaoDB.getConnection()))
    }

    CandidatoController(CandidatoDAO candidatoDAO) {
        this.candidatoDAO = candidatoDAO
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
            logger.severe("Erro ao buscar candidato: ${e.message}")
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            response.writer.print(JsonOutput.toJson([erro: "Erro interno no servidor."]))
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        configurarResposta(response)
        try {
            Map body = parsearBody(request)
            Candidato candidato = mapearCandidato(body)
            Integer id = candidatoDAO.inserir(candidato)

            response.setStatus(HttpServletResponse.SC_CREATED)
            response.writer.print(JsonOutput.toJson([
                    mensagem: "Candidato cadastrado com sucesso.",
                    id: id
            ]))
        } catch (IllegalArgumentException e) {
            logger.warning("Dados inválidos na requisição: ${e.message}")
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST)
            response.writer.print(JsonOutput.toJson([erro: e.message]))
        } catch (Exception e) {
            logger.severe("Erro ao cadastrar candidato: ${e.message}")
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
            response.writer.print(JsonOutput.toJson([erro: "Erro interno no servidor."]))
        }
    }

    private void listarTodos(HttpServletResponse response) {
        List<Candidato> candidatos = candidatoDAO.listarTodos()
        response.setStatus(HttpServletResponse.SC_OK)
        response.writer.print(JsonOutput.toJson(
                candidatos.collect { Candidato c -> mapearParaJson(c) }
        ))
    }

    private void listarPorId(Integer id, HttpServletResponse response) {
        Candidato candidato = candidatoDAO.buscarPorId(id)
        if (!candidato) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND)
            response.writer.print(JsonOutput.toJson([erro: "Candidato não encontrado com o ID ${id}."]))
            return
        }
        response.setStatus(HttpServletResponse.SC_OK)
        response.writer.print(JsonOutput.toJson(mapearParaJson(candidato)))
    }

    private static Map mapearParaJson(Candidato c) {
        return [
                id:             c.id,
                nome:           c.nome,
                sobrenome:      c.sobrenome,
                dataNascimento: c.dataNascimento?.toString(),
                email:          c.email,
                telefone:       c.telefone,
                cpf:            c.cpf,
                pais:           c.pais,
                estado:         c.estado,
                cidade:         c.cidade,
                cep:            c.cep,
                descricao:      c.descricao,
                linkedin:       c.linkedin
        ]
    }

    private static Candidato mapearCandidato(Map body) {
        validarCamposObrigatorios(body, ["nome", "sobrenome", "email", "cpf", "senha"])
        return new Candidato(
                nome:           body.nome as String,
                sobrenome:      body.sobrenome as String,
                dataNascimento: body.dataNascimento ? LocalDate.parse(body.dataNascimento as String) : null,
                email:          body.email as String,
                telefone:       body.telefone as String,
                cpf:            body.cpf as String,
                pais:           body.pais as String,
                estado:         body.estado as String,
                cidade:         body.cidade as String,
                cep:            body.cep as String,
                descricao:      body.descricao as String,
                linkedin:       body.linkedin as String,
                senha:          body.senha as String
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