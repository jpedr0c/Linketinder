package br.com.linketinder.service

import br.com.linketinder.model.Empresa
import spock.lang.Specification

class EmpresaServiceSpec extends Specification {

    EmpresaService empresaService = new EmpresaService();

    def "Deve cadastrar uma nova empresa na lista"() {
        given: "Empresa válido";
        def empresa = new Empresa(
                nome: "Empresa teste",
                email: "teste@mail.com",
                cnpj: "32.453.534/0001-33",
                pais: "Brasil",
                estado: "RJ",
                cep: "20098123",
                descricao: "Testando adicionar uma nova empresa",
                competencias: ["Java", "Angular"]
        )

        when: "Inserir a empresa na lista";
        def resultado = empresaService.adicionar(empresa);

        then: "Deve retornar true e adicionar a empresa na lista"
        resultado == true;
        empresaService.listar().size() == 1
        empresaService.listar()[0].nome == "Empresa teste"
        empresaService.listar()[0].cnpj == "32.453.534/0001-33"
    }

    def "deve retornar lista vazia quando não há empresa"() {
        when: "listar empresas sem ter nenhuma cadastrada"
        def lista = empresaService.listar()

        then: "deve retornar lista vazia"
        lista != null
        lista.isEmpty()
    }
}
