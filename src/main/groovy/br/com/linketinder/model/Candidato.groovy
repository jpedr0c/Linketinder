package br.com.linketinder.model

class Candidato extends PessoaBase{
    String cpf;
    int idade;

    @Override
    void exibirInformacoes() {
        super.exibirInformacoes();
        println("CPF: $cpf" +
                "\nIdade: $idade");
        println("----------------");
    }
}
