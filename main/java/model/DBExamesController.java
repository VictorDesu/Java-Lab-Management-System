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
import types.Exame;
import view.CadastrarExameView;
import view.ExcluirExameView;

/**
 *
 * @author Victor
 */
public class DBExamesController {
    //lista de clientes
    private ArrayList<Exame> listaExames = new ArrayList<>();
    public ArrayList<Exame> getListaExames() {
        return listaExames;
    }
    
    public DBExamesController(){
        lerArquivoTXT_Exames();
    }
    
    private void lerArquivoTXT_Exames(){
        File dataBase = new File("src/main/java/model/DataBaseExames.txt");
        try{
            Scanner fileReader = new Scanner(dataBase);
            DBClientesController dbClientesController = new DBClientesController();
            while(fileReader.hasNextLine()){
                String rawData = fileReader.nextLine();
                
                if(rawData.startsWith("//")){//define uma espécie de comentário para o arquivo txt
                    rawData = fileReader.nextLine();
                }
                String formatedData[] = rawData.split("; ");
                
                //DBClientesController dbClientesController = new DBClientesController();
                long cpf = Long.parseLong(formatedData[0]);
                Cliente cliente = null;
                for(int i=0; i<dbClientesController.getListaClientes().size(); i++){
                    if(dbClientesController.getListaClientes().get(i).getCpf()==cpf){
                        cliente = dbClientesController.getListaClientes().get(i);
                    }
                }
                
                String tipo = formatedData[1];
                String data = formatedData[2];
                String resultado = formatedData[3];
                double preco = Double.parseDouble(formatedData[4]);
                int id = Integer.parseInt(formatedData[5]);
                
                Exame exame = new Exame(cliente, tipo, data, resultado, preco, id);
                listaExames.add(exame);
            }
        }catch(FileNotFoundException e){
            System.out.println("Arquivo não encontrado!");
        }
    }
    
    public void escreverArquivoTXT_Exames(Exame exame, CadastrarExameView cadastrarExameView) throws IOException{
            String path = "src/main/java/model/DataBaseExames.txt";
            FileWriter fileWriter = new FileWriter(path, true);
            String formatacaoData = exame.getCliente().getCpf()+"; "+exame.getTipo()+"; "+exame.getData()+"; "+exame.getResultado()+"; "+exame.getPreco()+"; "+exame.getId()+"\n";
            
            fileWriter.write(formatacaoData);
            fileWriter.close();
            
            JOptionPane.showMessageDialog(cadastrarExameView, "Exame cadastrado!");
    }
    
    public void excluirDoArquivo(int IDExcluir, ExcluirExameView excluirExameView) throws IOException{
        boolean achou=false;
        for(int i=0; i<listaExames.size(); i++){
            if(listaExames.get(i).getId()==IDExcluir){
                listaExames.remove(i);
                achou=true;
            }
        }
        
        if(achou){
            String path = "src/main/java/model/DataBaseExames.txt";
            FileWriter overWriter = new FileWriter(path, false);
            FileWriter writer = new FileWriter(path, true);
            File dataBase = new File(path);
            Scanner fileReader = new Scanner(dataBase);

            //limpa o arquivo
            while(fileReader.hasNextLine()){
                overWriter.write("\n");
            }

            //reescreve o comentário guia
            writer.write("//cpf do cliente; tipo de exame; preço; data de realização; resultado\n");
            //reescreve o arquivo sem o cliente excluído
            for(int i=0; i<listaExames.size(); i++){
                String formatacaoData = listaExames.get(i).getCliente().getCpf()+"; "+listaExames.get(i).getTipo()+"; "+listaExames.get(i).getData()+"; "+listaExames.get(i).getResultado()+"; "+listaExames.get(i).getPreco()+"; "+listaExames.get(i).getId()+"\n";
                writer.write(formatacaoData);
            }
            
            JOptionPane.showMessageDialog(excluirExameView, "Exclusão realizada");
            
            writer.close();
            
        }else{
            JOptionPane.showMessageDialog(excluirExameView, "Não existe um exame com esse ID!");
        }
    }
}
