package groovy

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
