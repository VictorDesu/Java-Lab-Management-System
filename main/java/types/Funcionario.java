/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package types;

/**
 *
 * @author aluno
 */
public abstract class Funcionario {
    private long cpf;
    private String nome;
    private int idade;
    private double salario;
    private float horasSemanais;
    private String senha;
    private int cargo;

    public Funcionario() {
    }
    
    public Funcionario(long cpf, String nome, int idade, double salario, float horasSemanais, String senha, int cargo) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
        this.salario = salario;
        this.horasSemanais = horasSemanais;
        this.senha = senha;
        this.cargo = cargo;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public float getHorasSemanais() {
        return horasSemanais;
    }

    public void setHorasSemanais(float horasSemanais) {
        this.horasSemanais = horasSemanais;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getCargo() {
        return cargo;
    }

    public void setCargo(int cargo) {
        this.cargo = cargo;
    }
}
