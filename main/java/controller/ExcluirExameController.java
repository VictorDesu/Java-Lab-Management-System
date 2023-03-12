/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.DBExamesController;
import view.ExcluirExameView;

/**
 *
 * @author aluno
 */
public class ExcluirExameController {
    
    private static ExcluirExameController instancia;
    
    ExcluirExameView excluirExameView;

    private ExcluirExameController() {
        excluirExameView = new ExcluirExameView();
        
        excluirExameView.getBtnCancelar().addActionListener((ActionEvent e) -> {
            sair();
        });
        excluirExameView.getBtnExcluir().addActionListener((ActionEvent e) -> {
            try {
                excluirExame();
            } catch (IOException ex) {
                Logger.getLogger(ExcluirExameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        excluirExameView.setVisible(true);
    }
    
    public static ExcluirExameController getInstancia(){
        if(instancia==null){
            instancia = new ExcluirExameController();
        }
        return instancia;
    }
    
    public void sair() {
        excluirExameView.setVisible(false);
        excluirExameView.dispose();
        ListarExamesController listarExamesController = ListarExamesController.getInstancia();
        listarExamesController.listaExamesView.setVisible(true);
        listarExamesController.verExames();
    }
    
    public void excluirExame() throws IOException{
        if(excluirExameView.getInputID().getText().isEmpty()){
            JOptionPane.showMessageDialog(excluirExameView, "O campo não pode estar vazio");
        }else{
            DBExamesController dbExamesController = new DBExamesController();
            dbExamesController.excluirDoArquivo(Integer.parseInt(excluirExameView.getInputID().getText()), excluirExameView);
        }
    }
    
    
}
