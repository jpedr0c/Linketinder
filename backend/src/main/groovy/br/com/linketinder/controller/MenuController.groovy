package br.com.linketinder.controller

import br.com.linketinder.service.CandidatoService
import br.com.linketinder.service.EmpresaService
import br.com.linketinder.view.MenuView

class MenuController {

    private final MenuView menuView
    private final CandidatoService candidatoService
    private final EmpresaService empresaService

    MenuController() {
        this.menuView = new MenuView()
        this.candidatoService = new CandidatoService()
        this.empresaService = new EmpresaService()
    }

    void iniciar() {
        String opcao = ""
        while (opcao != "0") {
            opcao = menuView.exibirMenuPrincipal()
            processarOpcao(opcao)
        }
    }

    private void processarOpcao(String opcao) {
        switch (opcao) {
            case "1":
                candidatoService.inserir();
                break
            case "2":
                empresaService.inserir();
                break
            case "5":
                candidatoService.listarTodos();
                break
            case "6":
                empresaService.listarTodos();
                break
            case "9":
                candidatoService.atualizar();
                break
            case "10":
                empresaService.atualizar();
                break
            case "13":
                candidatoService.deletar();
                break
            case "14":
                empresaService.deletar();
                break
            case "0":
                menuView.exibirEncerramento();
                break
            default:
                menuView.exibirOpcaoInvalida()
        }
    }
}