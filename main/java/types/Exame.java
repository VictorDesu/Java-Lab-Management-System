/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package types;

import java.util.Date;

/**
 *
 * @author Victor
 */
public class Exame {
    private Cliente cliente;
    private String tipo;
    private String data;
    private String resultado;
    private double preco;
    private int id;

    public Exame() {
    }

    public Exame(Cliente cliente, String tipo, String data, String resultado, double preco, int id) {
        this.cliente = cliente;
        this.tipo = tipo;
        this.data = data;
        this.resultado = resultado;
        this.preco = preco;
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
