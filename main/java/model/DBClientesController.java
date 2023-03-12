/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import types.Cliente;
import view.CadastrarClienteView;
import view.ExcluirClienteView;

/**
 *
 * @author aluno
 */
public class DBClientesController {
    
    //lista de clientes
    private ArrayList<Cliente> listaClientes = new ArrayList<>();
    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }
    
    public DBClientesController() {
        lerArquivoTXT_Clientes();
    }
    
    private void lerArquivoTXT_Clientes(){
        File dataBase = new File("src/main/java/model/DataBaseClientes.txt");
        try{
            Scanner fileReader = new Scanner(dataBase);
            while(fileReader.hasNextLine()){
                String rawData = fileReader.nextLine();
                
                if(rawData.startsWith("//")){//define uma espécie de comentário para o arquivo txt
                    rawData = fileReader.nextLine();
                }
                String formatedData[] = rawData.split("; ");
                
                long cpf = Long.parseLong(formatedData[0]);
                String nome = formatedData[1];
                int idade = Integer.parseInt(formatedData[2]);
                String endereco = formatedData[3];
                String plano = formatedData[4];
                
                Cliente cliente = new Cliente(cpf, nome, idade, endereco, plano);
                listaClientes.add(cliente);
            }
        }catch(FileNotFoundException e){
            System.out.println("Arquivo não encontrado!");
        }
    }
    
    static public void escreverArquivoTXT_Clientes(Cliente cliente, CadastrarClienteView telaCadastrarCliente) throws IOException{
            String path = "src/main/java/model/DataBaseClientes.txt";
            FileWriter fileWriter = new FileWriter(path, true);
            String formatacaoData = cliente.getCpf()+"; "+cliente.getNome()+"; "+cliente.getIdade()+"; "+cliente.getEndereco()+"; "+cliente.getPlano()+"\n";
            
            fileWriter.write(formatacaoData);
            fileWriter.close();
            
            JOptionPane.showMessageDialog(telaCadastrarCliente, "Cliente cadastrado!");
    }
    
    public void excluirDoArquivo(Long CPFExcluir, ExcluirClienteView excluirClienteView) throws IOException{
        boolean cpfFound = false;
        for(int i=0; i<listaClientes.size(); i++){
            if(listaClientes.get(i).getCpf()==CPFExcluir){
                listaClientes.remove(i);
                cpfFound = true;
                break;
            }
        }
        if(cpfFound){
            String path = "src/main/java/model/DataBaseClientes.txt";
            FileWriter overWriter = new FileWriter(path, false);
            FileWriter writer = new FileWriter(path, true);
            File dataBase = new File(path);
            Scanner fileReader = new Scanner(dataBase);

            //limpa o arquivo
            while(fileReader.hasNextLine()){
                overWriter.write("\n");
            }

            //reescreve o comentário guia
            writer.write("//cpf; nome; idade; endereço; plano\n");
            //reescreve o arquivo sem o cliente excluído
            for(int i=0; i<listaClientes.size(); i++){
                String formatacaoData = listaClientes.get(i).getCpf()+"; "+listaClientes.get(i).getNome()+"; "+listaClientes.get(i).getIdade()+"; "+listaClientes.get(i).getEndereco()+"; "+listaClientes.get(i).getPlano()+"\n";
                writer.write(formatacaoData);
            }

            writer.close();
            JOptionPane.showMessageDialog(excluirClienteView, "Exclusão realizada");
        }else{
            JOptionPane.showMessageDialog(excluirClienteView, "Esse cliente não existe!");
        }
    }
    
}
