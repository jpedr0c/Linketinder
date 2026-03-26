package br.com.linketinder.service

import br.com.linketinder.model.Candidato
import spock.lang.Specification

class CandidatoServiceSpec extends Specification{

    CandidatoService candidatoService = new CandidatoService();
    def "Deve cadastrar um novo candidato na lista"() {
        given: "Candidato válido";
        def candidato = new Candidato(
                nome: "Teste",
                email: "teste@mail.com",
                cpf: "995.823.525-00",
                idade: 22,
                estado: "RJ",
                cep: "20098123",
                descricao: "Testando adicionar um novo candidato",
                competencias: ["Java", "Angular"]
        )

        when: "Inserir o candidato na lista";
        def resultado = candidatoService.adicionar(candidato);

        then: "Deve retornar true e adicionar o candidato a lista"
        resultado == true;
        candidatoService.listar().size() == 1;
        candidatoService.listar()[0].nome == "Teste";
        candidatoService.listar()[0].cpf == "995.823.525-00";
    }

    def "Deve retornar lista vazia quando não há candidatos"() {
        when: "listar candidatos sem nenhum cadastrado"
        def lista = candidatoService.listar();

        then: "Deve retornar lista vazia"
        lista != null;
        lista.isEmpty();
    }
}
