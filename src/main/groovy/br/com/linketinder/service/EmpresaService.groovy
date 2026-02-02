package br.com.linketinder.service

import br.com.linketinder.model.Empresa

class EmpresaService {

    private List<Empresa> empresas = [];

    boolean adicionar(Empresa empresa) {
        empresas.add(empresa);
        return true;
    }

    List<Empresa> listar() {
        return new ArrayList<>(empresas);
    }
}
