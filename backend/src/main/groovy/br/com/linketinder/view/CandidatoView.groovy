package br.com.linketinder.view

import br.com.linketinder.model.Candidato
import br.com.linketinder.model.Empresa

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class CandidatoView {

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    private final Scanner scanner = new Scanner(System.in)

    Candidato coletarDadosCandidato() {
        exibirCabecalho()

        String nome        = lerCampoObrigatorio("Nome")
        String sobrenome   = lerCampoObrigatorio("Sobrenome")
        LocalDate dataNasc = lerData("Data de nascimento (dd/MM/yyyy)")
        String email       = lerEmail("Email")
        String telefone    = lerCampoObrigatorio("Telefone")
        String cpf         = lerCampoObrigatorio("CPF (formato: 123.456.789-00)")
        String pais        = lerCampoObrigatorio("País")
        String estado      = lerCampoObrigatorio("Estado (UF)")
        String cidade      = lerCampoObrigatorio("Cidade")
        String cep         = lerCampoObrigatorio("CEP (formato: 12345-678)")
        String descricao   = lerCampoObrigatorio("Descrição pessoal")
        String linkedin    = lerCampoObrigatorio("LinkedIn (URL)")
        String senha       = lerCampoObrigatorio("Senha")

        return new Candidato(
                nome:           nome,
                sobrenome:      sobrenome,
                dataNascimento: dataNasc,
                email:          email,
                telefone:       telefone,
                cpf:            cpf,
                pais:           pais,
                estado:         estado,
                cidade:         cidade,
                cep:            cep,
                descricao:      descricao,
                linkedin:       linkedin,
                senha:          senha
        )
    }

    static void exibirSucessoCadastro() {
        println "\nCandidato cadastrado com sucesso!"
    }

    static void exibirErroCadastro() {
        println "\nErro ao cadastrar candidato. Tente novamente!"
    }

    static void exibirCandidato(Candidato candidato) {
        println "\n======= Candidato ========"
        candidato.exibirInformacoes()
    }

    static void exibirTodosCandidatos(List<Candidato> candidatos) {
        println "\n======= Candidatos ========"
        candidatos.each { Candidato e -> e.exibirInformacoes() }
    }

    static void exibirCandidatoNaoEncontrado(Integer id) {
        println "\nNenhum candidato encontrado com o ID ${id}."
    }

    static void exibirSucessoAtualizacao() {
        println "\nCandidato atualizado com sucesso!"
    }

    static void exibirSucessoDelecao() {
        println "\nCandidato deletado com sucesso!"
    }

    private static void exibirCabecalho() {
        println "\n============================================="
        println "      CADASTRO DE NOVO CANDIDATO"
        println "=============================================\n"
    }

    Integer capturarId() {
        print "\nDigite o ID do candidato: "
        String valor = scanner.nextLine()?.trim()
        if (!valor?.isInteger()) {
            throw new IllegalArgumentException("ID inválido: '${valor}'. Deve ser um número inteiro.")
        }
        return valor.toInteger()
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

    private LocalDate lerData(String rotulo) {
        print "${rotulo}: "
        String dataStr = scanner.nextLine()?.trim()
        try {
            return LocalDate.parse(dataStr, FORMATO_DATA)
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Data inválida: '${dataStr}'. Use o formato dd/MM/yyyy.", e)
        }
    }
}