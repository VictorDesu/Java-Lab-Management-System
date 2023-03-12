package controller;


import model.DBClientesController;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import types.Cliente;
import view.CadastrarClienteView;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Victor
 */
public class CadastrarClienteController{
    
    private static CadastrarClienteController instancia;
    
    CadastrarClienteView cadastrarClienteView;
    
    private CadastrarClienteController() {
        
        cadastrarClienteView = new CadastrarClienteView();
        
        cadastrarClienteView.getBtnLimpar().addActionListener((ActionEvent e) -> {
            limpar();
        });
        cadastrarClienteView.getBtnCadastrar().addActionListener((ActionEvent e) -> {
            try {
                cadastrarCliente();
            } catch (IOException ex) {
                Logger.getLogger(CadastrarClienteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        cadastrarClienteView.getBtnCancelar().addActionListener((ActionEvent e) -> {
            sair();
        });
        
        cadastrarClienteView.setVisible(true);
    }
    
    public static CadastrarClienteController getInstancia(){
        if(instancia==null){
            instancia = new CadastrarClienteController();
        }
        return instancia;
    }
    
    public void cadastrarCliente() throws IOException{
        if(cadastrarClienteView.getInputCPF().getText().isEmpty() || cadastrarClienteView.getInputEndereco().getText().isEmpty() || 
                cadastrarClienteView.getInputIdade().getText().isEmpty() || cadastrarClienteView.getInputNome().getText().isEmpty()){
            JOptionPane.showMessageDialog(cadastrarClienteView, "Os campos não podem estar vazios");
        }else{
            String nome = cadastrarClienteView.getInputNome().getText();
            String endereco = cadastrarClienteView.getInputEndereco().getText();
            int idade = Integer.parseInt(cadastrarClienteView.getInputIdade().getText());
            long cpf = Long.parseLong(cadastrarClienteView.getInputCPF().getText());
            String plano = cadastrarClienteView.getInputPlano().getText();
            
            Cliente cliente = new Cliente(cpf, nome, idade, endereco, plano);
            
            DBClientesController.escreverArquivoTXT_Clientes(cliente, cadastrarClienteView);
        }
    }

    public void limpar() {
        cadastrarClienteView.getInputNome().setText("");
        cadastrarClienteView.getInputCPF().setText("");
        cadastrarClienteView.getInputEndereco().setText("");
        cadastrarClienteView.getInputIdade().setText("");
        cadastrarClienteView.getInputPlano().setText("");
    }
    
    public void sair() {
        cadastrarClienteView.setVisible(false);
        cadastrarClienteView.dispose();
        ListarClientesController listarClientesController = ListarClientesController.getInstancia();
        listarClientesController.listarClientesView.setVisible(true);
        listarClientesController.verClientes();
    }
    
    
}
