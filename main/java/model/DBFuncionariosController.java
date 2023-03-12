/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.CadastrarFuncionarioController;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import types.Atendente;
import types.Enfermeiro;
import types.Funcionario;
import types.Gerente;
import types.PessoalLimpeza;
import view.CadastrarFuncionarioView;
import view.ExcluirFuncionarioView;

/**
 *
 * @author aluno
 */
public class DBFuncionariosController {
    
    //lista de funcionarios
    private ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();
    public ArrayList<Funcionario> getListaFuncionarios() {
        return listaFuncionarios;
    }
    
    public DBFuncionariosController() {
        lerArquivoTXT_Funcionarios();
    }
    
    private void lerArquivoTXT_Funcionarios(){
        File dataBase = new File("src/main/java/model/DataBaseFuncionarios.txt");
        try{
            Scanner fileReader = new Scanner(dataBase);
            while(fileReader.hasNextLine()){
                String rawData = fileReader.nextLine();
                
                //define uma espécie de comentário para o arquivo txt
                if(rawData.startsWith("//")){
                    rawData = fileReader.nextLine();
                }
                
                //define o separador das informações no arquivo
                String formatedData[] = rawData.split("; ");
                
                long cpf = Long.parseLong(formatedData[0]);
                String nome = formatedData[1];
                int idade = Integer.parseInt(formatedData[2]);
                double salario = Double.parseDouble(formatedData[3]);
                float horasSemanais = Float.parseFloat(formatedData[4]);
                String senha = formatedData[5];
                int cargo = Integer.parseInt(formatedData[6]);
                
                Funcionario funcionario;
                switch(cargo){
                    case 0 -> {
                        funcionario = new Atendente(cpf, nome, idade, salario, horasSemanais, senha, cargo);
                        this.listaFuncionarios.add(funcionario);
                    }
                    case 1 -> {
                        funcionario = new Gerente(cpf, nome, idade, salario, horasSemanais, senha, cargo);
                        this.listaFuncionarios.add(funcionario);
                    }
                    case 2 -> { 
                        funcionario = new Enfermeiro(cpf, nome, idade, salario, horasSemanais, senha, cargo);
                        this.listaFuncionarios.add(funcionario);
                    }
                    case 3 -> {
                        funcionario = new PessoalLimpeza(cpf, nome, idade, salario, horasSemanais, senha, cargo);
                        this.listaFuncionarios.add(funcionario);
                    }
                    default -> {
                        System.out.println("Cargo inválido");
                    }
                }
            }
        }catch(FileNotFoundException e){
            System.out.println("Arquivo não encontrado!");
        }
    }
    
    public void escreverArquivoTXT_Funcionarios(Funcionario funcionario, CadastrarFuncionarioView telaCadastrarFuncionario, int cargo){
        try {
            String path = "src/main/java/model/DataBaseFuncionarios.txt";
            FileWriter fileWriter = new FileWriter(path, true);
            String formatacaoData = funcionario.getCpf()+"; "+funcionario.getNome()+"; "+funcionario.getIdade()+"; "+funcionario.getSalario()+"; "+funcionario.getHorasSemanais()+"; "+funcionario.getSenha()+"; "+cargo+"\n";
            
            fileWriter.write(formatacaoData);
            fileWriter.close();
            
            JOptionPane.showMessageDialog(telaCadastrarFuncionario, "Funcionário cadastrado!");
        } catch (HeadlessException | IOException e) {
            JOptionPane.showMessageDialog(telaCadastrarFuncionario, "Erro!");
            CadastrarFuncionarioController cadastrarFuncionarioController = CadastrarFuncionarioController.getInstancia();
            cadastrarFuncionarioController.limpar();
        }
    }
    
    public void excluirDoArquivo(Long CPFExcluir, ExcluirFuncionarioView excluirFuncionarioView) throws IOException{
        boolean cpfFound = false;
        for(int i=0; i<listaFuncionarios.size(); i++){
            if(listaFuncionarios.get(i).getCpf()==CPFExcluir){
                listaFuncionarios.remove(i);
                cpfFound = true;
            }
        }
        if(cpfFound){
            String path = "src/main/java/model/DataBaseFuncionarios.txt";
            FileWriter overWriter = new FileWriter(path, false);
            FileWriter writer = new FileWriter(path, true);
            File dataBase = new File(path);
            Scanner fileReader = new Scanner(dataBase);

            //limpa o arquivo
            while(fileReader.hasNextLine()){
                overWriter.write("\n");
            }

            //reescreve o comentário guia
            writer.write("//cpf; nome; idade; salario; horas/semana; senha; cargo\n");
            //reescreve o arquivo sem o cliente excluído
            for(int i=0; i<listaFuncionarios.size(); i++){
                String formatacaoData = listaFuncionarios.get(i).getCpf()+"; "+listaFuncionarios.get(i).getNome()+"; "+listaFuncionarios.get(i).getIdade()+"; "+listaFuncionarios.get(i).getSalario()+"; "+listaFuncionarios.get(i).getHorasSemanais()+"; "+listaFuncionarios.get(i).getSenha()+"; "+listaFuncionarios.get(i).getCargo()+"\n";
                writer.write(formatacaoData);
            }

            writer.close();
            JOptionPane.showMessageDialog(excluirFuncionarioView, "Exclusão realizada");   
        }else{
            JOptionPane.showMessageDialog(excluirFuncionarioView, "Esse funcionário não existe!");   
        }
    }
}
