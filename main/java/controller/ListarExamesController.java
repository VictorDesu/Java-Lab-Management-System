/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import model.DBExamesController;
import types.Exame;
import view.ListarExamesView;

/**
 *
 * @author Victor
 */
public class ListarExamesController {
    
    private static ListarExamesController instancia;
    
    ListarExamesView listaExamesView;
    
    private ListarExamesController() {
        listaExamesView = new ListarExamesView();
        verExames();
        
        listaExamesView.getBtnSair().addActionListener((ActionEvent e) -> {
            sair();
        });
        listaExamesView.getBtnAddExame().addActionListener((ActionEvent e) -> {
            cadastrarExame();
        });
        listaExamesView.getBtnDeleteExame().addActionListener((ActionEvent e) -> {
            excluirExame();
        });
        listaExamesView.getBtnFiltrar().addActionListener((ActionEvent e) -> {
            filtrar();
        });
        
        listaExamesView.setVisible(true);
    }
    
    public static ListarExamesController getInstancia(){
        if(instancia==null){
            instancia = new ListarExamesController();
        }
        return instancia;
    }
    
    public void sair() {
        listaExamesView.setVisible(false);
        listaExamesView.dispose();
        
        switch (LoginController.getSessao()) {
            case 0 -> {
                GerenteController gerenteController = GerenteController.getInstancia();
                gerenteController.menuGerenteView.setVisible(true);
            }
            case 1 -> {
                AtendenteController atendenteController = AtendenteController.getInstancia();
                atendenteController.menuAtendenteView.setVisible(true);;
            }
            case 2 -> {
                EnfermeiroController enfermeiroController = EnfermeiroController.getInstancia();
                enfermeiroController.menuEnfermeiroView.setVisible(true);
            }
            default -> System.out.println("Sessão não encontrada");
        }
    }
    
    public void cadastrarExame(){
        listaExamesView.setVisible(false);
        listaExamesView.dispose();
        CadastrarExameController cadastrarExameController = CadastrarExameController.getInstancia();
        cadastrarExameController.cadastrarExameView.setVisible(true);
    }
    
    public void verExames() {
        DBExamesController dbExamesController = new DBExamesController();
        
        //Manipula as informações da tabela
        DefaultTableModel model = (DefaultTableModel) listaExamesView.getTabelaExames().getModel();
        
        //define o número de linhas
        model.setRowCount(dbExamesController.getListaExames().size());
        
        Exame exame;
        for(int i=0; i<dbExamesController.getListaExames().size(); i++){
            exame = dbExamesController.getListaExames().get(i);
            
            model.setValueAt(exame.getCliente().getCpf(), i, 0);
            model.setValueAt(exame.getTipo(), i, 1);
            model.setValueAt(exame.getData(), i, 2);
            model.setValueAt(exame.getResultado(), i, 3);
            model.setValueAt(exame.getPreco(), i, 4);
            model.setValueAt(exame.getId(), i, 5);
        }
        
        model.fireTableDataChanged();
    }
    
    public void excluirExame(){
        listaExamesView.setVisible(false);
        listaExamesView.dispose();
        ExcluirExameController excluirExameController = ExcluirExameController.getInstancia();
        excluirExameController.excluirExameView.setVisible(true);
    }
    
    public void filtrar(){
        DBExamesController dbExamesController = new DBExamesController();
        
        //Manipula as informações da tabela
        DefaultTableModel model = (DefaultTableModel) listaExamesView.getTabelaExames().getModel();
        
        int contLinhas = 1;
        Exame exame;
        
        switch (listaExamesView.getDropDown().getSelectedIndex()){
            case 0 -> {
                long CPFfiltrar = Long.parseLong(listaExamesView.getInputFiltro().getText());
                for(int i=0; i<dbExamesController.getListaExames().size(); i++){
                    exame = dbExamesController.getListaExames().get(i);
                    if(exame.getCliente().getCpf()==CPFfiltrar){
                        model.setRowCount(contLinhas);
                            
                        model.setValueAt(exame.getCliente().getCpf(), contLinhas-1, 0);
                        model.setValueAt(exame.getTipo(), contLinhas-1, 1);
                        model.setValueAt(exame.getData(), contLinhas-1, 2);
                        model.setValueAt(exame.getResultado(), contLinhas-1, 3);
                        model.setValueAt(exame.getPreco(), contLinhas-1, 4);
                        model.setValueAt(exame.getId(), contLinhas-1, 5);
                            
                        contLinhas++;
                    }
                }
            }
            case 1 -> {
                String tipoFiltrar = listaExamesView.getInputFiltro().getText();
                for(int i=0; i<dbExamesController.getListaExames().size(); i++){
                    exame = dbExamesController.getListaExames().get(i);
                    if(exame.getTipo().equals(tipoFiltrar)){
                        model.setRowCount(contLinhas);

                        model.setValueAt(exame.getCliente().getCpf(), contLinhas-1, 0);
                        model.setValueAt(exame.getTipo(), contLinhas-1, 1);
                        model.setValueAt(exame.getData(), contLinhas-1, 2);
                        model.setValueAt(exame.getResultado(), contLinhas-1, 3);
                        model.setValueAt(exame.getPreco(), contLinhas-1, 4);
                        model.setValueAt(exame.getId(), contLinhas-1, 5);

                        contLinhas++;
                    }
                }
            }
            case 2 -> {
                String dataFiltrar = listaExamesView.getInputFiltro().getText();
                for(int i=0; i<dbExamesController.getListaExames().size(); i++){
                    exame = dbExamesController.getListaExames().get(i);
                    if(exame.getData().equals(dataFiltrar)){
                        model.setRowCount(contLinhas);

                        model.setValueAt(exame.getCliente().getCpf(), contLinhas-1, 0);
                        model.setValueAt(exame.getTipo(), contLinhas-1, 1);
                        model.setValueAt(exame.getData(), contLinhas-1, 2);
                        model.setValueAt(exame.getResultado(), contLinhas-1, 3);
                        model.setValueAt(exame.getPreco(), contLinhas-1, 4);
                        model.setValueAt(exame.getId(), contLinhas-1, 5);

                        contLinhas++;
                    }
                }
            }
            case 3 -> {
                String resultadoFiltrar = listaExamesView.getInputFiltro().getText();
                for(int i=0; i<dbExamesController.getListaExames().size(); i++){
                    exame = dbExamesController.getListaExames().get(i);
                    if(exame.getResultado().equals(resultadoFiltrar)){
                        model.setRowCount(contLinhas);

                        model.setValueAt(exame.getCliente().getCpf(), contLinhas-1, 0);
                        model.setValueAt(exame.getTipo(), contLinhas-1, 1);
                        model.setValueAt(exame.getData(), contLinhas-1, 2);
                        model.setValueAt(exame.getResultado(), contLinhas-1, 3);
                        model.setValueAt(exame.getPreco(), contLinhas-1, 4);
                        model.setValueAt(exame.getId(), contLinhas-1, 5);

                        contLinhas++;
                    }
                }
            }
            case 4 -> {
                double precoFiltrar = Double.parseDouble(listaExamesView.getInputFiltro().getText());
                for(int i=0; i<dbExamesController.getListaExames().size(); i++){
                    exame = dbExamesController.getListaExames().get(i);
                    if(exame.getPreco()==precoFiltrar){
                        model.setRowCount(contLinhas);

                        model.setValueAt(exame.getCliente().getCpf(), contLinhas-1, 0);
                        model.setValueAt(exame.getTipo(), contLinhas-1, 1);
                        model.setValueAt(exame.getData(), contLinhas-1, 2);
                        model.setValueAt(exame.getResultado(), contLinhas-1, 3);
                        model.setValueAt(exame.getPreco(), contLinhas-1, 4);
                        model.setValueAt(exame.getId(), contLinhas-1, 5);

                        contLinhas++;
                    }
                }
            }
            case 5 -> {
                int IDFiltrar = Integer.parseInt(listaExamesView.getInputFiltro().getText());
                for(int i=0; i<dbExamesController.getListaExames().size(); i++){
                    exame = dbExamesController.getListaExames().get(i);
                    if(exame.getId()==IDFiltrar){
                        model.setRowCount(1);

                        model.setValueAt(exame.getCliente().getCpf(), 0, 0);
                        model.setValueAt(exame.getTipo(), 0, 1);
                        model.setValueAt(exame.getData(), 0, 2);
                        model.setValueAt(exame.getResultado(), 0, 3);
                        model.setValueAt(exame.getPreco(), 0, 4);
                        model.setValueAt(exame.getId(), 0, 5);
                    }
                }
            }
            default -> {}
        }
        
        model.fireTableDataChanged();
    }
    
}
