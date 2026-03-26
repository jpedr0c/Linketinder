package br.com.linketinder.model

class Empresa extends PessoaBase{
    String cnpj;
    String pais;

    @Override
    void exibirInformacoes() {
        super.exibirInformacoes();
        println("CNPJ: $cnpj" +
                "\nPaís: $pais");
        println("----------------");
    }
}
