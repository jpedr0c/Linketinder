package br.com.linketinder

import br.com.linketinder.controller.MenuController
import br.com.linketinder.database.ConexaoDB

class Main {
    static void main(String[] args) {
        try {
            new MenuController().iniciar()
        } finally {
            ConexaoDB.fechar()
        }
    }
}