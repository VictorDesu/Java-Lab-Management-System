/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package types;

import types.Funcionario;

/**
 *
 * @author aluno
 */
public class Gerente extends Funcionario{

    public Gerente(long cpf, String nome, int idade, double salario, float horasSemanais, String senha, int cargo) {
        super(cpf, nome, idade, salario, horasSemanais, senha, cargo);
    }

}
