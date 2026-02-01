package br.com.linketinder.service

import br.com.linketinder.model.Candidato
import spock.lang.Specification

class CandidatoServiceSpec extends Specification{

    CandidatoService service = new CandidatoService();
    def "Deve cadastrar um novo candidato na lista"() {
        given: "Candidato válido";
        def candidato = new Candidato(
                nome: "Amanda",
                email: "amanda@mail.com",
                cpf: "995.823.525-00",
                idade: 22,
                estado: "RJ",
                cep: "20098123",
                descricao: "Testando adicionar um novo candidato",
                competencias: ["Java", "Angular"]
        )

        when: "Inserir o candidato na lista";
        def resultado = service.adicionarCandidato(candidato);

        then: "Deve retornar true e adicionar o candidato a lista"
        resultado == true;
        service.listarCandidatos().size() == 1
        service.listarCandidatos()[0].nome == "Amanda"
        service.listarCandidatos()[0].cpf == "995.823.525-00"
    }

    def "deve retornar lista vazia quando não há candidatos"() {
        when: "listar candidatos sem nenhum cadastrado"
        def lista = service.listarCandidatos()

        then: "deve retornar lista vazia"
        lista != null
        lista.isEmpty()
    }

    def "não deve inserir candidato com dados vazios"() {
        given: "um candidato com campos vazios"
        def candidato = new Candidato(
                nome: "",
                email: "test@email.com",
                cpf: "123.456.789-00",
                idade: 25,
                estado: "SP",
                cep: "01234-567",
                descricao: "Dev",
                competencias: ["Java"]
        )

        when: "tentar inserir o candidato"
        def resultado = service.adicionarCandidato(candidato)

        then: "deve retornar false"
        resultado == false
        service.listarCandidatos().size() == 0
    }
}
