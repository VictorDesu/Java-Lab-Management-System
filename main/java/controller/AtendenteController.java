/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import view.MenuAtendenteView;

/**
 *
 * @author Victor
 */
public class AtendenteController{
    
    private static AtendenteController instancia;
    
    MenuAtendenteView menuAtendenteView;
    
    private AtendenteController() {
        menuAtendenteView = new MenuAtendenteView();
        
        menuAtendenteView.getBtnClientData().addActionListener((ActionEvent e) -> {
            verClientes();
        });
        menuAtendenteView.getBtnExamesData().addActionListener((ActionEvent e) -> {
            verExames();
        });
        menuAtendenteView.getBtnSair().addActionListener((ActionEvent e) -> {
            sair();
        });
        
        menuAtendenteView.setVisible(true);
    }
    
    public static AtendenteController getInstancia(){
        if(instancia==null){
            instancia = new AtendenteController();
        }
        return instancia;
    }
    
    public void verClientes(){
        menuAtendenteView.setVisible(false);
        menuAtendenteView.dispose();
        ListarClientesController telaListarClientesController = ListarClientesController.getInstancia();
        telaListarClientesController.verClientes();
        telaListarClientesController.listarClientesView.setVisible(true);
    }
    
    public void verExames(){
        menuAtendenteView.setVisible(false);
        menuAtendenteView.dispose();
        ListarExamesController listarExameController = ListarExamesController.getInstancia();
        listarExameController.verExames();
        listarExameController.listaExamesView.setVisible(true);
    };

    public void sair() {
        menuAtendenteView.setVisible(false);
        menuAtendenteView.dispose();
        LoginController loginController = LoginController.getInstancia();
        loginController.loginView.setVisible(true);
    }
}
