package groovy

abstract class PessoaBase implements Pessoa {
    String nome;
    String email;
    String estado;
    String cep;
    String descricao;
    List<String> competencias = [];

    @Override
    void exibirInformacoes() {
        println("Nome: $nome" +
                "\nEmail: $email" +
                "\nEstado: $estado" +
                "\nCEP: $cep" +
                "\nDescricão: $descricao" +
                "\nCompetências: ${competencias.join(", ")}");
    }
}
