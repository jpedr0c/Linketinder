package br.com.linketinder

import br.com.linketinder.model.Candidato
import br.com.linketinder.model.Empresa
import br.com.linketinder.model.Competencia
import br.com.linketinder.model.Vaga
import br.com.linketinder.service.CandidatoService
import br.com.linketinder.service.EmpresaService
import java.time.LocalDate

class Main {
    static void main(String[] args) {
        exibirMenu();
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
        ║  2 - 📝 Cadastrar Nova Empresa                             ║
        ║  3 - 📝 Cadastrar Nova Competência                         ║
        ║  4 - 📝 Cadastrar Nova Vaga                                ║
        ║  5 - 👥 Listar Candidatos                                  ║
        ║  6 - 👥 Listar Empresas                                    ║
        ║  7 - 👥 Listar Competências                                ║
        ║  8 - 👥 Listar Vagas                                       ║
        ║  9 - 🏭 Atualizar Candidato                                ║
        ║  10 - 🏭 Atualizar Empresa                                 ║
        ║  11 - 🏭 Atualizar Competência                             ║
        ║  12 - 🏭 Atualizar Vaga                                    ║
        ║  13 - 🏭 Deletar Candidato                                 ║
        ║  14 - 🏭 Deletar Empresa                                   ║
        ║  15 - 🏭 Deletar Competência                               ║
        ║  16 - 🏭 Deletar Vaga                                      ║
        ║  0 - 🚪 Sair                                               ║
        ╚════════════════════════════════════════════════════════════╝
        """.stripIndent()

        System.out.print("Digite a opção desejada: ");
        String option = scanner.nextLine();

        switch (option) {
            case "1":
                candidatoService.inserir()
                break;
//            case "2":
//                empresaService.inserir()
//                break;
            case "5":
                List<Candidato> candidatos = candidatoService.listarTodos()
                println("\n======= Candidatos ========")
                candidatos.each {it.exibirInformacoes()}
                break;
            case "6":
                List<Empresa> empresas = empresaService.listarTodos()
                println("\n======= Empresas ========")
                empresas.each {it.exibirInformacoes()}
                break;
            case "7":
                List<Empresa> empresas = empresaService.listarTodos()
                println("\n======= Competências ========")
                empresas.each {it.exibirInformacoes()}
                break;
            case "8":
                List<Empresa> empresas = empresaService.listarTodos()
                println("\n======= Vagas ========")
                empresas.each {it.exibirInformacoes()}
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
}
