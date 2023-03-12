/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JOptionPane;
import model.DBClientesController;
import view.ExcluirClienteView;

/**
 *
 * @author Victor
 */
public class ExcluirClienteController{
    
    private static ExcluirClienteController instancia;

    ExcluirClienteView excluirClienteView;
    
    private ExcluirClienteController() {
        excluirClienteView = new ExcluirClienteView();
        
        excluirClienteView.getBtnCancelar().addActionListener((ActionEvent e) -> {
            sair();
        });
        excluirClienteView.getBtnExcluir().addActionListener((ActionEvent e) -> {
            try {
                if(excluirClienteView.getInputCPF().getText().isEmpty()){
                    JOptionPane.showMessageDialog(excluirClienteView, "O campo não pode estar vazio");
                }else{
                    DBClientesController dbClientesController = new DBClientesController();
                    dbClientesController.excluirDoArquivo(Long.parseLong(excluirClienteView.getInputCPF().getText()), excluirClienteView);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(excluirClienteView, "Erro!");
            }
        });
        
        excluirClienteView.setVisible(true);
    }
    
    public static ExcluirClienteController getInstancia(){
        if(instancia==null){
            instancia = new ExcluirClienteController();
        }
        return instancia;
    }
    
    public void sair() {
        excluirClienteView.setVisible(false);
        excluirClienteView.dispose();
        ListarClientesController listarClientesController = ListarClientesController.getInstancia();
        listarClientesController.listarClientesView.setVisible(true);
        listarClientesController.verClientes();
    }
    
}
