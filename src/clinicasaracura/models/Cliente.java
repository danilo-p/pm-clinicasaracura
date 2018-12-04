/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.models;

/**
 *
 * @author danilo
 */
public class Cliente {

    private Integer id;
    private Pessoa pessoa;

    public Cliente() {
    }

    public Cliente(int id, Pessoa pessoa) {
        this.id = id;
        this.pessoa = pessoa;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
