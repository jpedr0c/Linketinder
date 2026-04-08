package br.com.linketinder.model

abstract class PessoaBase implements Pessoa {
    String nome;
    String email;
    String cep;
    String cidade;
    String estado;
    String pais;
    String descricao;

    @Override
    void exibirInformacoes() {
        println("Nome: $nome" +
                "\nEmail: $email" +
                "\nCEP: $cep" +
                "\nCidade: $cidade" +
                "\nEstado: $estado" +
                "\nPaís: $pais" +
                "\nDescricão: $descricao");
    }
}
