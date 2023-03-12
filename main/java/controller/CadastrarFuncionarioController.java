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
import types.Funcionario;
import types.Gerente;
import types.PessoalLimpeza;
import view.CadastrarFuncionarioView;

/**
 *
 * @author Victor
 */
public class CadastrarFuncionarioController{
    
    private static CadastrarFuncionarioController instancia;

    CadastrarFuncionarioView cadastrarFuncionarioView;
    
    private CadastrarFuncionarioController() {
        cadastrarFuncionarioView = new CadastrarFuncionarioView();
        
        cadastrarFuncionarioView.getBtnLimpar().addActionListener((ActionEvent e) -> {
            limpar();
        });
        cadastrarFuncionarioView.getBtnCadastrar().addActionListener((ActionEvent e) -> {
            cadastrarFuncionario();
        });
        cadastrarFuncionarioView.getBtnCancelar().addActionListener((ActionEvent e) -> {
            sair();
        });
        
        cadastrarFuncionarioView.setVisible(true);
    }
    
    public static CadastrarFuncionarioController getInstancia(){
        if(instancia==null){
            instancia = new CadastrarFuncionarioController();
        }
        return instancia;
    }
    
    public void cadastrarFuncionario(){
        if(cadastrarFuncionarioView.getInputCPF().getText().isEmpty() || cadastrarFuncionarioView.getInputHoras().getText().isEmpty() || 
                cadastrarFuncionarioView.getInputIdade().getText().isEmpty() || cadastrarFuncionarioView.getInputNome().getText().isEmpty() 
                || cadastrarFuncionarioView.getInputSalario().getText().isEmpty() || cadastrarFuncionarioView.getInputSenhaAcesso().getText().isEmpty()){
            JOptionPane.showMessageDialog(cadastrarFuncionarioView, "Os campos não podem estar vazios");
        }else{
            String nome = cadastrarFuncionarioView.getInputNome().getText();
            int idade = Integer.parseInt(cadastrarFuncionarioView.getInputIdade().getText());
            long cpf = Long.parseLong(cadastrarFuncionarioView.getInputCPF().getText());
            double salario = Double.parseDouble(cadastrarFuncionarioView.getInputSalario().getText());
            float horasSemanais = Float.parseFloat(cadastrarFuncionarioView.getInputHoras().getText());
            String senha = cadastrarFuncionarioView.getInputSenhaAcesso().getText();
            int cargo;
            
            Funcionario funcionario;
            DBFuncionariosController dbFuncionariosController = new DBFuncionariosController();
            switch (cadastrarFuncionarioView.getInputCargo().getSelectedIndex()) {
                case 0 -> {
                    cargo = 0;
                    funcionario = new Atendente(cpf, nome, idade, salario, horasSemanais, senha, cargo);
                    dbFuncionariosController.escreverArquivoTXT_Funcionarios(funcionario, cadastrarFuncionarioView, cargo);
                }
                case 1 -> {
                    cargo = 1;
                    funcionario = new Gerente(cpf, nome, idade, salario, horasSemanais, senha, cargo);
                    dbFuncionariosController.escreverArquivoTXT_Funcionarios(funcionario, cadastrarFuncionarioView, cargo);
                }
                case 2 -> {
                    senha = null;
                    cargo = 2;
                    funcionario = new Enfermeiro(cpf, nome, idade, salario, horasSemanais, senha, cargo);
                    dbFuncionariosController.escreverArquivoTXT_Funcionarios(funcionario, cadastrarFuncionarioView, cargo);
                }
                case 3 -> {
                    senha = null;
                    cargo = 3;
                    funcionario = new PessoalLimpeza(cpf, nome, idade, salario, horasSemanais, senha, cargo);
                    dbFuncionariosController.escreverArquivoTXT_Funcionarios(funcionario, cadastrarFuncionarioView, cargo);
                }
                default -> {}
            }
        }
    }
    
    public void limpar(){
        cadastrarFuncionarioView.getInputNome().setText("");
        cadastrarFuncionarioView.getInputCPF().setText("");
        cadastrarFuncionarioView.getInputSenhaAcesso().setText("");
        cadastrarFuncionarioView.getInputIdade().setText("");
        cadastrarFuncionarioView.getInputHoras().setText("");
        cadastrarFuncionarioView.getInputSalario().setText("");
    }
    
    public void sair(){
        cadastrarFuncionarioView.setVisible(false);
        cadastrarFuncionarioView.dispose();
        ListarFuncionarioController listarFuncionarioController = ListarFuncionarioController.getInstancia();
        listarFuncionarioController.listarFuncionarioView.setVisible(true);
        listarFuncionarioController.verFuncionarios();
    }
}
