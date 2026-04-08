package br.com.linketinder

import br.com.linketinder.model.Candidato
import br.com.linketinder.model.Empresa
import br.com.linketinder.service.CandidatoService
import br.com.linketinder.service.EmpresaService
import br.com.linketinder.model.Competencia
import br.com.linketinder.dao.CompetenciaDAO
import br.com.linketinder.dao.EmpresaDAO
import br.com.linketinder.database.ConexaoDB
import groovy.sql.Sql

class Main {
    static List<Candidato> candidatos = [];
    static List<Empresa> empresas = [];

    static void main(String[] args) {
        def connection = ConexaoDB.getConnection()
        def sql = new Sql(connection)
//        exibirMenu();

        def empresaDAO = new EmpresaDAO(sql)

//        def competenciaDAO = new CompetenciaDAO(sql)
//        Integer compId = competenciaDAO.inserir(new Competencia(nome: "Typescript"))
//        println "ID inserido: $compId"

//        println "\nLista:"
//        competenciaDAO.listarTodos().each { println it.nome }
//
//        println "\nBuscar por ID:"
//        println(competenciaDAO.buscarPorId(7).nome)
//
//        competenciaDAO.update(new Competencia(id: 6, nome: "PostgreSQL"))
//        println "\nAtualizado: " + competenciaDAO.buscarPorId(6).nome
//
//        def result = competenciaDAO.buscarPorId(4).nome
//        competenciaDAO.delete(4)
//        println "\nCompetência $result deletado com sucesso"

//        def empId = empresaDAO.inserir(new Empresa(
//                nome: "Testando",
//                email: "empresa@test.com",
//                cnpj: "99999999999999",
//                pais: "Brasil",
//                estado: "SP",
//                cidade: "São Paulo",
//                cep: "01000000",
//                descricao: "Teste",
//                senha: "654321"
//        ))
//
//        println "Empresa ID: $empId"
//
//        println "\nLista:"
//        empresaDAO.listarTodos().each { println it.nome }
//
//        println "\nBuscar por ID:"
//        println empresaDAO.buscarPorId(2).nome

        empresaDAO.update(new Empresa(
                id: 14,
                nome: "Teste do Update",
                email: "empresa@test.com",
                cnpj: "00999999999999",
                pais: "Brasil",
                estado: "RJ",
                cidade: "Rio de Janeiro",
                cep: "01000000",
                descricao: "Fazendo o teste para saber se o update_at irá atualizar",
                senha: "123456"
        ))

//        def result = empresaDAO.buscarPorId(11).nome
//        empresaDAO.delete(11)
//        println "Empresa $result deletada"
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
