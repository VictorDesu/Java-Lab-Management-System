/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import view.MenuGerenteView;

/**
 *
 * @author aluno
 */
public class GerenteController{
    
    private static GerenteController instancia;

    MenuGerenteView menuGerenteView;
    
    private GerenteController() {
        menuGerenteView = new MenuGerenteView();
        
        menuGerenteView.getBtnClientData().addActionListener((ActionEvent e) -> {
            verClientes();
        });
        menuGerenteView.getBtnFuncionarioData().addActionListener((ActionEvent e) -> {
            verFuncionarios();
        });
        menuGerenteView.getBtnExamesData().addActionListener((ActionEvent e) -> {
            verExames();
        });
        menuGerenteView.getBtnSair().addActionListener((ActionEvent e) -> {
            sair();
        });
        
        menuGerenteView.setVisible(true);
    }
    
    public static GerenteController getInstancia(){
        if(instancia==null){
            instancia = new GerenteController();
        }
        return instancia;
    }
    
    public void verClientes(){
        menuGerenteView.setVisible(false);
        menuGerenteView.dispose();
        ListarClientesController telaListarClientesController = ListarClientesController.getInstancia();
        telaListarClientesController.verClientes();
        telaListarClientesController.listarClientesView.setVisible(true);
    }
    
    public void verFuncionarios(){
        menuGerenteView.setVisible(false);
        menuGerenteView.dispose();
        ListarFuncionarioController listarFuncionarioController = ListarFuncionarioController.getInstancia();
        listarFuncionarioController.verFuncionarios();
        listarFuncionarioController.listarFuncionarioView.setVisible(true);
    }
    
    public void verExames(){
        menuGerenteView.setVisible(false);
        menuGerenteView.dispose();
        ListarExamesController listarExameController = ListarExamesController.getInstancia();
        listarExameController.verExames();
        listarExameController.listaExamesView.setVisible(true);
    };

    public void sair() {
        menuGerenteView.setVisible(false);
        menuGerenteView.dispose();
        LoginController loginController = LoginController.getInstancia();
        loginController.loginView.setVisible(true);
    }
}
