/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.models;

/**
 * Modelo para pessoas.
 *
 * @author danilo
 */
public class Pessoa extends Agendavel {

    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private int tipo;

    public Pessoa() {
        super();
    }

    public Pessoa(String nome, String cpf, String telefone, int tipo) {
        super();
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

}
