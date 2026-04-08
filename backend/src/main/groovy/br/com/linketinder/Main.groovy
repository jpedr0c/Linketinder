package br.com.linketinder

import br.com.linketinder.model.Candidato
import br.com.linketinder.model.Empresa
import br.com.linketinder.service.CandidatoService
import br.com.linketinder.service.EmpresaService
import br.com.linketinder.model.Competencia
import br.com.linketinder.dao.CompetenciaDAO
import br.com.linketinder.database.ConexaoDB
import groovy.sql.Sql

class Main {
    static List<Candidato> candidatos = [];
    static List<Empresa> empresas = [];

    static void main(String[] args) {
        def connection = ConexaoDB.getConnection()
        def sql = new Sql(connection)
        def competenciaDAO = new CompetenciaDAO(sql)
//        Integer compId = competenciaDAO.inserir(new Competencia(nome: "Typescript"))
//        println "ID inserido: $compId"

        println "\nLista:"
        competenciaDAO.listarTodos().each { println it.nome }

        println "\nBuscar por ID:"
        println(competenciaDAO.buscarPorId(7).nome)

        competenciaDAO.update(new Competencia(id: 6, nome: "PostgreSQL"))
        println "\nAtualizado: " + competenciaDAO.buscarPorId(6).nome

        def result = competenciaDAO.buscarPorId(4).nome
        competenciaDAO.delete(4)
        println "\nCompetência $result deletado com sucesso"
    }

//    static void main(String[] args) {
////        carregarDados();
////        exibirMenu();
//}

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
        CandidatoService candidatoService = new CandidatoService();
        EmpresaService empresaService = new EmpresaService();

        println """
        ╔════════════════════════════════════════════════════════════╗
        ║                       MENU PRINCIPAL                       ║
        ╠════════════════════════════════════════════════════════════╣
        ║  1 - 📝 Cadastrar Novo Candidato                           ║
        ║  2 - 🏢 Cadastrar Nova Empresa                             ║
        ║  3 - 👥 Listar Candidatos                                  ║
        ║  4 - 🏭 Listar Empresas                                    ║
        ║  0 - 🚪 Sair                                               ║
        ╚════════════════════════════════════════════════════════════╝
        """.stripIndent()

        System.out.print("Digite a opção desejada: ");
        String option = scanner.nextLine();

        switch (option) {
            case "1":
                adicionarCandidato(scanner, candidatoService);
                break;
            case "2":
                adicionarEmpresa(scanner, empresaService);
                break;
            case "3":
                listarCandidatos();
                break;
            case "4":
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

    static void adicionarCandidato(Scanner scanner, CandidatoService candidatoService) {
        println "\n╔════════════════════════════════════════════════════════════╗"
        println "║            📝 CADASTRO DE NOVO CANDIDATO 📝               ║"
        println "╚════════════════════════════════════════════════════════════╝\n"

        print "Nome: "
        String nome = scanner.nextLine()

        print "Email: "
        String email = scanner.nextLine()

        print "CPF (formato: 123.456.789-00): "
        String cpf = scanner.nextLine()

        print "Idade: "
        Integer idade = scanner.nextInt()
        scanner.nextLine()

        print "Estado (UF): "
        String estado = scanner.nextLine()

        print "CEP (formato: 12345-678): "
        String cep = scanner.nextLine()

        print "Descrição pessoal: "
        String descricao = scanner.nextLine()

        print "Competências (separadas por vírgula): "
        String competenciasStr = scanner.nextLine()
        List<String> competencias = competenciasStr.split(",").collect { it.trim() }

        def candidato = new Candidato(
                nome: nome,
                email: email,
                cpf: cpf,
                idade: idade,
                estado: estado,
                cep: cep,
                descricao: descricao,
                competencias: competencias
        )

        if (candidatoService.adicionar(candidato)) {
            println "\n✅ Candidato adicionado com sucesso!"
        } else {
            println "\n❌ Erro ao adicionar candidato. Verifique os dados informados."
        }
    }

    static void adicionarEmpresa(Scanner scanner, EmpresaService empresaService) {
        println "\n╔════════════════════════════════════════════════════════════╗"
        println "║              🏢 CADASTRO DE NOVA EMPRESA 🏢               ║"
        println "╚════════════════════════════════════════════════════════════╝\n"

        print "Nome da empresa: "
        String nome = scanner.nextLine()

        print "Email corporativo: "
        String email = scanner.nextLine()

        print "CNPJ (formato: 12.345.678/0001-90): "
        String cnpj = scanner.nextLine()

        print "País: "
        String pais = scanner.nextLine()

        print "Estado (UF): "
        String estado = scanner.nextLine()

        print "CEP (formato: 12345-678): "
        String cep = scanner.nextLine()

        print "Descrição da empresa: "
        String descricao = scanner.nextLine()

        print "Competências desejadas (separadas por vírgula): "
        String competenciasStr = scanner.nextLine()
        List<String> competencias = competenciasStr.split(",").collect { it.trim() }

        def empresa = new Empresa(
                nome: nome,
                email: email,
                cnpj: cnpj,
                pais: pais,
                estado: estado,
                cep: cep,
                descricao: descricao,
                competencias: competencias
        )

        if (empresaService.adicionar(empresa)) {
            println "\n✅ Empresa adicionada com sucesso!"
        } else {
            println "\n❌ Erro ao adicionar empresa. Verifique os dados informados."
        }
    }

    static void listarCandidatos() {
        if (candidatos.isEmpty()) {
            println("Nenhum candidato cadastrado!");
            return;
        }
        println("======= Candidatos ========")
        candidatos.each {it.exibirInformacoes()}
    }

    static void listarEmpresas() {
        if (empresas.isEmpty()) {
            println("Nenhuma empresa cadastrada!");
            return;
        }
        println("======= Empresas ========")
        empresas.each {it.exibirInformacoes()}
    }
}
