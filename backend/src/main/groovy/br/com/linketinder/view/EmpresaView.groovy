package br.com.linketinder.view

import br.com.linketinder.model.Empresa
import java.util.logging.Logger

class EmpresaView {

    private static final Logger logger = Logger.getLogger(EmpresaView.class.getName())
    private final Scanner scanner = new Scanner(System.in)

    Empresa coletarDadosEmpresa() {
        exibirCabecalho()
        return new Empresa(
                nome:      lerCampoObrigatorio("Nome"),
                cnpj:      lerCampoObrigatorio("CNPJ (formato: 00.000.000/0000-00)"),
                email:     lerEmail("Email"),
                pais:      lerCampoObrigatorio("País"),
                estado:    lerCampoObrigatorio("Estado (UF)"),
                cidade:    lerCampoObrigatorio("Cidade"),
                cep:       lerCampoObrigatorio("CEP (formato: 12345-678)"),
                descricao: lerCampoObrigatorio("Descrição"),
                senha:     lerCampoObrigatorio("Senha")
        )
    }

    Integer capturarId() {
        print "\nDigite o ID da empresa: "
        String valor = scanner.nextLine()?.trim()
        if (!valor?.isInteger()) {
            throw new IllegalArgumentException("ID inválido: '${valor}'. Deve ser um número inteiro.")
        }
        return valor.toInteger()
    }

    static void exibirEmpresa(Empresa empresa) {
        println "\n======= Empresa ========"
        empresa.exibirInformacoes()
    }

    static void exibirTodasEmpresas(List<Empresa> empresas) {
        println "\n======= Empresas ========"
        empresas.each { Empresa e -> e.exibirInformacoes() }
    }

    static void exibirEmpresaNaoEncontrada(Integer id) {
        println "\nNenhuma empresa encontrada com o ID ${id}."
    }

    static void exibirSucessoCadastro() {
        println "\nEmpresa cadastrada com sucesso!"
    }

    static void exibirSucessoAtualizacao() {
        println "\nEmpresa atualizada com sucesso!"
    }

    static void exibirSucessoDelecao() {
        println "\nEmpresa deletada com sucesso!"
    }

    static void exibirErro() {
        println "\nOcorreu um erro. Tente novamente!"
    }

    static private void exibirCabecalho() {
        println "\n============================================="
        println "       CADASTRO DE NOVA EMPRESA"
        println "=============================================\n"
    }

    private String lerCampoObrigatorio(String rotulo) {
        print "${rotulo}: "
        String valor = scanner.nextLine()?.trim()
        if (!valor) {
            throw new IllegalArgumentException("O campo '${rotulo}' não pode ser vazio.")
        }
        return valor
    }

    private String lerEmail(String rotulo) {
        String email = lerCampoObrigatorio(rotulo)
        if (!email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Email inválido: '${email}'.")
        }
        return email
    }
}