package br.com.linketinder.model

class Vaga {
    Integer id;
    Empresa empresa;
    String nome;
    String cidade;
    String estado;
    String descricao;
    List<Competencia> competencias = [];

    void exibirInformacoes() {
        println("ID: $id" +
                "\nEmpresa: $empresa.nome" +
                "\nNome: $nome" +
                "\nCidade: $cidade" +
                "\nEstado: $estado" +
                "\nDescricão: $descricao" +
                "\nCompetências: ${competencias.join(", ")}");
        println("----------------");
    }
}
