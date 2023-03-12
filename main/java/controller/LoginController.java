/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.DBFuncionariosController;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import types.Atendente;
import types.Enfermeiro;
import types.Gerente;
import view.LoginView;

/**
 *
 * @author aluno
 */
public class LoginController{
    
    private static LoginController instancia;

    //guarda se está logado como gerente ou atendente
    static private int sessao; //0 = gerente, 1 = atendente
    static public int getSessao() {
        return sessao;
    }
    
    LoginView loginView;
    private LoginController() {
        loginView = new LoginView();
        DBFuncionariosController dbFuncionariosController = new DBFuncionariosController();
         
        loginView.getBtnLimpar().addActionListener((ActionEvent e) -> {
            limpar();
        });
        loginView.getBtnEntrar().addActionListener((ActionEvent e) -> {
            fazerLogin();
        });
        
        loginView.setVisible(true);
    }
    
    public static LoginController getInstancia(){
        if(instancia==null){
            instancia = new LoginController();
        }
        return instancia;
    }
    
    public void limpar(){
        loginView.getInputCPF().setText("");
        loginView.getInputSenha().setText("");
    }
    
    private void fazerLogin(){
        //verifica se os campos estão vazios
        if(loginView.getInputCPF().getText().isEmpty() || loginView.getInputSenha().getText().isEmpty()){
            JOptionPane.showMessageDialog(loginView, "Os campos não podem estar vazios");
        }
        else{
            DBFuncionariosController dbFuncionariosController = new DBFuncionariosController();
            
            long CPFAcesso = Long.parseLong(loginView.getInputCPF().getText());
            String senhaAcesso = loginView.getInputSenha().getText();
            boolean achouConta = false;
            
            for(int i=0; i<dbFuncionariosController.getListaFuncionarios().size(); i++){
                //verifica se o CPF e a senha estão corretos
                if(dbFuncionariosController.getListaFuncionarios().get(i).getCpf()==CPFAcesso && 
                        dbFuncionariosController.getListaFuncionarios().get(i).getSenha().equals(senhaAcesso)){
                    
                    //verifica o tipo de login
                    if(dbFuncionariosController.getListaFuncionarios().get(i) instanceof Gerente){
                        loginView.setVisible(false);
                        loginView.dispose();
                        GerenteController gerenteController = GerenteController.getInstancia();
                        gerenteController.menuGerenteView.setVisible(true);
                        sessao = 0;
                    }
                    else if(dbFuncionariosController.getListaFuncionarios().get(i) instanceof Atendente){
                        loginView.setVisible(false);
                        loginView.dispose();
                        AtendenteController atendenteController = AtendenteController.getInstancia();
                        atendenteController.menuAtendenteView.setVisible(true);
                        sessao = 1;
                    }
                    else if(dbFuncionariosController.getListaFuncionarios().get(i) instanceof Enfermeiro){
                        loginView.setVisible(false);
                        loginView.dispose();
                        EnfermeiroController enfermeiroController = EnfermeiroController.getInstancia();
                        enfermeiroController.menuEnfermeiroView.setVisible(true);
                        sessao = 2;
                    }
                    achouConta=true;
                }
            }
            if(achouConta==false){
                JOptionPane.showMessageDialog(loginView, "CPF ou senha incorretos!");
            }
        }
    }

}
