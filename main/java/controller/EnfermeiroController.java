/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import view.MenuEnfermeiroView;

/**
 *
 * @author Victor
 */
public class EnfermeiroController {
    private static EnfermeiroController instancia;

    MenuEnfermeiroView menuEnfermeiroView;
    
    private EnfermeiroController() {
        menuEnfermeiroView = new MenuEnfermeiroView();
        
        menuEnfermeiroView.getBtnExamesData().addActionListener((ActionEvent e) -> {
            verExames();
        });
        menuEnfermeiroView.getBtnSair().addActionListener((ActionEvent e) -> {
            sair();
        });
        
        menuEnfermeiroView.setVisible(true);
    }
    
    public static EnfermeiroController getInstancia(){
        if(instancia==null){
            instancia = new EnfermeiroController();
        }
        return instancia;
    }
    
    public void verExames(){
        menuEnfermeiroView.setVisible(false);
        menuEnfermeiroView.dispose();
        ListarExamesController listarExameController = ListarExamesController.getInstancia();
        listarExameController.verExames();
        listarExameController.listaExamesView.setVisible(true);
    };

    public void sair() {
        menuEnfermeiroView.setVisible(false);
        menuEnfermeiroView.dispose();
        LoginController loginController = LoginController.getInstancia();
        loginController.loginView.setVisible(true);
    }
}
