//package br.com.linketinder
//
//import br.com.linketinder.controller.MenuController
//import br.com.linketinder.database.ConexaoDB
//
//class Main {
//    static void main(String[] args) {
//        try {
//            new MenuController().iniciar()
//        } finally {
//            ConexaoDB.fechar()
//        }
//    }
//}


package br.com.linketinder

import br.com.linketinder.controller.CandidatoController
import br.com.linketinder.database.ConexaoDB
import org.apache.catalina.startup.Tomcat

class Main {
    static void main(String[] args) {
        Tomcat tomcat = new Tomcat()
        tomcat.port = 8080

        String baseDir = System.getProperty("java.io.tmpdir")
        tomcat.baseDir = baseDir

        def context = tomcat.addContext("", baseDir)

        Tomcat.addServlet(context, "candidatoController", new CandidatoController())
        context.addServletMappingDecoded("/candidatos", "candidatoController")

//        Tomcat.addServlet(context, "empresaController", new EmpresaController())
//        context.addServletMappingDecoded("/empresas", "empresaController")
//
//        Tomcat.addServlet(context, "vagaController", new VagaController())
//        context.addServletMappingDecoded("/vagas", "vagaController")

        tomcat.start()
        println "Servidor iniciado em http://localhost:8080"
        tomcat.server.await()

        ConexaoDB.fechar()
    }
}