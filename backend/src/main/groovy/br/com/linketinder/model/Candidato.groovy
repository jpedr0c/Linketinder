package br.com.linketinder.model

import java.time.LocalDate

class Candidato extends PessoaBase{
    Integer id
    String sobrenome;
    LocalDate dataNascimento;
    String telefone;
    String cpf;
    String linkedin;
    Integer idade;
    List<Competencia> competencias = [];

    @Override
    void exibirInformacoes() {
        super.exibirInformacoes();
        println("ID: $id" +
                "\nSobrenome: $sobrenome" +
                "\nData de nascimento: $dataNascimento" +
                "\nTelefone: $telefone" +
                "\nCPF: $cpf" +
                "\nLinkedin: $linkedin" +
                "\nIdade: $idade" +
                "\nCompetências: ${competencias.join(", ")}");
        println("----------------");
    }
}
