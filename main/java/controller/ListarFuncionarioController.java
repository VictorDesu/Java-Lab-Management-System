/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.DBFuncionariosController;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import types.Funcionario;
import view.ListarFuncionariosView;

/**
 *
 * @author Victor
 */
public class ListarFuncionarioController{
    
    private static ListarFuncionarioController instancia;

    ListarFuncionariosView listarFuncionarioView;
    
    private ListarFuncionarioController(){
        listarFuncionarioView = new ListarFuncionariosView();
        verFuncionarios();
        
        listarFuncionarioView.getBtnSair().addActionListener((ActionEvent e) -> {
            sair();
        });
        listarFuncionarioView.getBtnFiltrar().addActionListener((ActionEvent e) -> {
            filtrar();
        });
        listarFuncionarioView.getBtnAddFuncionario().addActionListener((ActionEvent e) -> {
            cadastrarFuncionario();
        });
        listarFuncionarioView.getBtnDeleteFuncionario().addActionListener((ActionEvent e) -> {
            excluirFuncionario();
        });
        
        listarFuncionarioView.setVisible(true);
    }
    
    public static ListarFuncionarioController getInstancia(){
        if(instancia==null){
            instancia = new ListarFuncionarioController();
        }
        return instancia;
    }
    
    public void verFuncionarios(){
        DBFuncionariosController dbFuncionariosController = new DBFuncionariosController();
        
        //Manipula as informações da tabela
        DefaultTableModel model = (DefaultTableModel) listarFuncionarioView.getTabelaFuncionarios().getModel();
        
        model.setRowCount(dbFuncionariosController.getListaFuncionarios().size());
        
        Funcionario funcionario;
        for(int i=0; i<dbFuncionariosController.getListaFuncionarios().size(); i++){
            funcionario = dbFuncionariosController.getListaFuncionarios().get(i);
            
            model.setValueAt(funcionario.getCpf(), i, 0);
            model.setValueAt(funcionario.getNome(), i, 1);
            model.setValueAt(funcionario.getIdade(), i, 2);
            model.setValueAt(funcionario.getSalario(), i, 3);
            model.setValueAt(funcionario.getHorasSemanais(), i, 4);
        }
        
        model.fireTableDataChanged();
    }

    public void sair() {
        listarFuncionarioView.setVisible(false);
        listarFuncionarioView.dispose();
        switch (LoginController.getSessao()) {
            case 0 -> {
                GerenteController gerenteController = GerenteController.getInstancia();
                gerenteController.menuGerenteView.setVisible(true);
            }
            case 1 -> {
                AtendenteController atendenteController = AtendenteController.getInstancia();
                atendenteController.menuAtendenteView.setVisible(true);
            }
            case 2 -> {
                EnfermeiroController enfermeiroController = EnfermeiroController.getInstancia();
                enfermeiroController.menuEnfermeiroView.setVisible(true);
            }
            default -> System.out.println("Sessão não encontrada");
        }
    }
    
    public void filtrar(){
        DBFuncionariosController dbFuncionariosController = new DBFuncionariosController();
        
        //Manipula as informações da tabela
        DefaultTableModel model = (DefaultTableModel) listarFuncionarioView.getTabelaFuncionarios().getModel();
        
        int contLinhas = 1;
        Funcionario funcionario;
        
        switch (listarFuncionarioView.getDropDown().getSelectedIndex()){
            case 0 -> {
                long CPFfiltrar = Long.parseLong(listarFuncionarioView.getInputFiltro().getText());
                model.setRowCount(1);
                for(int i=0; i<dbFuncionariosController.getListaFuncionarios().size(); i++){
                    funcionario = dbFuncionariosController.getListaFuncionarios().get(i);
                    if(funcionario.getCpf()==CPFfiltrar){
                        model.setValueAt(funcionario.getCpf(), 0, 0);
                        model.setValueAt(funcionario.getNome(), 0, 1);
                        model.setValueAt(funcionario.getIdade(), 0, 2);
                        model.setValueAt(funcionario.getSalario(), 0, 3);
                        model.setValueAt(funcionario.getHorasSemanais(), 0, 4);
                    }
                }
            }
            case 1 -> {
                String nomeFiltrar = listarFuncionarioView.getInputFiltro().getText();
                for(int i=0; i<dbFuncionariosController.getListaFuncionarios().size(); i++){
                    funcionario = dbFuncionariosController.getListaFuncionarios().get(i);
                    if(funcionario.getNome().equals(nomeFiltrar)){
                        model.setRowCount(contLinhas);

                        model.setValueAt(funcionario.getCpf(), contLinhas-1, 0);
                        model.setValueAt(funcionario.getNome(), contLinhas-1, 1);
                        model.setValueAt(funcionario.getIdade(), contLinhas-1, 2);
                        model.setValueAt(funcionario.getSalario(), contLinhas-1, 3);
                        model.setValueAt(funcionario.getHorasSemanais(), contLinhas-1, 4);

                        contLinhas++;
                    }
                }
            }
            case 2 -> {
                int idadeFiltrar = Integer.parseInt(listarFuncionarioView.getInputFiltro().getText());
                for(int i=0; i<dbFuncionariosController.getListaFuncionarios().size(); i++){
                    funcionario = dbFuncionariosController.getListaFuncionarios().get(i);
                    if(funcionario.getIdade()==idadeFiltrar){
                        model.setRowCount(contLinhas);

                        model.setValueAt(funcionario.getCpf(), contLinhas-1, 0);
                        model.setValueAt(funcionario.getNome(), contLinhas-1, 1);
                        model.setValueAt(funcionario.getIdade(), contLinhas-1, 2);
                        model.setValueAt(funcionario.getSalario(), contLinhas-1, 3);
                        model.setValueAt(funcionario.getHorasSemanais(), contLinhas-1, 4);

                        contLinhas++;
                    }
                }
            }
            case 3 -> {
                double salarioFiltrar = Double.parseDouble(listarFuncionarioView.getInputFiltro().getText());
                for(int i=0; i<dbFuncionariosController.getListaFuncionarios().size(); i++){
                    funcionario = dbFuncionariosController.getListaFuncionarios().get(i);
                    if(funcionario.getSalario()==salarioFiltrar){
                        model.setRowCount(contLinhas);

                        model.setValueAt(funcionario.getCpf(), contLinhas-1, 0);
                        model.setValueAt(funcionario.getNome(), contLinhas-1, 1);
                        model.setValueAt(funcionario.getIdade(), contLinhas-1, 2);
                        model.setValueAt(funcionario.getSalario(), contLinhas-1, 3);
                        model.setValueAt(funcionario.getHorasSemanais(), contLinhas-1, 4);

                        contLinhas++;
                    }
                }
            }
            case 4 -> {
                float horasFiltrar = Float.parseFloat(listarFuncionarioView.getInputFiltro().getText());
                for(int i=0; i<dbFuncionariosController.getListaFuncionarios().size(); i++){
                    funcionario = dbFuncionariosController.getListaFuncionarios().get(i);
                    if(funcionario.getHorasSemanais()==horasFiltrar){
                        model.setRowCount(contLinhas);

                        model.setValueAt(funcionario.getCpf(), contLinhas-1, 0);
                        model.setValueAt(funcionario.getNome(), contLinhas-1, 1);
                        model.setValueAt(funcionario.getIdade(), contLinhas-1, 2);
                        model.setValueAt(funcionario.getSalario(), contLinhas-1, 3);
                        model.setValueAt(funcionario.getHorasSemanais(), contLinhas-1, 4);

                        contLinhas++;
                    }
                }
            }
            default -> {}
        }
        
        model.fireTableDataChanged();
    }
    
    public void cadastrarFuncionario(){
        listarFuncionarioView.setVisible(false);
        listarFuncionarioView.dispose();
        CadastrarFuncionarioController cadastrarFuncionarioController = CadastrarFuncionarioController.getInstancia();
        cadastrarFuncionarioController.cadastrarFuncionarioView.setVisible(true);
    }
    
    public void excluirFuncionario(){
        listarFuncionarioView.setVisible(false);
        listarFuncionarioView.dispose();
        ExcluirFuncionarioController excluirFuncionarioController = ExcluirFuncionarioController.getInstancia();
        excluirFuncionarioController.excluirFuncionarioView.setVisible(true);
    }
}
