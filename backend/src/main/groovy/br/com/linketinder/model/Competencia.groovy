package br.com.linketinder.model

class Competencia {
    Integer id;
    String nome;

    void exibirInformacoes() {
        println("ID: $id" +
                "\nNome: $nome");
        println("----------------");
    }
}
