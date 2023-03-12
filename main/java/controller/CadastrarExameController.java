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
import model.DBClientesController;
import model.DBExamesController;
import types.Cliente;
import types.Exame;
import view.CadastrarExameView;

/**
 *
 * @author Victor
 */
public class CadastrarExameController{
    
    private static CadastrarExameController instancia;
    
    CadastrarExameView cadastrarExameView;
    
    private CadastrarExameController() {
        cadastrarExameView = new CadastrarExameView();
        
        cadastrarExameView.getBtnCancelar().addActionListener((ActionEvent e) -> {
            sair();
        });
        cadastrarExameView.getBtnLimpar().addActionListener((ActionEvent e) -> {
            limpar();
        });
        cadastrarExameView.getBtnCadastrar().addActionListener((ActionEvent e) -> {
            cadastrarExame();
        });
        
        cadastrarExameView.setVisible(true);
    }
    
    public static CadastrarExameController getInstancia(){
        if(instancia==null){
            instancia = new CadastrarExameController();
        }
        return instancia;
    }
    
    public void cadastrarExame(){
        if(cadastrarExameView.getInputCPF().getText().isEmpty() || cadastrarExameView.getInputTipo().getText().isEmpty() || 
                cadastrarExameView.getInputData().getText().isEmpty() || cadastrarExameView.getInputResultado().getText().isEmpty() ||
                cadastrarExameView.getInputPreco().getText().isEmpty() ){
            JOptionPane.showMessageDialog(cadastrarExameView, "Os campos não podem estar vazios");
        }else{
            DBClientesController dbClientesController = new DBClientesController();
            DBExamesController dbExamesController = new DBExamesController();
            
            boolean achou=false;
            long cpf = Long.parseLong(cadastrarExameView.getInputCPF().getText());
            Cliente cliente = new Cliente();
            for(int i=0; i<dbClientesController.getListaClientes().size(); i++){
                if(dbClientesController.getListaClientes().get(i).getCpf()==cpf){
                    cliente = dbClientesController.getListaClientes().get(i);
                    achou=true;
                }
            }
            
            String tipo = cadastrarExameView.getInputTipo().getText();
            double preco = Double.parseDouble(cadastrarExameView.getInputPreco().getText());
            String data = cadastrarExameView.getInputData().getText();
            String resultado = cadastrarExameView.getInputResultado().getText();
            int id = dbExamesController.getListaExames().size() + 1;
            
            if(achou==true){
                Exame exame = new Exame(cliente, tipo, data, resultado, preco, id);
                try {
                    dbExamesController.escreverArquivoTXT_Exames(exame, cadastrarExameView);
                } catch (IOException ex) {
                    Logger.getLogger(CadastrarExameController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(cadastrarExameView, "Não existe um cliente com esse CPF no sistema!");
            }
        }
    }
    
    public void limpar(){
        cadastrarExameView.getInputCPF().setText("");
        cadastrarExameView.getInputTipo().setText("");
        cadastrarExameView.getInputData().setText("");
        cadastrarExameView.getInputResultado().setText("");
        cadastrarExameView.getInputPreco().setText("");
    }
    
    public void sair(){
        cadastrarExameView.setVisible(false);
        cadastrarExameView.dispose();
        ListarExamesController listarExamesController = ListarExamesController.getInstancia();
        listarExamesController.listaExamesView.setVisible(true);
        listarExamesController.verExames();
    };
}