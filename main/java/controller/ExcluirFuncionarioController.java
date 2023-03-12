/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JOptionPane;
import model.DBFuncionariosController;
import view.ExcluirFuncionarioView;

/**
 *
 * @author Victor
 */
public class ExcluirFuncionarioController{

    private static ExcluirFuncionarioController instancia;
    
    ExcluirFuncionarioView excluirFuncionarioView;
    
    private ExcluirFuncionarioController() {
        excluirFuncionarioView = new ExcluirFuncionarioView();
        
        excluirFuncionarioView.getBtnCancelar().addActionListener((ActionEvent e) -> {
            sair();
        });
        
        excluirFuncionarioView.getBtnExcluir().addActionListener((ActionEvent e) -> {
            try {
                if(excluirFuncionarioView.getInputCPF().getText().isEmpty()){
                    JOptionPane.showMessageDialog(excluirFuncionarioView, "O campo não pode estar vazio");
                }else{
                    DBFuncionariosController dbFuncionariosController = new DBFuncionariosController();
                    dbFuncionariosController.excluirDoArquivo(Long.parseLong(excluirFuncionarioView.getInputCPF().getText()), excluirFuncionarioView);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(excluirFuncionarioView, "Erro!");
            }
        });
        
        excluirFuncionarioView.setVisible(true);
    }
    
    public static ExcluirFuncionarioController getInstancia(){
        if(instancia==null){
            instancia = new ExcluirFuncionarioController();
        }
        return instancia;
    }
    
    public void sair() {
        excluirFuncionarioView.setVisible(false);
        excluirFuncionarioView.dispose();
        ListarFuncionarioController listarFuncionarioController = ListarFuncionarioController.getInstancia();
        listarFuncionarioController.listarFuncionarioView.setVisible(true);
        listarFuncionarioController.verFuncionarios();
    }
    
}
