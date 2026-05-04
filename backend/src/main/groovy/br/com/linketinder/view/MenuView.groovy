package br.com.linketinder.view

class MenuView {

    private final Scanner scanner = new Scanner(System.in)

    String exibirMenuPrincipal() {
        println """
        =============================================
                       MENU PRINCIPAL
        =============================================
          1  - Cadastrar Novo Candidato
          2  - Cadastrar Nova Empresa
          3  - Cadastrar Nova Competência
          4  - Cadastrar Nova Vaga
          5  - Listar Candidatos
          6  - Listar Empresas
          7  - Listar Competências
          8  - Listar Vagas
          9  - Atualizar Candidato
          10 - Atualizar Empresa
          11 - Atualizar Competência
          12 - Atualizar Vaga
          13 - Deletar Candidato
          14 - Deletar Empresa
          15 - Deletar Competência
          16 - Deletar Vaga
          0  - Sair
        =============================================
        """.stripIndent()

        print "Digite a opção desejada: "
        return scanner.nextLine()?.trim()
    }

    static void exibirOpcaoInvalida() {
        println "\nOpção inválida. Digite uma das opções válidas."
    }

    static void exibirEncerramento() {
        println "\nEncerrando o programa..."
    }
}