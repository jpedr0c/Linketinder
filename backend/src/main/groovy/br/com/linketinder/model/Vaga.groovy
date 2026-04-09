package br.com.linketinder.model

class Vaga {
    Integer id;
    Integer empresaId;
    String nome;
    String cidade;
    String estado;
    String descricao;
    List<Competencia> competencias = [];

    void exibirInformacoes() {
        println("ID: $id" +
                "\nEmpresa: $empresaId.nome" +
                "\nNome: $nome" +
                "\nCidade: $cidade" +
                "\nEstado: $estado" +
                "\nDescricão: $descricao" +
                "\nCompetências: ${competencias.join(", ")}");
        println("----------------");
    }
}
