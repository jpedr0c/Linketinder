package br.com.linketinder.service

import br.com.linketinder.model.Candidato

class CandidatoService {

    private List<Candidato> candidatos = []

    boolean adicionar(Candidato candidato) {
        candidatos.add(candidato);
        return true;
    }

    List<Candidato> listar() {
        return new ArrayList<>(candidatos);
    }
}
