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
                "Email: $email" +
                "Estado: $estado" +
                "CEP: $cep" +
                "Descricão: $descricao" +
                "Competências: ${competencias.join(", ")}");
    }
}
