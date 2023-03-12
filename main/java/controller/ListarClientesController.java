/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.DBClientesController;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import types.Cliente;
import view.ListarClientesView;

/**
 *
 * @author Victor
 */
public class ListarClientesController{

    private static ListarClientesController instancia;
    
    ListarClientesView listarClientesView;
    
    private ListarClientesController(){
        listarClientesView = new ListarClientesView();
        verClientes();
        
        listarClientesView.getBtnSair().addActionListener((ActionEvent e) -> {
            sair();
        });
        listarClientesView.getBtnFiltrar().addActionListener((ActionEvent e) -> {
            filtrar();
        });
        listarClientesView.getBtnAddClient().addActionListener((ActionEvent e) -> {
            cadastrarCliente();
        });
        listarClientesView.getBtnDeleteClient().addActionListener((ActionEvent e) -> {
            excluirCliente();
        });
        
        listarClientesView.setVisible(true);
    }
    
    public static ListarClientesController getInstancia(){
        if(instancia==null){
            instancia = new ListarClientesController();
        }
        return instancia;
    }

    public void verClientes() {
        DBClientesController dbClientesController = new DBClientesController();
        
        //Manipula as informações da tabela
        DefaultTableModel model = (DefaultTableModel) listarClientesView.getTabelaClientes().getModel();
        
        //define o número de linhas
        model.setRowCount(dbClientesController.getListaClientes().size());
        
        Cliente cliente;
        for(int i=0; i<dbClientesController.getListaClientes().size(); i++){
            cliente = dbClientesController.getListaClientes().get(i);
            
            model.setValueAt(cliente.getCpf(), i, 0);
            model.setValueAt(cliente.getNome(), i, 1);
            model.setValueAt(cliente.getIdade(), i, 2);
            model.setValueAt(cliente.getEndereco(), i, 3);
            model.setValueAt(cliente.getPlano(), i, 4);
        }
        
        model.fireTableDataChanged();
    }
    
    public void sair() {
        listarClientesView.setVisible(false);
        listarClientesView.dispose();
        
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
        DBClientesController dbClientesController = new DBClientesController();
        
        //Manipula as informações da tabela
        DefaultTableModel model = (DefaultTableModel) listarClientesView.getTabelaClientes().getModel();
        
        int contLinhas = 1;
        Cliente cliente;
        
        switch (listarClientesView.getDropDown().getSelectedIndex()){
            case 0 -> {
                long CPFfiltrar = Long.parseLong(listarClientesView.getInputFiltro().getText());
                model.setRowCount(1);
                for(int i=0; i<dbClientesController.getListaClientes().size(); i++){
                    cliente = dbClientesController.getListaClientes().get(i);
                    if(cliente.getCpf()==CPFfiltrar){
                        model.setValueAt(cliente.getCpf(), 0, 0);
                        model.setValueAt(cliente.getNome(), 0, 1);
                        model.setValueAt(cliente.getIdade(), 0, 2);
                        model.setValueAt(cliente.getEndereco(), 0, 3);
                        model.setValueAt(cliente.getPlano(), 0, 4);
                    }
                }
            }
            case 1 -> {
                String nomeFiltrar = listarClientesView.getInputFiltro().getText();
                for(int i=0; i<dbClientesController.getListaClientes().size(); i++){
                    cliente = dbClientesController.getListaClientes().get(i);
                    if(cliente.getNome().equals(nomeFiltrar)){
                        model.setRowCount(contLinhas);

                        model.setValueAt(cliente.getCpf(), contLinhas-1, 0);
                        model.setValueAt(cliente.getNome(), contLinhas-1, 1);
                        model.setValueAt(cliente.getIdade(), contLinhas-1, 2);
                        model.setValueAt(cliente.getEndereco(), contLinhas-1, 3);
                        model.setValueAt(cliente.getPlano(), contLinhas-1, 4);

                        contLinhas++;
                    }
                }
            }
            case 2 -> {
                int idadeFiltrar = Integer.parseInt(listarClientesView.getInputFiltro().getText());
                for(int i=0; i<dbClientesController.getListaClientes().size(); i++){
                    cliente = dbClientesController.getListaClientes().get(i);
                    if(cliente.getIdade()==idadeFiltrar){
                        model.setRowCount(contLinhas);

                        model.setValueAt(cliente.getCpf(), contLinhas-1, 0);
                        model.setValueAt(cliente.getNome(), contLinhas-1, 1);
                        model.setValueAt(cliente.getIdade(), contLinhas-1, 2);
                        model.setValueAt(cliente.getEndereco(), contLinhas-1, 3);
                        model.setValueAt(cliente.getPlano(), contLinhas-1, 4);

                        contLinhas++;
                    }
                }
            }
            case 3 -> {
                String enderecoFiltrar = listarClientesView.getInputFiltro().getText();
                for(int i=0; i<dbClientesController.getListaClientes().size(); i++){
                    cliente = dbClientesController.getListaClientes().get(i);
                    if(cliente.getEndereco().equals(enderecoFiltrar)){
                        model.setRowCount(contLinhas);

                        model.setValueAt(cliente.getCpf(), contLinhas-1, 0);
                        model.setValueAt(cliente.getNome(), contLinhas-1, 1);
                        model.setValueAt(cliente.getIdade(), contLinhas-1, 2);
                        model.setValueAt(cliente.getEndereco(), contLinhas-1, 3);
                        model.setValueAt(cliente.getPlano(), contLinhas-1, 4);

                        contLinhas++;
                    }
                }
            }
            case 4 -> {
                String planoFiltrar = listarClientesView.getInputFiltro().getText();
                for(int i=0; i<dbClientesController.getListaClientes().size(); i++){
                    cliente = dbClientesController.getListaClientes().get(i);
                    if(cliente.getPlano().equals(planoFiltrar)){
                        model.setRowCount(contLinhas);

                        model.setValueAt(cliente.getCpf(), contLinhas-1, 0);
                        model.setValueAt(cliente.getNome(), contLinhas-1, 1);
                        model.setValueAt(cliente.getIdade(), contLinhas-1, 2);
                        model.setValueAt(cliente.getEndereco(), contLinhas-1, 3);
                        model.setValueAt(cliente.getPlano(), contLinhas-1, 4);

                        contLinhas++;
                    }
                }
            }
            default -> {}
        }
        
        model.fireTableDataChanged();
    }
    
    public void cadastrarCliente(){
        listarClientesView.setVisible(false);
        listarClientesView.dispose();
        CadastrarClienteController cadastrarClienteController = CadastrarClienteController.getInstancia();
        cadastrarClienteController.cadastrarClienteView.setVisible(true);
    }
    
    public void excluirCliente(){
        listarClientesView.setVisible(false);
        listarClientesView.dispose();
        ExcluirClienteController excluirClienteController = ExcluirClienteController.getInstancia();
        excluirClienteController.excluirClienteView.setVisible(true);
    }
}
