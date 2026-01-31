package br.com.linketinder

import br.com.linketinder.model.Candidato
import br.com.linketinder.model.Empresa

class Main {
    static List<Candidato> candidatos = [];
    static List<Empresa> empresas = [];

    static void main(String[] args) {
        carregarDados();
        exibirMenu();
    }

    static void carregarDados() {

        candidatos = [
                new Candidato(
                        nome: "João Pedro Cardoso",
                        email: "joao@pedro.com",
                        cpf: "111.424.643-45",
                        idade: 24,
                        estado: "RJ",
                        cep: "20000-123",
                        descricao: "Desenvolvedor Backend",
                        competencias: ["Java", "Spring", "SQL"]
                ),
                new Candidato(
                        nome: "Carlos Lima",
                        email: "carlos@email.com",
                        cpf: "222.222.222-22",
                        idade: 30,
                        estado: "SP",
                        cep: "01000-000",
                        descricao: "Frontend Developer",
                        competencias: ["Angular", "TypeScript", "HTML"]
                ),
                new Candidato(
                        nome: "Mariana Costa",
                        email: "mariana@email.com",
                        cpf: "333.333.333-33",
                        idade: 28,
                        estado: "MG",
                        cep: "30000-000",
                        descricao: "Fullstack",
                        competencias: ["Java", "Angular", "Spring"]
                ),
                new Candidato(
                        nome: "Pedro Rocha",
                        email: "pedro@email.com",
                        cpf: "444.444.444-44",
                        idade: 35,
                        estado: "RS",
                        cep: "90000-000",
                        descricao: "DevOps",
                        competencias: ["Docker", "AWS", "Linux"]
                ),
                new Candidato(
                        nome: "Julia Mendes",
                        email: "julia@email.com",
                        cpf: "555.555.555-55",
                        idade: 23,
                        estado: "BA",
                        cep: "40000-000",
                        descricao: "Estagiária",
                        competencias: ["Python", "Git"]
                )
        ]

        empresas = [
                new Empresa(
                        nome: "ZG Solucões",
                        email: "timecomercial@zgsolucoes.com.br",
                        cnpj: "14.488.144/0001-43",
                        pais: "Brasil",
                        estado: "GO",
                        cep: "74070-040",
                        descricao: "Empresa de tecnologia atuante na área de saúde",
                        competencias: ["Groovy", "Spring"]
                ),
                new Empresa(
                        nome: "Zero Glosa",
                        email: "contato@zeroglosa.com",
                        cnpj: "22.222.222/0001-22",
                        pais: "Brasil",
                        estado: "RJ",
                        cep: "20000-000",
                        descricao: "Soluções web",
                        competencias: ["Angular", "Frontend"]
                ),
                new Empresa(
                        nome: "ZG Transmissão",
                        email: "contato@zgtransmissao.com",
                        cnpj: "33.333.333/0001-33",
                        pais: "Brasil",
                        estado: "MG",
                        cep: "30000-000",
                        descricao: "Big Data",
                        competencias: ["Python", "SQL"]
                ),
                new Empresa(
                        nome: "ZG Conformidade",
                        email: "contato@zgconformidade.com",
                        cnpj: "44.444.444/0001-44",
                        pais: "Brasil",
                        estado: "RS",
                        cep: "90000-000",
                        descricao: "Infraestrutura",
                        competencias: ["AWS", "Docker"]
                ),
                new Empresa(
                        nome: "Acelera ZG",
                        email: "contato@acelerazg.com",
                        cnpj: "55.555.555/0001-55",
                        pais: "Brasil",
                        estado: "SP",
                        cep: "04000-000",
                        descricao: "Startup inovadora",
                        competencias: ["Java", "Angular"]
                )
        ]
    }

    static void exibirMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n===== Linketinder =====");
        System.out.println("1 - Listar candidatos");
        System.out.println("2 - Listar empresas");
        System.out.println("0 - Sair");

        System.out.print("Escolha uma opção: ");
        String option = scanner.nextLine();

        switch (option) {
            case "1":
                listarCandidatos();
                break;
            case "2":
                listarEmpresas();
                break;
            case "0":
                System.out.println("Encerrando o programa...");
                scanner.close();
                return;
            default:
                println("Opção inválida. Digite uma das opções válidas");
        }

        exibirMenu();
    }

    static void listarCandidatos() {
        println("======= Candidatos ========")
        candidatos.each {it.exibirInformacoes()}
    }

    static void listarEmpresas() {
        println("======= Empresas ========")
        empresas.each {it.exibirInformacoes()}
    }
}
