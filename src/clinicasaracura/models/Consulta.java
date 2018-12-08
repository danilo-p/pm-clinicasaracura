/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicasaracura.models;

import java.sql.Date;
import java.sql.Time;

/**
 * Consulta para agenda.
 *
 * @author danilo
 */
public class Consulta {

    private int id;
    private Date data;
    private Time duracao;
    private Medico medico;
    private Cliente cliente;
    
    public Consulta() {}
    
    public Consulta(Date data, Time duracao, Medico medico, Cliente cliente) {
        this.data = data;
        this.duracao = duracao;
        this.medico = medico;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getDuracao() {
        return duracao;
    }

    public void setDuracao(Time duracao) {
        this.duracao = duracao;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    @Override
    public String toString() {
        return this.id + ", " + this.data + ", " + this.cliente.getNome()
            + ", " + this.medico.getNome();
    }
}
